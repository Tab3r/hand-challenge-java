package es.nipalante.domain.entities;

public class MemoryCell {

    private static final int MAX_VALUE = 255;
    private static final int MIN_VALUE = 0;

    private Integer value = 0;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void increaseValue() {
        if (value == MAX_VALUE) value = MIN_VALUE;
        else this.value++;
    }

    public void decreaseValue() {
        if (value == MIN_VALUE) value = MAX_VALUE;
        else this.value--;
    }

    public char toChar() {
        return (char) (this.value.intValue());
    }
}
