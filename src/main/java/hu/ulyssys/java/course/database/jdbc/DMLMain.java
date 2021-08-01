package hu.ulyssys.java.course.database.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DMLMain {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/demo";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "Admin123";

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", DATABASE_USER);
            properties.setProperty("password", DATABASE_PASSWORD);
            Connection connection = DriverManager.getConnection(DATABASE_URL, properties);
//            insertInto(connection);
//            delete(connection);
//            update(connection);
//            selectIdDemo(connection);
            selectIdDemo(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("Vége a programunknak");
        }
    }

    private static void insertInto(Connection connection) throws SQLException {
        //insert into parancsot a dog_owner táblába
//        Statement statement = connection.createStatement();
//        statement.execute("insert into dog_owner(first_name,last_name) values ('alma','körte')");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into dog_owner(first_name,last_name)" +
                " values (?,?)");
        preparedStatement.setString(1, "körte");
        preparedStatement.setString(2, "cseresznye");
        preparedStatement.execute();
    }

    private static void delete(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from dog_owner where first_name=?");
        preparedStatement.setString(1, "alma");
        preparedStatement.execute();
    }

    private static void update(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update dog_owner set first_name=? where first_name=?");
        preparedStatement.setString(1, "Adrián" + System.currentTimeMillis());
        preparedStatement.setString(2, "Adrián");
        preparedStatement.execute();
    }

    private static void selectIdDemo(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from dog_owner where first_name=?");
        preparedStatement.setString(1, "Adrián");
        ResultSet resultSet = preparedStatement.executeQuery();
        Long id = null;
        while (resultSet.next()) {
            id = resultSet.getLong("id");
            selectDogDemo(connection, id);
        }
    }

    private static void selectDogDemo(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from dog where owner_id=?");
        preparedStatement.setLong(1, id);
        List<Dog> dogList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Dog dog = new Dog();
            dog.setId(resultSet.getLong("id"));
            dog.setColor(resultSet.getString("color"));
            dog.setName(resultSet.getString("name"));
            dog.setSpecies(resultSet.getString("species"));

            dogList.add(dog);
        }

        for (Dog dog : dogList) {
            System.out.println(dog);
        }
    }
}
