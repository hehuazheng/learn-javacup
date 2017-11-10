package sql; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* SqlUtil Tester. 
* 
* @author <Authors name> 
*/ 
public class SqlUtilTest { 
    @Test
    public void t1() {

    }

    @Test
    public void testNormSql() {
        String sql = "select a from b where b.name='ab\\' d'";
        System.out.println(SqlUtil.normSelect(sql));
    }
} 
