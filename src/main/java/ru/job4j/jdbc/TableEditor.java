package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public static void main(String[] args) throws Exception {
        String tableName = "test_table";
        String columnName = "name";
        String columnType = "VARCHAR(50)";
        String newColumnName = "full_name";

        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable(tableName);
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.addColumn(tableName, columnName, columnType);
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.renameColumn(tableName, columnName, newColumnName);
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.dropColumn(tableName, newColumnName);
        System.out.println(tableEditor.getTableScheme(tableName));
        tableEditor.dropTable(tableName);
    }

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try (InputStream input = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(input);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        executiveStatement(String.format(
                "CREATE TABLE IF NOT EXISTS %s()",
                tableName
        ));
        System.out.println("Create table");
    }

    public void dropTable(String tableName) {
        executiveStatement(String.format(
                "DROP TABLE %s",
                tableName
        ));
        System.out.println("Drop table");
    }

    public void addColumn(String tableName, String columnName, String type) {
        executiveStatement(String.format(
                "ALTER TABLE %s ADD COLUMN %s %s",
                tableName,
                columnName,
                type
        ));
        System.out.println("Add column");
    }

    public void dropColumn(String tableName, String columnName) {
        executiveStatement(String.format(
                "ALTER TABLE %s DROP COLUMN %s",
                tableName,
                columnName
        ));
        System.out.println("Drop column");
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executiveStatement(String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName,
                columnName,
                newColumnName
        ));
        System.out.println("Rename column");
    }

    private void executiveStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
