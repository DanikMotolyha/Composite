package by.motolyha.composite.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TextComposite extends AbstractTextComponent{
    private final List<AbstractTextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType componentType) {
        super(componentType);
    }

    @Override
    public void add(AbstractTextComponent item) {
        components.add(item);
    }

    @Override
    public void addAll(Collection<? extends AbstractTextComponent> items) {
        components.addAll(items);
    }

    @Override
    public void remove(AbstractTextComponent item) {
        components.remove(item);
    }

    @Override
    public void removeAll(Collection<? extends AbstractTextComponent> items) {
        components.removeAll(items);
    }

    @Override
    public Collection<AbstractTextComponent> getChildren() {
        return new ArrayList<>(components);
    }

    @Override
    public int hashCode() {
        return 31 * components.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        var textComposite = (TextComposite) obj;
        return components.equals(textComposite.components)
                && textComposite.componentType.equals(componentType);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        components.forEach(component -> {
            TextComponentType type = component.getComponentType();
            String prefix = type.getPrefix();
            String suffix = type.getSuffix();
            stringBuilder.append(prefix).append(component).append(suffix);
        });

        return (componentType != TextComponentType.TEXT)
                ? stringBuilder.toString().strip()
                : stringBuilder.toString().stripTrailing();
    }
}
