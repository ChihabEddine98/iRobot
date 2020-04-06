import java.io.Reader;
import java.util.LinkedList;

public class Parser2 implements Parser {


    protected LookAhead1 reader;
    protected Program program;

    public Parser2(LookAhead1 reader) {
        this.reader = reader;
    }



    @Override
    public Program parseProgram(String exeName, Reader lookAhead1) {

        Program program=null;

        try {
            program=nonTerm_programme();
            reader.eat(Sym.EOF);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return program;
    }


    public Program nonTerm_programme() throws ParserException,Exception {

        Program program=null,progRetourner=null;

        if (reader.check(Sym.AVANCER) || reader.check(Sym.TOURNER) || reader.check(Sym.ECRIRE)
                || reader.check(Sym.SI) || reader.check(Sym.TANTQUE)
                || reader.check(Sym.FAIRE)|| reader.check(Sym.ALORS))
        {
            instruction instruction=nonTerm_instruction();
            if(!instruction.action.equals("Si") && !instruction.action.equals("TantQue"))
            {
                reader.eat(Sym.SEMICOL);
                program=nonTerm_programme();
            }

            progRetourner=new Program(instruction,program);

            if(program!=null)
            {
                this.program=progRetourner;
            }

        }

        else if(reader.check(Sym.FIN))
        {
            return this.program;
        }

        else
        {
            throw new ParserException(" dans programme ",reader.lexer.getPosition());
        }

        return progRetourner;

    }



    public instruction nonTerm_instruction() throws ParserException,Exception {

        Expression expression=null;
        instruction ins=null;


        if (reader.check(Sym.AVANCER))
        {
            reader.eat(Sym.AVANCER);
            reader.eat(Sym.LPAR);
            expression=nonTerm_expression();
            ins=new instruction("Avancer",expression);

            reader.eat(Sym.RPAR);
        }
        else if (reader.check(Sym.TOURNER))
        {
            reader.eat(Sym.TOURNER);
            reader.eat(Sym.LPAR);
            expression=nonTerm_expression();
            ins=new instruction("Tourner",expression);
            reader.eat(Sym.RPAR);
        }
        else if (reader.check(Sym.ECRIRE))
        {
            reader.eat(Sym.ECRIRE);
            reader.eat(Sym.LPAR);
            expression=nonTerm_expression();
            ins=new instruction("Ecrire",expression);
            reader.eat(Sym.RPAR);
        }
        else if (reader.check(Sym.SI))
        {

            reader.eat(Sym.SI);
            Condition cdtSi=nonTerm_condition();
            reader.eat(Sym.ALORS);
            Program prgAlors=nonTerm_programme();
            reader.eat(Sym.FIN);



            ins=new instruction("Si",cdtSi,prgAlors);






        }

        else if (reader.check(Sym.TANTQUE))
        {
            reader.eat(Sym.TANTQUE);
            Condition cdtTq=nonTerm_condition();
            reader.eat(Sym.FAIRE);
            Program prgFaire=nonTerm_programme();
            reader.eat(Sym.FIN);

            ins=new instruction("TantQue",cdtTq,prgFaire);


        }

        else
        {
            throw new ParserException(" dans Instruction ",reader.lexer.getPosition());

        }


        return ins;

    }

    public Condition nonTerm_condition() throws ParserException,Exception {
        Condition cdt;

        if(reader.check(Sym.LACC))
        {
            reader.eat(Sym.LACC);
            Condition leftCdt=nonTerm_condition();
            String conn=nonTerm_connecteur();
            Condition rightCdt=nonTerm_condition();
            reader.eat(Sym.RACC);

            cdt=new Condition(leftCdt,conn,rightCdt);


        }
        else if(reader.check(Sym.INT) ||reader.check(Sym.LIRE)
                || reader.check(Sym.LPAR))
        {
            Expression exp1=nonTerm_expression();
            String comp=nonTerm_comparaison();
            Expression exp2=nonTerm_expression();

            cdt=new Condition(exp1,comp,exp2);


        }
        else
        {
            throw new ParserException(" dans Condition ",reader.lexer.getPosition());

        }


        return cdt;

    }

    private String nonTerm_comparaison() throws ParserException,Exception {

        String comp="";

        if(reader.check(Sym.INF))
        {
            reader.eat(Sym.INF);
            comp="<";
        }
        else if(reader.check(Sym.SUP))
        {
            reader.eat(Sym.SUP);
            comp=">";

        }
        else if(reader.check(Sym.EQUAL))
        {
            reader.eat(Sym.EQUAL);
            comp="=";

        }

        else
        {
            throw new ParserException(" dans Comparaison ",reader.lexer.getPosition());

        }

        return comp;
    }

    private String nonTerm_connecteur() throws ParserException,Exception {

        String conn;

        if(reader.check(Sym.ET))
        {
            reader.eat(Sym.ET);
            conn="Et";
        }
        else if(reader.check(Sym.OU))
        {
            reader.eat(Sym.OU);
            conn="Ou";

        }
        else
        {
            throw new ParserException(" dans Connecteur ",reader.lexer.getPosition());

        }

        return conn;
    }

    public Expression nonTerm_expression() throws ParserException {

        Expression expression=null;

        try {
            if (reader.check(Sym.LIRE))
            {
                reader.eat(Sym.LIRE);
                expression=new Expression("Lire");
            }
            else if (reader.check(Sym.INT))
            {
                int n=term_nombre();
                expression=new Expression(n);


            }
            else if (reader.check(Sym.LPAR))
            {
                reader.eat(Sym.LPAR);

                Expression expression1=nonTerm_expression();

                if(reader.check(Sym.INT))
                {
                    Expression expression2=nonTerm_expression();
                    reader.eat(Sym.RPAR);

                    if(expression1!=null && expression2!=null) {
                        if (expression1.expressions.size() == 1 && !expression1.expressions.get(0).equals("Lire")
                                &&
                                expression2.expressions.size() == 1 && !expression2.expressions.get(0).equals("Lire")) {

                            expression = new Expression(Integer.parseInt(expression1.expressions.get(0)) +
                                    Integer.parseInt(expression2.expressions.get(0)));
                        }
                    }
                    return expression;
                }

                String op=term_operation();

                Expression expression2=nonTerm_expression();

                reader.eat(Sym.RPAR);

                if(expression1!=null && expression2!=null)
                {
                    if(expression1.expressions.size()==1 && !expression1.expressions.get(0).equals("Lire")
                            &&
                            expression2.expressions.size()==1 && !expression2.expressions.get(0).equals("Lire"))
                    {
                        if(op.equals("+"))
                        {
                            expression=new Expression(Integer.parseInt(expression1.expressions.get(0))+
                                    Integer.parseInt(expression2.expressions.get(0)));
                        }
                        else if(op.equals("-"))
                        {
                            expression=new Expression(Integer.parseInt(expression1.expressions.get(0))-
                                    Integer.parseInt(expression2.expressions.get(0)));
                        }

                    }
                    else
                    {
                        LinkedList<String> exps=new LinkedList<>();


                        for (String s1:expression1.expressions)
                        {
                            exps.add(s1);
                        }

                        for (String s2:expression2.expressions)
                        {
                            exps.add(s2);
                        }



                        expression=new Expression(exps);
                    }

                }

            }

            else
            {
                throw new ParserException(" dans Expression ",reader.lexer.getPosition());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return expression;

    }

    private String term_operation() throws Exception,ParserException {

        String op;

        if(reader.check(Sym.PLUS))
        {
            reader.eat(Sym.PLUS);
            op="+";
        }
        else if(reader.check(Sym.MOINS))
        {
            reader.eat(Sym.MOINS);
            op="-";

        }

        else
        {
            throw new ParserException(" dans operation ",reader.lexer.getPosition());

        }

        return op;

    }

    public int term_nombre() throws ParserException {

        int n=-1;

        try {
            if(reader.check(Sym.INT))
            {
                n=reader.getIntValue();
                reader.eat(Sym.INT);
            }
            else
            {
                throw new ParserException(" dans nombre ! ",reader.lexer.getPosition());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return n;
    }





}
