package partie1;






class Main
{
    public static void main(String[] args) throws Exception {
        String exeName = "Main";
        IOEnv ioEnv = IOEnv.parseArgs(exeName, args);
        Lexer lexer = new Lexer(ioEnv.inProgram);
        LookAhead1 look = new LookAhead1(lexer);
        Parser parser = new Parser1(look);

        try {
            Program prog = parser.parseProgram(exeName, ioEnv.inProgram);
            System.out.println(" programme accepte !");
            System.out.println(prog);

            Grid grid = Grid.parseGrid(exeName, ioEnv.inGrid);
            System.out.println(" grid ok!");

            Interpreter interp = new StupidInterpreter();
            interp.run(prog, grid);
            ioEnv.outGrid.println(grid);

        }
        catch (Exception e)
        {
            System.out.println(" programme non accepté !");

        }


    }
}
