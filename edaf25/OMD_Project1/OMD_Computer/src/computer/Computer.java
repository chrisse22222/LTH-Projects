package computer;

import program.Program;

public class Computer {
    private Program program;
    private int programCounter;

    private Memory memory;

    public Computer(Memory memory) {
        this.memory = memory;
    }

    public void load(Program program) {
        this.program = program;
    }

    public void run(){
        setProgramCounter(0);
        while(getProgramCounter() != -1) {
            program.get(getProgramCounter()).execute(memory, this);
        }
    }


    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public void incrProgramCounter() {
        programCounter++;
    }

}
