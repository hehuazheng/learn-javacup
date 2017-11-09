package calc;

import java_cup.runtime.*;

public class scanner {
    /* single lookahead character */
    protected static int next_char;

    /* we use a SymbolFactory to generate Symbols */
    private SymbolFactory sf = new DefaultSymbolFactory();

    /* advance input by one character */
    protected void advance() throws java.io.IOException
    { next_char = System.in.read(); }

    /* initialize the scanner */
    public void init() throws java.io.IOException
    { advance(); }

    /* recognize and return the next complete token */
    public Symbol next_token() throws java.io.IOException
    {
        for (;;)
            switch (next_char)
            {
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':
		    /* parse a decimal integer */
                int i_val = 0;
                do {
                    i_val = i_val * 10 + (next_char - '0');
                    advance();
                } while (next_char >= '0' && next_char <= '9');
                return sf.newSymbol("NUMBER", CalcParserSym.NUMBER, new Integer(i_val));

                case ';': advance(); return sf.newSymbol("SEMI", CalcParserSym.SEMI);
                case '+': advance(); return sf.newSymbol("PLUS", CalcParserSym.PLUS);
                case '-': advance(); return sf.newSymbol("MINUS", CalcParserSym.MINUS);
                case '*': advance(); return sf.newSymbol("TIMES", CalcParserSym.TIMES);
                case '(': advance(); return sf.newSymbol("LPAREN", CalcParserSym.LPAREN);
                case ')': advance(); return sf.newSymbol("RPAREN", CalcParserSym.RPAREN);

                case -1: return sf.newSymbol("EOF", CalcParserSym.EOF);

                default:
		    /* in this simple scanner we just ignore everything else */
                    advance();
                    break;
            }
    }
};