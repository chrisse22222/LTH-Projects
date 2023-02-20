package instruction;

import operand.Address;
import operand.LongWord;
import operand.Operand;
import operand.Word;

public class Add extends Expression {

    public Add (Operand op1, Operand op2, Address adress) {
        super(op1, op2, adress, "ADD");
    }

    @Override
    protected void op(Word w1, Word w2) {
        w1.add(w2);
    }
}
