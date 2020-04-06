package partie1;

public class Interpreter1 implements Interpreter {



    @Override
    public void run(Program prog, Grid grid)
    {

        Instruction ins=prog.getInstruction();
        deroulerInstruction(ins,grid);
        run(prog.getProgram(),grid);

    }

    public void deroulerInstruction(Instruction ins,Grid grid)
    {
        String action=ins.action;

        switch (action)
        {
            case "Tourner":
                if(ins.expression.expressions.size()==1)
                {
                    if(ins.expression.expressions.get(0).equals("Lire"))
                    {
                        grid.tourner(grid.lire());
                    }
                    else
                    {
                        int n=Integer.parseInt(ins.expression.expressions.get(0));
                        grid.tourner(n);
                    }
                }
                break;

            case "Avancer":
                if(ins.expression.expressions.size()==1)
                {
                    if(ins.expression.expressions.get(0).equals("Lire"))
                    {
                        grid.avancer(grid.lire());
                    }
                    else
                    {
                        int n=Integer.parseInt(ins.expression.expressions.get(0));
                        grid.tourner(n);
                    }
                }
                break;

            case "Ecrire":
                if(ins.expression.expressions.size()==1)
                {
                    if(ins.expression.expressions.get(0).equals("Lire"))
                    {
                        grid.ecrire(grid.lire());
                    }
                    else
                    {
                        int n=Integer.parseInt(ins.expression.expressions.get(0));
                        grid.tourner(n);
                    }
                }
                break;
        }

    }




}

