package partie1;


public class LexerException extends Exception {
    public LexerException(String text, int line, int column) {
        super("\nAt line "+line+", column "+column+"\nCan't make any token with "+text);
    }
}
