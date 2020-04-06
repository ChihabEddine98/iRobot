import java.util.LinkedList;

public class Expression
{
    LinkedList<String> expressions;

    public Expression(String s)
    {
     expressions=new LinkedList<>();
     expressions.add(s);
    }

    public Expression(int n)
    {
        expressions=new LinkedList<>();
        expressions.add(""+n);
    }



    public Expression(LinkedList<String> expressions)
    {
        this.expressions = expressions;
    }


    @Override
    public String toString() {
        return "Expression{" +
                "expressions=" + expressions +
                '}';
    }
}
