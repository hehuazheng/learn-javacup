package calc;

/**
 * Created by hejf on 2017/11/9.
 */
class Main {
    public static void main(String[] argv) throws Exception{
        System.out.println("Please type your arithmethic expression:");
        CalcParser p = new CalcParser(new scanner());
        p.parse();
    }
}