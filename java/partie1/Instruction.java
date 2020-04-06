package partie1;

public class Instruction
{
    String action; //Tourner,Avancer ou Ecrire
    Expression expression;

    public Instruction(String action, Expression expression) {
        this.action = action;
        this.expression = expression;
    }
}
