package sql;

import java_cup.runtime.Symbol;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Yylex Tester.
 *
 * @author <Authors name>
 */
public class YylexTest {
    @Test
    public void t0() throws Exception {
        StringReader sr = new StringReader("select column1 as c1 from test_b as b1");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t1() throws Exception {
        StringReader sr = new StringReader("select column1 as c1 from test_b as b1 join test_c as c1 on a=a");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t11() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a=a");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }
    @Test
    public void t12() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a>=a");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }
    @Test
    public void t13() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a<=a");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }
    @Test
    public void t14() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a!=a");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t21() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a=a group by column1");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t22() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a=a group by column1,column2");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t31() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a=a group by column1,column2 order by column1");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t32() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a=a group by column1,column2 order by column1 asc");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t33() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a=a group by column1,column2 order by column1 desc");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t34() throws Exception {
        StringReader sr = new StringReader("select column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 join test_c as c1 on a=a group by column1,column2 order by column1 desc,column2 asc");
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t41() throws Exception {
        StringReader sr = new StringReader(SqlUtil.normSelect("select b1.column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 where b1.n='aaa' group by column1,column2"));
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t42() throws Exception {
        StringReader sr = new StringReader(SqlUtil.normSelect("select b1.column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 where b1.n='aa\\'a' group by column1,column2"));
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t5() throws Exception {
        String sql = SqlUtil.normSelect("select b1.column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 where b1.n='aa\\'a' group by column1,column2 limit 10");
        StringReader sr = new StringReader(sql);
        SimpleSqlParser p = new SimpleSqlParser(new Yylex(sr));
        p.parse();
        sr.close();

        System.out.println(p.getTokenList());
        System.out.println(p.getFromList());
    }

    @Test
    public void t7() throws Exception {
        String sql = SqlUtil.normSelect("select b1.column1 as c1, count(column2) as c2, sum(column3) from test_b as b1 where b1.n='aa\\'a' group by column1,column2 limit 10");
        StringReader sr = new StringReader(sql);
        Yylex yylex = new Yylex(sr);
        int cnt = 0;
        while (cnt++ < 100) {
            Symbol symbol = yylex.next_token();
//            if (symbol.sym == SimpleSqlParserSym.EOF) {
//                break;
//            }
            System.out.println(cnt + ", " + SimpleSqlParserSym.terminalNames[symbol.sym]  + " " + symbol.value);
            if (cnt++ > 100) {
                break;
            }
        }
        sr.close();
    }

    @Test
    public void testRegexp() {
        String pattern = "'[^\']+'$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher("b'ab\'c'");
        if(m.matches()) {
            System.out.println("match");
        } else {
            System.out.println("not match");
        }
    }

} 
