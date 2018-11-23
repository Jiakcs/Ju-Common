package cn.jiakcs.jucommon.mysql;

import cn.jiakcs.jucommon.julog.JuLogUtil;
import cn.jiakcs.jucommon.mysql.model.DbCommand;
import cn.jiakcs.jucommon.mysql.model.JuDataRow;
import cn.jiakcs.jucommon.mysql.model.JuDataTable;
import cn.jiakcs.jucommon.mysql.util.JuJdbc;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JuMysqlEngine {
    private JuJdbc juJdbc;
    private DbCommand dbCommand;

    public JuMysqlEngine() {
        juJdbc = new JuJdbc();
        dbCommand = new DbCommand();
    }

    /**
     * 初始化数据库连接
     */
    public Boolean Connection(String url, String user, String pwd) {
        return Connection(url, user, pwd, "com.mysql.jdbc.Driver");
    }

    public Boolean Connection(String url, String user, String pwd, String driver) {
        return juJdbc.Connection(url, user, pwd, driver);
    }

    /**
     * 关闭连接
     */
    public void Disconnection() {
        juJdbc.Disconnection();
    }

    public JuMysqlEngine selectTable(String tableName){
        dbCommand = new DbCommand();
        dbCommand.setJuTableName(tableName);
        return this;
    }

    public JuMysqlEngine setLimit(Integer limit){
        dbCommand.setJuLimit(limit);
        return this;
    }

    public JuMysqlEngine addField(String fieldName){
        dbCommand.addFields(fieldName);
        return this;
    }


    public JuDataTable doSelect(){
        JuDataTable table = new JuDataTable();

        String sql = dbCommand.generateSql();
        ResultSet resultSet = juJdbc.GetResultSet(sql);
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                JuDataRow row = new JuDataRow();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String colName = metaData.getColumnName(i + 1);
                    Object colValue = resultSet.getObject(i + 1);
                    row.setValue(colName, colValue);
                }
                table.add(row);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return table;
    }
}
