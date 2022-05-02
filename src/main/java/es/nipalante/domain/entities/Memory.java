package es.nipalante.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    private List<MemoryCell> memory = new ArrayList<>();
    private Integer pointerPosition;

    public Memory() {
        memory.add(new MemoryCell());
        pointerPosition = 0;
    }

    public MemoryCell getCurrentCell() {
        return this.memory.get(pointerPosition);
    }

    public void moveLeft() {
        if (this.pointerPosition > 0)
            this.pointerPosition--;
    }

    public void moveRight() {
        this.pointerPosition++;
        if (this.pointerPosition == this.memory.size()) {
            this.memory.add(new MemoryCell());
        }
    }

    public Integer getPointerPosition() {
        return pointerPosition;
    }

    public void setPointerPosition(Integer pointerPosition) {
        this.pointerPosition = pointerPosition;
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (MemoryCell cell : memory) {
            stringBuilder.append(cell.toChar());
        }

        return stringBuilder.toString();
    }

}
