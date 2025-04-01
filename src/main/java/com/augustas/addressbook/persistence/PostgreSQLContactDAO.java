package com.augustas.addressbook.persistence;

import com.augustas.addressbook.model.Contact;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLContactDAO implements ContactDAO{

    @Override
    public boolean createContact(Contact contact){

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            //Get the connection from connection manager
            connection = DBConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO contact_information (first_name, last_name, phone_number,  email_address) VALUES (?,?,?,?)";

            statement = connection.prepareStatement(sql);

            // Set parameters for the prepared statement
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhoneNumber());
            statement.setString(4, contact.getEmailAddress());

            // Execute the statement and get number of affected rows
            int rowsAffected = statement.executeUpdate();

            // If at least one row was affected, the insertion was successful
            return rowsAffected > 0;

        } catch(SQLException e){
            System.err.println("Error creating contact: " + e.getMessage());
            return false;
        } finally{
            // Close resources to prevent leaks
            DBConnectionManager.getInstance().closeResources(statement, connection);
        }

    }

    //Read from db
    @Override
    public List<Contact> getContactByFirstName(String firstName){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Contact> results = new ArrayList<>();

        try{

            connection = DBConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM contact_information WHERE first_name = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);

            // Execute the statement and get number of affected rows
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setEmailAddress(resultSet.getString("email_address"));

                results.add(contact);
            }

            return results;

        }catch (SQLException e){
            System.err.println("Error finding contacts by first name: " + e.getMessage());
            return results;
        }finally{
            DBConnectionManager.getInstance().closeResources(resultSet, statement, connection);
        }
    }

    @Override
    public List<Contact> getAllContacts (){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Contact> results = new ArrayList<>();

        try{

            connection = DBConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM contact_information";

            statement = connection.prepareStatement(sql);

            // Execute the statement and get number of affected rows
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setEmailAddress(resultSet.getString("email_address"));

                results.add(contact);
            }

            return results;

        }catch (SQLException e){
            System.err.println("Error finding contacts: " + e.getMessage());
            return results;
        }finally{
            DBConnectionManager.getInstance().closeResources(resultSet, statement, connection);
        }
    }

    @Override
    public boolean updateContact(Contact contact){

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DBConnectionManager.getInstance().getConnection();

            String sql = "UPDATE contact_information SET first_name = ?, last_name = ?, phone_number = ?, email_address = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

            statement = connection.prepareStatement(sql);

            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhoneNumber());
            statement.setString(4, contact.getEmailAddress());
            statement.setInt(5, contact.getId());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        }catch(SQLException e){
            System.err.println("Error updating contact: " + e.getMessage());
            return false;
        }finally{
            DBConnectionManager.getInstance().closeResources(statement, connection);
        }
    }
    @Override
    public boolean deleteContact (int id){

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DBConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM contact_information WHERE id = ?";

            statement = connection.prepareStatement(sql);



            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        }catch(SQLException e){
            System.err.println("Error deleting contact: " + e.getMessage());
            return false;
        }finally{
            DBConnectionManager.getInstance().closeResources(statement, connection);
        }
    }
}
