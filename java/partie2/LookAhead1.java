class LookAhead1 {
    /* Simulating a reader class for a stream of Token2 */
    
    public Token current;
    public Lexer lexer;

    public LookAhead1(Lexer l)
            throws Exception {
	lexer=l;
	current=lexer.yylex();
    }



    public boolean check(Sym s)
	throws Exception {
	/* check whether the first character is of type s*/
          return (current.symbol() == s); 
    }

    public void eat(Sym s)
	throws Exception {
	/* consumes a token of type s from the stream,
	   exception when the contents does not start on s.   */
	if (!check(s)) {
	    throw new Exception("\nCan't eat "+s+" current being "+current);
	}
          current=lexer.yylex();

    }
    public boolean isEmpty()
	{
		return current==null;
	}

    public int getIntValue ()
            throws Exception {
        /* returns the value of an integer token */
        if(current.symbol()== Sym.INT)
            return ((IntToken)current).value();
        else
            throw new Exception("Erreur");
    }

    // à compléter

}
