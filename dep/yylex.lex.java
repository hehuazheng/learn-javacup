package sql;
import java_cup.runtime.Symbol;


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NOT_ACCEPT,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"29:9,30:2,29,30:2,29:18,30,23,29:5,28,24,25,29:2,26,29,27,29,27:10,29:2,23," +
"22,23,29:2,27:26,29:4,27,29,6,19,4,21,2,7,13,14,11,15,27,3,10,12,9,18,27,8," +
"1,5,17,27,16,27,20,27,29:5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,76,
"0,1,2,1,3,1:4,4,5:2,1,5:13,6,7,6,8,1,9,10,11,12,13,14,15,16,17,18,19,20,21," +
"22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,5,37,38,39,40,41,42,43,44,45,4" +
"6,47,48,49,50,51,52")[0];

	private int yy_nxt[][] = unpackFromString(53,31,
"1,2,59,64,66,59,27,68,69,29,59,70,59,71,59,72,73,59:2,31,59,74,3,4,5,6,7,59" +
",28,30,8,-1:32,59,75,59:14,32,59:4,-1:5,59,-1:25,3,-1:9,59:3,14,59:17,-1:5," +
"59,-1:4,59:21,-1:5,59,-1:4,26:27,12,26:2,-1,9,59:20,-1:5,59,-1:4,59:7,38,59" +
":3,10,59:9,-1:5,59,-1:4,59:19,11,59,-1:5,59,-1:4,59:9,13,59:11,-1:5,59,-1:4" +
",59:6,43,59:14,-1:5,59,-1:4,59:9,62,59:11,-1:5,59,-1:4,59:16,61,59:4,-1:5,5" +
"9,-1:4,59:8,44,59:12,-1:5,59,-1:4,59:12,45,59:8,-1:5,59,-1:4,59:20,65,-1:5," +
"59,-1:4,59:11,67,59:9,-1:5,59,-1:4,59:10,47,59:10,-1:5,59,-1:4,59,48,59:19," +
"-1:5,59,-1:4,49,59:20,-1:5,59,-1:4,59:4,15,59:16,-1:5,59,-1:4,59:9,16,59:11" +
",-1:5,59,-1:4,59:13,53,59:7,-1:5,59,-1:4,59:16,56,59:4,-1:5,59,-1:4,59:11,1" +
"7,59:9,-1:5,59,-1:4,59:7,57,59:13,-1:5,59,-1:4,59:3,18,59:17,-1:5,59,-1:4,5" +
"9:3,58,59:17,-1:5,59,-1:4,59:4,19,59:16,-1:5,59,-1:4,59:4,13,59:16,-1:5,59," +
"-1:4,59:4,20,59:16,-1:5,59,-1:4,59:7,21,59:13,-1:5,59,-1:4,59:7,22,59:13,-1" +
":5,59,-1:4,59:17,23,59:3,-1:5,59,-1:4,59,24,59:19,-1:5,59,-1:4,59:4,25,59:1" +
"6,-1:5,59,-1:4,59:8,46,59:12,-1:5,59,-1:4,59:11,52,59:9,-1:5,59,-1:4,59:10," +
"51,59:10,-1:5,59,-1:4,59,50,59:19,-1:5,59,-1:4,59,33,59:8,34,59:10,-1:5,59," +
"-1:4,59,54,59:19,-1:5,59,-1:4,59:8,35,59:12,-1:5,59,-1:4,59,55,59:19,-1:5,5" +
"9,-1:4,59:7,36,59:13,-1:5,59,-1:4,59:10,37,59:10,-1:5,59,-1:4,59:11,39,59:9" +
",-1:5,59,-1:4,59:7,60,59:13,-1:5,59,-1:4,59:8,40,59:12,-1:5,59,-1:4,59:13,4" +
"1,59:7,-1:5,59,-1:4,59,42,59:19,-1:5,59,-1:4,59:2,63,59:18,-1:5,59,-1:3");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -3:
						break;
					case 3:
						{  return new Symbol(SimpleSqlParserSym.OP); }
					case -4:
						break;
					case 4:
						{ System.err.println("Illegal character: "+yytext()); }
					case -5:
						break;
					case 5:
						{  return new Symbol(SimpleSqlParserSym.LPAREN); }
					case -6:
						break;
					case 6:
						{  return new Symbol(SimpleSqlParserSym.RPAREN); }
					case -7:
						break;
					case 7:
						{  return new Symbol(SimpleSqlParserSym.COMMA); }
					case -8:
						break;
					case 8:
						{ /* ignore white space. */ }
					case -9:
						break;
					case 9:
						{ return new Symbol(SimpleSqlParserSym.AS);}
					case -10:
						break;
					case 10:
						{ return new Symbol(SimpleSqlParserSym.ON);}
					case -11:
						break;
					case 11:
						{ return new Symbol(SimpleSqlParserSym.BY);}
					case -12:
						break;
					case 12:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -13:
						break;
					case 13:
						{  return new Symbol(SimpleSqlParserSym.FUNC); }
					case -14:
						break;
					case 14:
						{ return new Symbol(SimpleSqlParserSym.ASC);}
					case -15:
						break;
					case 15:
						{ return new Symbol(SimpleSqlParserSym.LEFT);}
					case -16:
						break;
					case 16:
						{ return new Symbol(SimpleSqlParserSym.FROM);}
					case -17:
						break;
					case 17:
						{ return new Symbol(SimpleSqlParserSym.JOIN); }
					case -18:
						break;
					case 18:
						{ return new Symbol(SimpleSqlParserSym.DESC);}
					case -19:
						break;
					case 19:
						{ return new Symbol(SimpleSqlParserSym.LIMIT); }
					case -20:
						break;
					case 20:
						{ return new Symbol(SimpleSqlParserSym.RIGHT);}
					case -21:
						break;
					case 21:
						{ return new Symbol(SimpleSqlParserSym.ORDER);}
					case -22:
						break;
					case 22:
						{ return new Symbol(SimpleSqlParserSym.INNER);}
					case -23:
						break;
					case 23:
						{ return new Symbol(SimpleSqlParserSym.GROUP);}
					case -24:
						break;
					case 24:
						{ return new Symbol(SimpleSqlParserSym.WHERE);}
					case -25:
						break;
					case 25:
						{ return new Symbol(SimpleSqlParserSym.SELECT);}
					case -26:
						break;
					case 27:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -27:
						break;
					case 28:
						{ System.err.println("Illegal character: "+yytext()); }
					case -28:
						break;
					case 29:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -29:
						break;
					case 30:
						{ System.err.println("Illegal character: "+yytext()); }
					case -30:
						break;
					case 31:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -31:
						break;
					case 32:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -32:
						break;
					case 33:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -33:
						break;
					case 34:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -34:
						break;
					case 35:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -35:
						break;
					case 36:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -36:
						break;
					case 37:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -37:
						break;
					case 38:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -38:
						break;
					case 39:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -39:
						break;
					case 40:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -40:
						break;
					case 41:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -41:
						break;
					case 42:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -42:
						break;
					case 43:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -43:
						break;
					case 44:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -44:
						break;
					case 45:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -45:
						break;
					case 46:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -46:
						break;
					case 47:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -47:
						break;
					case 48:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -48:
						break;
					case 49:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -49:
						break;
					case 50:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -50:
						break;
					case 51:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -51:
						break;
					case 52:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -52:
						break;
					case 53:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -53:
						break;
					case 54:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -54:
						break;
					case 55:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -55:
						break;
					case 56:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -56:
						break;
					case 57:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -57:
						break;
					case 58:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -58:
						break;
					case 59:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -59:
						break;
					case 60:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -60:
						break;
					case 61:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -61:
						break;
					case 62:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -62:
						break;
					case 63:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -63:
						break;
					case 64:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -64:
						break;
					case 65:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -65:
						break;
					case 66:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -66:
						break;
					case 67:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -67:
						break;
					case 68:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -68:
						break;
					case 69:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -69:
						break;
					case 70:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -70:
						break;
					case 71:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -71:
						break;
					case 72:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -72:
						break;
					case 73:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -73:
						break;
					case 74:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -74:
						break;
					case 75:
						{ return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
					case -75:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
