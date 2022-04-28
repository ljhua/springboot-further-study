package me.junhua.common.enums;

public interface EnumType<K extends Enum<K>, T, P> {

    T getStatus();

    P getDesc();
}
