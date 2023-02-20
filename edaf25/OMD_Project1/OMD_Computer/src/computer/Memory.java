package computer;

import operand.Word;

import java.util.Arrays;

public abstract class Memory{

    protected Word [] memory;

    public Memory(int size){
        memory = new Word[size];
    }

    public void printMemory() {
        System.out.println(memory[0] + " " + memory[1]);
//        System.out.println(Arrays.toString(memory));
    }

    public Word get(int address){
        if (address > memory.length || address < 0){
            return null;
        } else{
            return memory[address];
        }
    }


    public abstract void populate(int size);
}
