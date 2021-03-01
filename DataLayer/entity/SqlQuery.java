package entity;

import java.util.List;

public class SqlQuery {

    private final String query;

    private final List<Object> params;

    public SqlQuery(String query, List<Object> params) {
        this.query = query;
        this.params = params;
    }

    public String getQuery() {
        return query;
    }

    public List<Object> getParams() {
        return params;
    }
}