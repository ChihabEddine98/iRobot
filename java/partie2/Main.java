import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

class Main
{
    public static void main(String[] args) throws Exception {
        String exeName = "Main";
        IOEnv ioEnv = IOEnv.parseArgs(exeName, args);
        Lexer lexer = new Lexer(ioEnv.inProgram);
        LookAhead1 look = new LookAhead1(lexer);
        Parser parser = new Parser2(look);

        try {
            Program prog = parser.parseProgram(exeName, ioEnv.inProgram);
            System.out.println();
            System.out.println();

            System.out.println(" Yooo ! programme accepte !");
            System.out.println();
            System.out.println();



            Grid grid = Grid.parseGrid(exeName, ioEnv.inGrid);


            Interpreter interp = new Interpreter2();
            new File("C:\\Users\\Chihab Eddine\\Desktop\\18_19__Mi\\S4\\ADS4\\gui_ads4\\src\\gui\\grilles").mkdirs();
            interp.run(prog, grid);
            ioEnv.outGrid.println(grid);

            int cpt=new File("C:\\Users\\Chihab Eddine\\Desktop\\18_19__Mi\\S4\\ADS4\\gui_ads4\\src\\gui\\grilles").list().length+1;
            PrintWriter writer=null;

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

        }
        catch (Exception e)
        {
            System.out.println(" programme non accepte !");
            e.printStackTrace();

        }


    }
}
