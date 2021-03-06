%%
%public
%class Lexer
%unicode
%type Token
%line
%column
%debug


%{
  private Token token(Sym type) {
      return new Token(type);
  }
  
  private IntToken token(Sym type,int value) {
      return new IntToken(type,value);
  }

  private StringToken token(Sym type, String value) {
      return new StringToken(type,value);
  }


  public String getPosition()
  {
        return "Problem dans la ligne "+yyline+" colonne : "+yycolumn;
  }
%}

%yylexthrow{
Exception, LexerException
%yylexthrow}

%eofval{
  return token(Sym.EOF);
%eofval}

nombre=-?[1-9][0-9]*|0
finDeMot=[ \n\t\r\f]

%%

"{"   {return token(Sym.LACC);}
"}"   {return token(Sym.RACC);}
"Si"  {return token(Sym.SI);}
"Alors" {return token(Sym.ALORS);}
"Fin"   {return token(Sym.FIN);}
"TantQue" {return token(Sym.TANTQUE);}
"Faire" {return token(Sym.FAIRE);}
"Et" {return token(Sym.ET);}
"Ou" {return token(Sym.OU);}
"<"  {return token(Sym.INF);}
">"   {return token(Sym.SUP);}
"=" {return token(Sym.EQUAL);}
"("   {return token(Sym.LPAR);}
")"   {return token(Sym.RPAR);}
"+"   {return token(Sym.PLUS);}
"-"   {return token(Sym.MOINS);    }
";"   {return token(Sym.SEMICOL);}
"Lire" {return token(Sym.LIRE);}
"Tourner" {return token(Sym.TOURNER);}
"Avancer" {return token(Sym.AVANCER);}
"Ecrire" {return token(Sym.ECRIRE);}
{nombre} { return token(Sym.INT,Integer.parseInt(yytext()));}
{finDeMot} { }
[^] {throw new LexerException(yytext(),yyline,yycolumn); }