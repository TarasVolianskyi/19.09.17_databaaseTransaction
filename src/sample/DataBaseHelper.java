package sample;

import java.sql.*;

public class DataBaseHelper {
    public static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_PATH = "jdbc:mysql://localhost/dbTransaction?user=root&password=fr43edsw21qa";
    public static final String TABLE_NAME = "myTable";

    public Statement openConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DATABASE_DRIVER);
        Connection myConnection = (Connection) DriverManager.getConnection(DATABASE_PATH);
        return (Statement) myConnection.createStatement();
    }

    public PreparedStatement openPreparedConnection(String qwery) throws ClassNotFoundException, SQLException {
        Class.forName(DATABASE_DRIVER);
        Connection myConnection = (Connection) DriverManager.getConnection(DATABASE_PATH);
        return (PreparedStatement) myConnection.prepareStatement(qwery);
    }

    public PreparedStatement closePreparedConnection(String qwery) throws ClassNotFoundException, SQLException {
        Class.forName(DATABASE_DRIVER);
        Connection myConnection = (Connection) DriverManager.getConnection(DATABASE_PATH);
        return (PreparedStatement) myConnection.prepareStatement(qwery);
        // (PreparedStatement) myConnection.close();
    }


    public void insert(String name, String pass) {
        String qwery = String.format("insert INTO %s (name, pass, loginDate) values ('%s','%s',NOW())",
                TABLE_NAME, name, pass);
        makeTransaction(qwery);
    }

    public void preparedInseert(String name, String pass) {
        String qwery = "insert INTO myTable (name, pass, loginDate) values (?,?, now())";
        try {
            PreparedStatement preparedStatement = openPreparedConnection(qwery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String qwery = String.format("delete from %s where id = %d ", TABLE_NAME, id);
        makeTransaction(qwery);
    }

    public void deleteAll() {
        String qwery = String.format("delete from %s ", TABLE_NAME);
        makeTransaction(qwery);
    }

    public void deleteWithLimit(int fromID, int toID) {
        String qwery = String.format("delete from %s where id>%d and id<%d", TABLE_NAME, fromID, toID);
        makeTransaction(qwery);
    }

    public void update(String name, String pass, int id) {
        String qwery = "update ? set name=?, pass=? where id=?";
        //makeTransaction(qwery);
        try {
            PreparedStatement preparedStatement = openPreparedConnection(qwery);
            preparedStatement.setString(1, TABLE_NAME);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, pass);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void printAll() {
        String qwery = String.format("select * from  %s", TABLE_NAME);
        try {
            Statement statement = openConnection();
            ResultSet resultSet = statement.executeQuery(qwery);//save all data from table
            while (resultSet.next()) {
                User myUser = new User();
                myUser.setId(resultSet.getInt("id"));
                myUser.setName(resultSet.getString("name"));
                myUser.setPass(resultSet.getString("pass"));
                myUser.setLoginDate(resultSet.getString("loginDate"));
                System.out.println(myUser);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printSomePart(int fromID, int toId) {
        String qwery = String.format("select * from  %s where id>%d and id<%d", TABLE_NAME,fromID,toId);
        try {
            Statement statement = openConnection();
            ResultSet resultSet = statement.executeQuery(qwery);//save all data from table
            while (resultSet.next()) {
                User myUser = new User();
                myUser.setId(resultSet.getInt("id"));
                myUser.setName(resultSet.getString("name"));
                myUser.setPass(resultSet.getString("pass"));
                myUser.setLoginDate(resultSet.getString("loginDate"));
                System.out.println(myUser);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeTransaction(String qwery) {
        try {
            Statement statement = openConnection();
            statement.execute(qwery);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}