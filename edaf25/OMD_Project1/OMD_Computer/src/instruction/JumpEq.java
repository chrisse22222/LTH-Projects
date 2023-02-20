package instruction;

import computer.Computer;
import computer.Memory;
import operand.Operand;

public class JumpEq implements Instruction {
    private Operand op1, op2;
    private int dest;

    public JumpEq(int dest, Operand op1, Operand op2) {
        this.dest = dest;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public void execute(Memory memory, Computer pc) {
        if(op1.getWord(memory).getValue()==op2.getWord(memory).getValue()){
            pc.setProgramCounter(dest);
        } else {
            pc.incrProgramCounter();
        }
    }

    @Override
    public String toString() {
        return "JEQ " + dest + " " + op1.toString() + " "+  op2.toString();
    }
}
