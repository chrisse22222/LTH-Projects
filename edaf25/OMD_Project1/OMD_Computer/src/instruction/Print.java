package instruction;

import computer.Computer;
import computer.Memory;
import operand.Operand;

public class Print implements Instruction{
    private Operand op;

    public Print(Operand op) {
        this.op = op;

    }

    @Override
    public void execute(Memory memory, Computer pc) {
        System.out.println(op.getWord(memory).toString());
        pc.incrProgramCounter();
    }

    @Override
    public String toString() {
        return "PRT " + op.toString();
    }


}
