package by.motolyha.composite.entity;

import java.util.Collection;

public abstract class AbstractTextComponent {
    protected final TextComponentType componentType;

    public AbstractTextComponent(TextComponentType componentType) {
        this.componentType = componentType;
    }

    public TextComponentType getComponentType() {
        return componentType;
    }

    public abstract void add(AbstractTextComponent item);
    public abstract void addAll(Collection<? extends AbstractTextComponent> items);
    public abstract void remove(AbstractTextComponent item);
    public abstract void removeAll(Collection<? extends AbstractTextComponent> items);
    public abstract Collection<AbstractTextComponent> getChildren();
}
