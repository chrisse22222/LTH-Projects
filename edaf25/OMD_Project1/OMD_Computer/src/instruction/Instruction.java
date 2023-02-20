package instruction;

import computer.Computer;
import computer.Memory;

public interface Instruction {

    public void execute(Memory memory, Computer pc);

    public String toString();

}
