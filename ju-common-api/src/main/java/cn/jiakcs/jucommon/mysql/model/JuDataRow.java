package cn.jiakcs.jucommon.mysql.model;

import java.util.*;

public class JuDataRow {
    private Map<String, Object> cols;

    public JuDataRow() {
        cols = new HashMap<>();
    }

    public void setValue(String name, Object value) {
        cols.put(name, value);
    }

    public Object getValue(String name) {
        return cols.getOrDefault(name, null);
    }

    public Object getStringValue(String name) {
        return getValue(name).toString();
    }

}
