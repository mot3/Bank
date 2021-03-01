package entity;

import java.util.List;

public class SqlFinderQuery<T> extends SqlQuery {

    private final SqlRowMapper<T> rowMapper;
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SqlFinderQuery(String query, List<Object> param, SqlRowMapper viewUserSqlRowMapper) {

        super(query, param);
        this.rowMapper = viewUserSqlRowMapper;
	}

	public SqlRowMapper<T> getRowMapper() {
        return rowMapper;
    }
}