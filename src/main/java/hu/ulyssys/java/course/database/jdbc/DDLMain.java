package hu.ulyssys.java.course.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DDLMain {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/demo";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "Admin123";

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", DATABASE_USER);
            properties.setProperty("password", DATABASE_PASSWORD);
            Connection connection = DriverManager.getConnection(DATABASE_URL, properties);
//            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "Admin123");
//            truncateDemo(connection);
//            dropTableDemo(connection);
//            createTableDemo(connection);
//            alterTableAddColumn(connection);
            createTableDemo(connection, "apple", "apple_name");
            createTableDemo(connection, "pear", "pear_name");
            createTableDemo(connection, "cherry", "cherry_name");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("Vége a programunknak");
        }
    }

    private static void truncateDemo(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        //truncate SQL parancs , kitöröljük az adress tábla minden elemét
        statement.execute("truncate adress");
    }

    private static void dropTableDemo(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        //tábla törlése
        statement.execute("drop table adress");
    }

    private static void createTableDemo(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        //Create table demo
        statement.execute("CREATE TABLE public.address\n" +
                "(\n" +
                "    id serial NOT NULL,\n" +
                "    employee_id integer NOT NULL,\n" +
                "    value character varying(255) COLLATE pg_catalog.\"default\",\n" +
                "    CONSTRAINT address_pkey PRIMARY KEY (id),\n" +
                "    CONSTRAINT employee_foreign_key FOREIGN KEY (employee_id)\n" +
                "        REFERENCES public.employee (id) MATCH SIMPLE\n" +
                "        ON UPDATE NO ACTION\n" +
                "        ON DELETE NO ACTION\n" +
                ")\n");
    }


    private static void createTableDemo(Connection connection, String tableName, String columnName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table " + tableName + "(id serial not null," + columnName +
                " character varying (255),constraint " + tableName + "_sequence primary key(id))");
    }

    private static void alterTableAddColumn(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("alter table address add test boolean");
    }

}
