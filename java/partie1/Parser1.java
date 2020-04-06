package partie1;


import java.io.Reader;
import java.util.List;

public class Parser1 implements Parser {


    protected LookAhead1 reader;

    public Parser1(LookAhead1 reader) {
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


    public Program nonTerm_programme() throws Exception {

        Program program,progRetourner=null;

        if (reader.check(Sym.AVANCER) || reader.check(Sym.TOURNER) || reader.check(Sym.ECRIRE))
        {
            Instruction instruction=nonTerm_instruction();
            reader.eat(Sym.SEMICOL);
            program=nonTerm_programme();

            progRetourner=new Program(instruction,program);

        }
        else if(reader.check(Sym.EOF))
        {
            reader.eat(Sym.EOF);
        }
        else
        {
            throw new Exception(" dans programme ");
        }

        return progRetourner;

    }



    public Instruction nonTerm_instruction() throws Exception {

        Expression expression=null;
        Instruction ins=null;


        if (reader.check(Sym.AVANCER))
        {
            reader.eat(Sym.AVANCER);
            reader.eat(Sym.LPAR);
            expression=nonTerm_expression();
            ins=new Instruction("Avancer",expression);

            reader.eat(Sym.RPAR);
        }
        else if (reader.check(Sym.TOURNER))
        {
            reader.eat(Sym.TOURNER);
            reader.eat(Sym.LPAR);
            reader.eat(Sym.LPAR);
            expression=nonTerm_expression();
            ins=new Instruction("Tourner",expression);
            reader.eat(Sym.RPAR);
        }
        else if (reader.check(Sym.ECRIRE))
        {
            reader.eat(Sym.ECRIRE);
            reader.eat(Sym.LPAR);
            reader.eat(Sym.LPAR);
            expression=nonTerm_expression();
            ins=new Instruction("Ecrire",expression);
            reader.eat(Sym.RPAR);
        }
        else
        {
            throw new Exception(" dans instruction ");
        }


        return ins;

    }

    public Expression nonTerm_expression() throws Exception {

        Expression expression=null;

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
            nonTerm_expression();
            if(reader.check(Sym.INT))
            {
                expression=nonTerm_expression();
                reader.eat(Sym.RPAR);
                return expression;
            }
            term_operation();
            expression=nonTerm_expression();
            reader.eat(Sym.RPAR);

        }

        else
        {
            throw new Exception(" dans expression ");
        }

        return expression;

    }

    private String term_operation() throws Exception {

        String op="";

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
            throw new Exception(" dans Operation ");
        }

        return op;

    }

    public int term_nombre() throws Exception {


        int n;

        if(reader.check(Sym.INT))
        {
            reader.eat(Sym.INT);
            n=Integer.parseInt(reader.current.value());

        }
        else
        {
            throw new Exception(" dans Nombre ");
        }


        return n;
    }





}
