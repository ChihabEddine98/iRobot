package partie1;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IOEnv
{
    public final Reader inProgram, inGrid;
    public final PrintWriter outGrid;

    private IOEnv(Reader inProgram, Reader inGrid,
                  PrintWriter outGrid) {
        this.inProgram = inProgram;
        this.inGrid = inGrid;
        this.outGrid = outGrid;
    }

    public static IOEnv parseArgs(String exeName, String[] args) {
        int nbArgs = args.length;
        switch (args.length) {
            case 0:
                System.err.println("java " + exeName + ": no program file given.");
                System.err.println(usage(exeName));
                System.exit(2);
                return null;
            case 1:
                return new IOEnv(
                           openInProgram(exeName, args[0]),
                           openStdIn(),
                           openStdOut());
            case 2:
                return new IOEnv(
                           openInProgram(exeName, args[0]),
                           openInGrid(exeName, args[1]),
                           openStdOut());
            case 3:
                return new IOEnv(
                           openInProgram(exeName, args[0]),
                           openInGrid(exeName, args[1]),
                           openOutGrid(exeName, args[2]));
            default:
                System.err.println("java " + exeName + ": too many arguments;\n"
                                   + "don't know what to do with " + args[3] + ".");
                System.err.println(usage(exeName));
                System.exit(2);
                return null;
        }
    }

    private static String usage(String exeName) {
        return
  "Usage: java " + exeName + " <program-file> <input-grid-file> <output-grid-file>\n"
+ "   or: java " + exeName + " <program-file> <input-grid-file>\n"
+ "   or: java " + exeName + " <program-file>\n"
+ "Run the program on the given grid and print the resulting grid in the given file.\n"
+ "If no output file is given, the resulting grid is printed on the standard output.\n"
+ "If no input grid file is given, the input grid is read on the standard input.";
    }

    private static Reader openInProgram(String exeName, String filename) {
        try {
            return new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("java " + exeName + ": program file not found: " + filename + ".");
            System.exit(2);
            return null;
        }
    }

    private static Reader openInGrid(String exeName, String filename) {
        try {
            return new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("java " + exeName + ": input grid file not found: " + filename + ".");
            System.exit(2);
            return null;
        }
    }

    private static PrintWriter openOutGrid(String exeName, String filename) {
        try {
            return new PrintWriter(
                       new BufferedWriter(new FileWriter(filename)),
                       true);
        } catch (IOException e) {
            System.err.println("java " + exeName + ": output error;\n"
                               + "cannot write the output in " + filename + ".");
            System.exit(2);
            return null;
        }
    }

    private static Reader openStdIn() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static PrintWriter openStdOut() {
        return new PrintWriter(
                   new BufferedWriter(new OutputStreamWriter(System.out)),
                   true);
    }
}
