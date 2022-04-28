package me.junhua.common.enums;

public interface EnumTypeTriple<K extends Enum<K>, T,O, P> {

    T getType();

    O getValue();

    P getDesc();
}
