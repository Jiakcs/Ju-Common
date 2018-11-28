package cn.jiakcs.jucommon.mysql.model;

import java.util.ArrayList;
import java.util.List;

public class DbCommand {

    public String juTableName;
    public List<String> juFields;
    public List<String> juWhere;
    public List<String> juOrderBy;
    public List<String> juGroupBy;
    public List<String> juJoinTable;
    public Integer juLimit;

    public DbCommand() {
        juTableName = "";
        juFields = new ArrayList<>();
        juWhere = new ArrayList<>();
        juOrderBy = new ArrayList<>();
        juGroupBy = new ArrayList<>();
        juJoinTable = new ArrayList<>();

        juLimit = 0;
    }


    public String getJuTableName() {
        return juTableName;
    }

    public void setJuTableName(String juTableName) {
        this.juTableName = juTableName;
    }

    public void setJuLimit(Integer juLimit) {
        this.juLimit = juLimit;
    }

    public Integer getJuLimit() {
        return juLimit;
    }

    public void addFields(String value){
        juFields.add(value);
    }

    public String getFields(){
        if (juFields.isEmpty())
            return "*";
        return String.join(",", juFields);
    }

    public void addWhere(String value){
        juWhere.add(value);
    }
    public void addOrderBy(String value){
        juOrderBy.add(value);
    }
    public void addGroupBy(String value){
        juGroupBy.add(value);
    }
    public void addJoinTable(String value){
        juJoinTable.add(value);
    }

    public String generateSql(){
        StringBuilder sb = new StringBuilder();

        sb.append("select ");
        sb.append(getFields());
        sb.append(" from ");
        sb.append(juTableName);

        if (juLimit > 0) {
            sb.append(" limit " + juLimit.toString());
        }
        return sb.toString();
//        String SqlCommand = "";
//        SqlCommand = "select " + ;
//        if(SqlFields.size() < 1) SqlFields.add("*");
//
//        String strFields = "";
//        for(String one : SqlFields){
//            if (strFields.length() > 0)
//                strFields += ("," + one);
//            else
//                strFields += one;
//        }
//
//        SqlCommand += strFields + " from ";
//        for(String one : SqlTableName) SqlCommand += one;
//        // 添加联表关系
//        for(String one : SqlJoin) SqlCommand += one;
//
//        if ( SqlWhere.size() > 0 ){
//            SqlCommand += " where ";
//            for(String one : SqlWhere) SqlCommand += (one + " ");
//        }
//        if ( SqlOrderBy.size() > 0 ){
//            SqlCommand += " order by ";
//            for(String one : SqlOrderBy) SqlCommand += one;
//        }
//        if ( SqlGroupBy.size() > 0 ){
//            SqlCommand += " group by ";
//            for(String one : SqlGroupBy) SqlCommand += one;
//        }
//
//        if ( SqlPage.length() > 0 ){
//            SqlCommand += SqlPage;
//        }
//
//        return SqlCommand;
    }

}
