package partie1;


public class Program
{
    private Instruction instruction;
    private Program program;


    public Program(Instruction instruction, Program program) {
        this.instruction = instruction;
        this.program = program;
    }

    @Override
    public String toString() {
        return "Program{" +
                "instruction=" + instruction +
                ", program=" + program +
                '}';
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
