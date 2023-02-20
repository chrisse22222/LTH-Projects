package operand;

import computer.Memory;

public interface Operand {

    public Word getWord(Memory Context);
    public String toString();
}
