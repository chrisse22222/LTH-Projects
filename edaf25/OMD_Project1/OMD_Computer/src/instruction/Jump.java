package instruction;

import computer.Computer;
import computer.Memory;

public class Jump implements Instruction{
    private int dest;


    public Jump (int dest) {
        this.dest = dest;

    }

    @Override
    public void execute(Memory memory, Computer pc) {
        pc.setProgramCounter(dest);
    }

    @Override
    public String toString() {
        return "JMP " + dest;
    }

}
