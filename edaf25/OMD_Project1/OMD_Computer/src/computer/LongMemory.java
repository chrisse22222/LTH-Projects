package computer;


import operand.Address;
import operand.LongWord;
import operand.Operand;

public class LongMemory extends Memory {
    public LongMemory(int size) {
        super(size);
        populate(size);
    }

    @Override
    public void populate(int size) {
        for (int i = 0; i < size; i++) {
            this.memory[i] = new LongWord(0);
        }
    }
}
