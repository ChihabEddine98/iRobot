public class Program
{
    private instruction instruction;
    private Program program;


    public Program()
    {

    }

    public Program(instruction instruction, Program program)
    {
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

    public instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(instruction instruction) {
        this.instruction = instruction;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
