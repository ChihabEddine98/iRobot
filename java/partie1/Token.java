package partie1;


class Token {
    protected Sym symbol;
    public Token(Sym s) {
	symbol=s;
    }
    public Sym symbol() {
	return symbol;
    }

    public String value(){
		return "1998";
	}
}   

class IntToken extends Token {
	protected int value ; 
	
	public IntToken (Sym s , int v){
		super(s);
		value=v;
	}

	public String value(){
		return ""+value;
	}
   
}

class StringToken extends Token {
	protected String value ; 
	
	public StringToken (Sym s , String v){
		super(s);
		value=v;
	}

	public String value(){
		return value;
	}
   
}



