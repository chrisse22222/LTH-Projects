package operand;


import computer.Memory;

public class Address implements Operand {

    private int address;
    public Address(int memIndex) {
        this.address = memIndex;
    }

    public Word getWord(Memory context){
        return context.get(address);
    }

    @Override
    public String toString() {
        return "[" + address + "]";
    }

}
