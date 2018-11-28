package cn.jiakcs.jucommon.mysql.model;

import java.util.ArrayList;
import java.util.List;

public class JuDataTable {
    private Integer juPos;
    private List<JuDataRow> juRows;

    public JuDataTable() {
        juPos = -1;
        juRows = new ArrayList<>();
    }

    public List<JuDataRow> getRows(){
        return juRows;
    }

    public void add(JuDataRow row) {
        juRows.add(row);
    }

    public Integer count(){
        return juRows.size();
    }
}
