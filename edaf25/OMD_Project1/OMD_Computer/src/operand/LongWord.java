package operand;

import computer.Memory;

public class LongWord implements Word {

    private long value;
    public LongWord(long value) {
        this.value = value;
    }

    @Override
    public void setValue(Word w1) {
        if (w1 instanceof LongWord){
            this.value = ((LongWord) w1).value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void add(Word w1) {
        if (w1 instanceof LongWord){
            this.value +=((LongWord) w1).value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void mul(Word w1) {
        if (w1 instanceof LongWord){
            this.value *=((LongWord) w1).value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Word getWord(Memory Context) {
        return this;
    }

    @Override
    public String toString(){
        return Long.toString(value);
    }
}
