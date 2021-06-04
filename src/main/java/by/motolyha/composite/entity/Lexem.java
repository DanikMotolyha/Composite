package by.motolyha.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class Lexem extends AbstractTextComponent {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String value;

    public Lexem(TextComponentType componentType, String value) {
        super(componentType);
        this.value = value;
    }

    @Override
    public void add(AbstractTextComponent item) {
        LOGGER.error("UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    @Override
    public void addAll(Collection<? extends AbstractTextComponent> items) {
        LOGGER.error("UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(AbstractTextComponent item) {
        LOGGER.error("UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAll(Collection<? extends AbstractTextComponent> items) {
        LOGGER.error("UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<AbstractTextComponent> getChildren() {
        LOGGER.error("UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }
    @Override
    public int hashCode() {
        return 31 * componentType.hashCode() + value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        var lexeme = (Lexem) obj;
        return lexeme.value.equals(this.value)
                && lexeme.componentType.equals(this.componentType);
    }

    @Override
    public String toString() {
        return value;
    }
}
