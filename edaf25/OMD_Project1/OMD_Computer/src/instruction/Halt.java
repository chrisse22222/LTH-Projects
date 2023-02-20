package instruction;

import computer.Computer;
import computer.Memory;

public class Halt implements Instruction{

    public Halt() {

    }

    @Override
    public void execute(Memory memory, Computer pc) {
        pc.setProgramCounter(-1);
    }

    @Override
    public String toString() {
        return "HLT ";
    }



}
