package sql;

import java_cup.runtime.Symbol;
%%
%cup
%%
"select" { return new Symbol(SimpleSqlParserSym.SELECT);}
"as"   { return new Symbol(SimpleSqlParserSym.AS);}
"from" { return new Symbol(SimpleSqlParserSym.FROM);}
"inner" { return new Symbol(SimpleSqlParserSym.INNER);}
"left" { return new Symbol(SimpleSqlParserSym.LEFT);}
"right" { return new Symbol(SimpleSqlParserSym.RIGHT);}
"join"  { return new Symbol(SimpleSqlParserSym.JOIN); }
"where"   { return new Symbol(SimpleSqlParserSym.WHERE);}
"group"   { return new Symbol(SimpleSqlParserSym.GROUP);}
"by"   { return new Symbol(SimpleSqlParserSym.BY);}
"on"   { return new Symbol(SimpleSqlParserSym.ON);}
"order"   { return new Symbol(SimpleSqlParserSym.ORDER);}
"by"   { return new Symbol(SimpleSqlParserSym.BY);}
"as"   { return new Symbol(SimpleSqlParserSym.AS);}
"asc"   { return new Symbol(SimpleSqlParserSym.ASC);}
"desc"   { return new Symbol(SimpleSqlParserSym.DESC);}
"limit"  { return new Symbol(SimpleSqlParserSym.LIMIT); }
"="|">="|"<="|"!="    {  return new Symbol(SimpleSqlParserSym.OP); }
"("    {  return new Symbol(SimpleSqlParserSym.LPAREN); }
")"    {  return new Symbol(SimpleSqlParserSym.RPAREN); }
","    {  return new Symbol(SimpleSqlParserSym.COMMA); }
"count"|"sum"    {  return new Symbol(SimpleSqlParserSym.FUNC); }
[a-z0-9A-Z_\.]+ { return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
'[^']*' { return new Symbol(SimpleSqlParserSym.TOKEN, yytext()); }
[ \t\r\n\f] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }