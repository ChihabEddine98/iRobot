public class ParserException extends Exception
{
    public ParserException(String text, String pos) {
        super(pos+text);
    }

}
