package domain.interfaces;


public interface InterfaceValue {
    InterfaceType getType();

    InterfaceValue deepCopy();
}
