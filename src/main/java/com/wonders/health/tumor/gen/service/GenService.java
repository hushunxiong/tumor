package com.wonders.health.tumor.gen.service;

import com.wonders.health.tumor.gen.model.DbColumn;
import com.wonders.health.tumor.gen.utils.GenUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xuguobing on 2016/11/1 0001.
 */
public class GenService {

    private JdbcTemplate jdbcTemplate;

    public GenService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<DbColumn> findColumns(String dbName, String schema, String tableName) {
        if (GenUtils.ORACLE.equals(dbName)) {
            return findOracleCols(tableName);
        } else if (GenUtils.MYSQL.equals(dbName)) {
            return findMysqlCols(schema, tableName);
        } else if (GenUtils.SQLSERVER.equals(dbName)) {
            return findSqlserverCols(tableName);
        }
        throw new RuntimeException("数据库配置异常");
    }

    private List<DbColumn> findSqlserverCols(String tableName) {
        String sql = "SELECT t.COLUMN_NAME COLUMN_NAME,t.DATA_TYPE DATA_TYPE,t.CHARACTER_MAXIMUM_LENGTH LENGTH,t.IS_NULLABLE IS_NULLABLE,\n" +
                "CASE WHEN k.COLUMN_NAME IS NOT NULL THEN 'PRI' END AS COLUMN_KEY,'' COLUMN_COMMENT\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS t\n" +
                "LEFT JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE k\n" +
                "ON k.TABLE_NAME=? AND k.COLUMN_NAME=t.COLUMN_NAME\n" +
                "WHERE t.TABLE_NAME=?";
        return jdbcTemplate.query(sql, new DbColumnRowMapper(), tableName, tableName);
    }

    private List<DbColumn> findMysqlCols(String schema, String tableName) {
        String sql = "select COLUMN_NAME,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH LENGTH,IS_NULLABLE,COLUMN_KEY,COLUMN_COMMENT\n" +
                "from information_schema.COLUMNS where table_schema=? AND table_name=?";
        return jdbcTemplate.query(sql, new DbColumnRowMapper(), schema, tableName);
    }

    private List<DbColumn> findOracleCols(String tableName) {
        String sql = "select lower(t.column_name) COLUMN_NAME,t.data_type DATA_TYPE,t.data_length LENGTH,c.comments COLUMN_COMMENT,\n" +
                "    (SELECT CASE WHEN t.nullable='Y' THEN 'YES' ELSE 'NO' END FROM DUAL) IS_NULLABLE,\n" +
                "    (SELECT CASE WHEN EXISTS(select 1 from user_constraints s, user_cons_columns m\n" +
                "    where upper(m.table_name)=upper(?) and m.table_name=s.table_name\n" +
                "    and m.constraint_name=s.constraint_name and s.constraint_type='P' and m.column_name = t.column_name) THEN 'PRI' END FROM DUAL) COLUMN_KEY\n" +
                "    FROM user_tab_cols t, user_col_comments c\n" +
                "    WHERE upper(t.table_name)=upper(?)\n" +
                "    and c.table_name=t.table_name\n" +
                "    and c.column_name=t.column_name\n" +
                "    and t.hidden_column='NO'\n" +
                "    order by t.column_id";
        return jdbcTemplate.query(sql, new DbColumnRowMapper(), tableName, tableName);
    }

    private class DbColumnRowMapper implements RowMapper<DbColumn> {
        @Override
        public DbColumn mapRow(ResultSet rs, int rowNum) throws SQLException {
            DbColumn col = new DbColumn();
            col.setName(rs.getString("COLUMN_NAME"));
            col.setType(rs.getString("DATA_TYPE"));
            col.setLength(rs.getString("LENGTH"));
            col.setIsNull(rs.getString("IS_NULLABLE"));
            col.setColKey(rs.getString("COLUMN_KEY"));
            col.setComment(rs.getString("COLUMN_COMMENT"));
            return col;
        }
    }

}
