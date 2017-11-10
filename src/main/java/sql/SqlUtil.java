package sql;

/**
 * Created by hejf on 2017/11/10.
 */
public class SqlUtil {
    public static String normSelect(String sql) {
        if(sql != null) {
            sql = sql.trim().replaceAll("\\s+", " ");//多个空格合并
            StringBuilder sb = new StringBuilder("s");
            int i = 1;
            while(i < sql.length()) {
                char c = sql.charAt(i);
                if(c == '\\') {
                    if(i + 1 < sql.length()) {
                        char t = sql.charAt(i+1);
                        if(t != '\'') {
                            sb.append(c).append(t);
                        }
                        i += 2;
                    } else {
                        i++;
                    }
                } else {
                    sb.append(c);
                    i++;
                }
            }
            return sb.toString();
        }
        return sql;
    }
}
