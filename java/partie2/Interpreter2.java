import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class Interpreter2 implements Interpreter {

    boolean siBool =false;
    boolean tqBool =false;
    instruction insFirst;
    int cpt=0;
    PrintWriter writer = null;


    @Override
    public void run(Program prog, Grid grid)
    {


        cpt++;

        try {
            String fileName="C:\\Users\\Chihab Eddine\\Desktop\\18_19__Mi\\S4\\ADS4\\gui_ads4\\src\\gui\\grilles\\grille"+cpt+".txt";
            writer = new PrintWriter(fileName, "UTF-8");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println(grid);
        writer.close();



        instruction ins;


        ins=prog.getInstruction();


        if(ins!=null)
        {

            if(ins.action.equals("Si")) {
                siBool = evaluateCdt(ins.cdt, grid);
                insFirst=ins;
            }
            else if(ins.action.equals("TantQue")) {
                tqBool=evaluateCdt(ins.cdt,grid);
                insFirst=ins;
            }
            Program p=ins.program;

                if(siBool)
                {


                    if(p!=null)
                    {

                          ins=p.getInstruction();

                            deroulerInstruction(ins,grid);

                            if(p.getProgram()!=null)
                            {
                                run(p.getProgram(),grid);

                            }

                    }
                    else
                    {
                        ins=prog.getInstruction();

                        deroulerInstruction(ins,grid);

                        if(prog.getProgram()!=null)
                        {
                            run(prog.getProgram(),grid);

                        }

                    }




                }
                else if (tqBool)
                {
                     p=ins.program;


                    tqBool = evaluateCdt(insFirst.cdt, grid);
                    while (tqBool)
                    {
                        if(p!=null)
                        {

                            ins=p.getInstruction();

                            deroulerInstruction(ins,grid);

                            if(p.getProgram()!=null)
                            {
                                run(p.getProgram(),grid);

                            }

                        }
                        else
                        {
                            ins=prog.getInstruction();

                            deroulerInstruction(ins,grid);

                            if(prog.getProgram()!=null)
                            {
                                run(prog.getProgram(),grid);

                            }

                        }


                    }



                }
                else
                {
                    System.out.println(" Condition fausse !");
                    System.out.println();

                }



        }

    }

    public boolean evaluateCdt(Condition cdt,Grid grid)
    {

        boolean totalBool=false;

        if(cdt.leftCondition!=null && cdt.rightCondition!=null)
        {
            switch (cdt.connecteur)
            {
                case "Et":
                {
                    totalBool=evaluateCdt(cdt.leftCondition,grid) && evaluateCdt(cdt.rightCondition, grid);
                    break;
                }

                case "Ou":
                {
                    totalBool=evaluateCdt(cdt.leftCondition,grid) || evaluateCdt(cdt.rightCondition, grid);

                    break;
                }
            }
        }

        else if(cdt!=null && cdt.leftExpression!=null && cdt.rightExpression!=null ) {


            int cptLeft=0,cptRight=0;


            LinkedList<String> expsLeft=new LinkedList<>();
            LinkedList<String> expsRight=new LinkedList<>();


            for (String s1:cdt.leftExpression.expressions)
            {
                expsLeft.add(s1);
            }

            if (expsLeft != null) {
                for (String s : expsLeft) {
                    if (s.equals("Lire")) {

                        cptLeft += grid.lire();
                    } else {
                        int n = Integer.parseInt(s);
                        cptLeft += n;
                    }


                }
            }
            for (String s2:cdt.rightExpression.expressions)
            {
                expsRight.add(s2);
            }

            if (expsRight != null) {
                for (String s : expsRight) {
                    if (s.equals("Lire")) {

                        cptRight += grid.lire();
                    } else {
                        int n = Integer.parseInt(s);
                        cptRight += n;
                    }


                }
            }


            switch (cdt.comparaison)
            {
                case "<":
                    totalBool=cptLeft<cptRight;
                    break;
                case ">":
                    totalBool=cptRight<cptLeft;

                    break;
                case "=":
                    totalBool=cptLeft==cptRight;
                    break;
            }


        }


        return totalBool;

    }

    public void deroulerInstruction(instruction ins, Grid grid)
    {
        String action=ins.action;
        String affich=action+"(";
        LinkedList<String> exp;
        Condition cdt;




        switch (action)
        {
            case "Tourner":
                if(ins.expression!=null && ins.expression.expressions!=null ) {
                    exp = ins.expression.expressions;
                    int tourne = 0;

                    if (exp != null) {
                        for (String s : exp) {
                            if (s.equals("Lire")) {
                                tourne += grid.lire();
                                affich += "Lire";
                            } else {
                                int n = Integer.parseInt(s);
                                tourne += n;
                                affich += n + ")";

                            }
                        }


                        grid.tourner(tourne);
                        System.out.println(" Tourner :" + tourne);

                    }

                }
                break;
            case "Avancer":

                if(ins.expression!=null && ins.expression.expressions!=null ) {
                    exp = ins.expression.expressions;
                    int avance = 0;

                    if (exp != null) {
                        for (String s : exp) {
                            if (s.equals("Lire")) {

                                affich += "Lire)";
                                avance += grid.lire();
                            } else {
                                int n = Integer.parseInt(s);
                                affich += n + ")";
                                avance += n;
                            }


                        }

                        grid.avancer(avance);
                        System.out.println("Avancer :" + avance);

                    }
                }
                break;
            case "Ecrire":
                if(ins.expression!=null && ins.expression.expressions!=null ) {
                    exp = ins.expression.expressions;
                    int ecrire = 0;

                    if (exp != null) {
                        for (String s : exp) {
                            if (s.equals("Lire")) {
                                affich += "Lire";
                                ecrire += grid.lire();
                            } else {
                                int n = Integer.parseInt(s);
                                affich += n + ")";
                                ecrire += n;
                            }
                        }


                        grid.ecrire(ecrire);
                        System.out.println(" Ecrire :" + ecrire);
                        System.out.println();
                        System.out.println();



                    }
                }
                break;


                }




        }




    }






