public class instruction
{
    String action; //Tourner,Avancer ou Ecrire / SI
    Expression expression;
    Condition cdt;
    Program program;


    public instruction(String action, Expression expression)
    {
        this.action = action;
        this.expression = expression;
    }


    public instruction(String action, Condition cdt, Program program) {

        this.action=action;
        this.cdt = cdt;
        this.program = program;
    }


    @Override
    public String toString() {
        return "instruction{" +
                "action='" + action + '\'' +
                ", expression=" + expression +
                ", cdt=" + cdt +
                ", program=" + program +
                '}';
    }
}
