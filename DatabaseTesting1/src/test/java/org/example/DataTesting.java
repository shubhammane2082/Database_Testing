package org.example;

import org.testng.annotations.Test;

import java.sql.*;

public class DataTesting extends Myconnection
{
    Statement statement=null;
    Connection connection=null;
    PreparedStatement preparedStatement=null;

    CallableStatement callableStatement=null;

    @Test(priority = 1)
    public void createTable() throws SQLException
    {
        connection = createConnection();
        statement= connection.createStatement();
        java.lang.String query = "create table Employee(\n" +
                "ID int primary key auto_increment,\n" +
                "Emp_Name varchar(20) not null,\n" +
                "Emp_Department varchar(20) not null,\n" +
                "age int,\n" +
                "check (age >= 18))";

        statement.executeUpdate(query);
        System.out.println("Table Created Successfully...");
    }

    @Test(priority = 2)
    public void insertData() throws SQLException {
        connection=createConnection();
        java.lang.String query="insert into employee (Emp_Name, Emp_Department, age) values (?, ?, ?)";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,"Ryan");
        preparedStatement.setString(2,"UX");
        preparedStatement.setInt(3,23);

        preparedStatement.executeUpdate();

        System.out.println("Data Inserted Successfully...");
        getAll();
    }

    @Test()
    public void updateData() throws SQLException {
        String query="update employee set age=?,Emp_name=? where ID=?";
        connection=createConnection();
        preparedStatement=connection.prepareStatement(query);

        preparedStatement.setInt(1,25);
        preparedStatement.setString(2,"Jos");
        preparedStatement.setInt(3,3);

        preparedStatement.executeUpdate();
        System.out.println("Data Updated successfully...");
    }

    @Test
    public void getAll() throws SQLException {
        connection  = createConnection();
        statement=connection.createStatement();
        String query="select * from employee";
        ResultSet rs=statement.executeQuery(query);

        while (rs.next())
        {
//            Emp_Name, Emp_Department, age
            System.out.println("Id : "+rs.getInt(1)+", Emp_Name : "+rs.getString(2)+", Emp_Department : "+rs.getString(3)+", Age is : "+rs.getInt(4)+"\n");
        }
        System.out.println("Data Fetch successfully...");
    }

    @Test
    public void deleteData() throws SQLException {
        connection=createConnection();
        String query="delete from employee where ID=?";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,4);

        preparedStatement.executeUpdate();
        System.out.println("Data Deleted successfully...");
    }

    @Test
    public void getById() throws SQLException {
        connection=createConnection();
        String query="select * from employee where Id=?";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,3);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            System.out.println("Id : "+resultSet.getInt(1)+",Name is : "+resultSet.getString(2)
                    +",Department is : "+resultSet.getString(3)+",Age is : "+resultSet.getInt(4));
        }
    }
    @Test
    public void callProcedure() throws SQLException {
        connection=createConnection();
        String query="call getAll";
        callableStatement=connection.prepareCall(query);
        ResultSet resultSet = callableStatement.executeQuery();

        while (resultSet.next())
        {
            System.out.println("Id : "+resultSet.getInt(1)+",Name :"+resultSet.getString(2)+",Department :"+resultSet.getString(3)+",Age : "+resultSet.getInt(4)+"\n");
        }
    }

    @Test
    public void ProcedurewithParameter() throws SQLException {
        connection=createConnection();
        String query="{call getAllParameter(?)}";
        callableStatement=connection.prepareCall(query);
        callableStatement.setString(1,"Jos");

        ResultSet resultSet = callableStatement.executeQuery();

        while ((resultSet.next()))
        {
            System.out.println("Id :"+resultSet.getInt(1)+",Name :"+resultSet.getString(2)+",Department :"+resultSet.getString(3)+",Age :"+resultSet.getInt(4));
        }
    }
//    @Test(priority = 7)
//    public void dropTable() throws SQLException
//    {
//        connection= createConnection();
//        statement=connection.createStatement();
//        statement.executeUpdate("drop table Employee");
//
//        System.out.println("Table drop successfully...");
//    }
}
