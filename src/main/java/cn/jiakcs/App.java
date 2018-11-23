package cn.jiakcs;

import cn.jiakcs.jucommon.mysql.JuMysqlEngine;
import cn.jiakcs.jucommon.mysql.model.JuDataRow;
import cn.jiakcs.jucommon.mysql.model.JuDataTable;

import java.sql.ResultSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("启动成功！");

        JuMysqlEngine engine = new JuMysqlEngine();

        boolean conn = engine.Connection("jdbc:mysql://10.10.241.101:3306/address?useUnicode=true&characterEncoding=utf8","address","uE1#$pg@T9f7x7vZbrVmLUKNFFYXpVcY");

        JuDataTable table = engine.selectTable("admin_name_suffix")
                .setLimit(10)
                .doSelect();

        for(JuDataRow row : table.getRows()){
            System.out.println(row.getStringValue("suffix"));
        }


        engine.Disconnection();
    }
}
