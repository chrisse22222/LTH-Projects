package operand;

public interface Word <V> extends Operand {
    public void setValue(Word w1);
    public V getValue();
    public void add(Word w1);
    public void mul(Word w1);
}
