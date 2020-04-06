public class Condition
{
    protected Expression leftExpression;
    protected String comparaison;
    protected Expression rightExpression;

    protected Condition leftCondition;
    protected String connecteur;
    protected Condition rightCondition;


    public Condition(Expression leftExpression, String comparaison, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.comparaison = comparaison;
        this.rightExpression = rightExpression;
    }


    public Condition(Condition leftCondition, String connecteur, Condition rightCondition) {
        this.leftCondition = leftCondition;
        this.connecteur = connecteur;
        this.rightCondition = rightCondition;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "leftExpression=" + leftExpression +
                ", comparaison='" + comparaison + '\'' +
                ", rightExpression=" + rightExpression +
                ", leftCondition=" + leftCondition +
                ", connecteur='" + connecteur + '\'' +
                ", rightCondition=" + rightCondition +
                '}';
    }
}
