package cn.jiakcs.mysql;

import cn.jiakcs.jucommon.mysql.JuMysqlEngine;
import cn.jiakcs.jucommon.mysql.model.JuDataRow;
import cn.jiakcs.jucommon.mysql.model.JuDataTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SourceDataTest {

    private JuMysqlEngine engine;

    @Before
    public void init(){
        engine = new JuMysqlEngine();
    }
    @Test
    public void readSourceData()
    {
        JuDataTable table = engine.selectTable("admin_name_suffix")
                .setLimit(10)
                .doSelect();

        assertTrue(table.count() > 0 );

        for(JuDataRow row : table.getRows()){
            System.out.println(row.getStringValue("suffix"));
        }

    }
}
