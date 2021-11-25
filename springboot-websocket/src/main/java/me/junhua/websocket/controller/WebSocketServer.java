package me.junhua.websocket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.junhua.websocket.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ybq
 * @Description 开启websocket支持
 * @Date 2020/6/9 11:29
 */
@Component
@ServerEndpoint("/wsServer/{userId}")
@Slf4j
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";


    /**
     * 连接建立成功调用的方法
     *
     * @param session
     * @param usersId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String usersId) {
        this.session = session;
        this.userId = usersId;
        log.info("=====================》开始建立连接《=====================");
        log.info("用户id:{}", usersId);
        if (webSocketMap.containsKey(usersId)) {
            log.info("当前用户已存在，用户id: {}", usersId);
            log.info("移除用户，用户id：{}", usersId);
            webSocketMap.remove(usersId);
            log.info("重新加入该用户，用户id:{}", usersId);
            webSocketMap.put(userId, this);
        } else {
            log.info("用户第一次申请连接，加入该用户，用户id:{}", usersId);
            webSocketMap.put(userId, this);
            // 在线数加一
            addOnlineCount();
        }
        try {
            sendMessage("连接成功");
            log.info("=====================》连接建立成功《=====================");
        } catch (IOException e) {
            log.error("用户:" + usersId + ",网络异常!!!!!!");
        }
    }

    @OnClose
    public void onClose() {
        log.info("=====================》开始关闭连接《=====================");
        log.info("申请关闭连接的用户id：{}", userId);
        if (webSocketMap.containsKey(userId)) {
            log.info("移除该用户，用户id:{}", userId);
            webSocketMap.remove(userId);
            log.info("更新当前连接数");
            subOnlineCount();
            log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());

        } else {
            log.info("用户不存在，用户id:{}", userId);
        }
        log.info("==================》连接关闭成功《==========================");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("======================》接收客户端消息开始《======================");
        log.info("接收到用户消息，用户id:{}", userId);
        //可以群发消息
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {
            JSONObject jsonObject = new JSONObject();
            String toUserId = userId;
            try {
                //解析发送的报文
                jsonObject = JSON.parseObject(message);
                log.info("解析到用户报文信息，message:{}", jsonObject.toJSONString());
                toUserId = jsonObject.getString("toUserId");
                log.info("解析到要发送的客户端toUserId:{}", toUserId);
                log.info("为防止串改，追加发送人信息fromUserId:{}", userId);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId", userId);
                message = jsonObject.toJSONString();
            } catch (JSONException e) {
                log.error("非json格式消息");
            }
            //传送给对应toUserId用户的websocket
            try {
                if (StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
                    synchronized (webSocketMap.get(toUserId)) {
                        log.info("发送报文消息到客户端，{}", message);
                        webSocketMap.get(toUserId).sendMessage(message);
                        log.info("发送报文消息到客户端成功");
                    }
                } else {
                    log.info("请求的客户端不在线，toUserId:{}", toUserId);
                    //否则不在这个服务器上，发送到mysql或者redis


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("======================》消息发送结束《======================");
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, String userId) throws IOException {
        log.info("======================》发送自定义消息开始《======================");
        log.info("发送自定义消息到:" + userId + "，报文:" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            log.info("开始发送");
            webSocketMap.get(userId).sendMessage(message);
            log.info("发送完成");
        } else {
            log.error("用户" + userId + ",不在线！");
        }
        log.info("======================》发送自定义消息结束《======================");
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
