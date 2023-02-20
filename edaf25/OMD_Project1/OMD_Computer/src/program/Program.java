package program;

import instruction.Instruction;

import java.util.ArrayList;

public abstract class Program extends ArrayList<Instruction> {

    public Program () {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(Instruction i : this) {
            sb.append(count).append(" ");
            sb.append(i.toString());
            sb.append("\n");
            count++;
        }
        return sb.toString();
    }
}
