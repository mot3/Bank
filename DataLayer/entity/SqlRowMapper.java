package entity;

import java.sql.ResultSet;

public interface SqlRowMapper<T> {

    T mapTo(ResultSet resultSet);

}