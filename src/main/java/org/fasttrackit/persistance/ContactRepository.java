package org.fasttrackit.persistance;

import org.fasttrackit.domain.Contact;
import org.fasttrackit.transfer.SaveContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ContactRepository {

    public void createContact(SaveContactRequest request) throws SQLException, IOException, ClassNotFoundException {

        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "INSERT INTO contacts (first_name , last_name, phone_number) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setInt(3, request.getPhoneNumber());
            preparedStatement.executeUpdate();

        }

    }

    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String query = "SELECT id, first_name, last_name, phone_number FROM contacts ORDER BY last_name DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Contact> response = new ArrayList<>();
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getInt("phone_number"));
                response.add(contact);
            }
            return response;
        }
    }
    public void updateContact(SaveContactRequest request) throws SQLException, IOException, ClassNotFoundException {

        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "UPDATE contacts SET first_name = ?, last_name = ?, phone_number = ? WHERE last_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setLong(3, request.getPhoneNumber());
            preparedStatement.setString(4, request.getLastName());
            preparedStatement.executeUpdate();

        }

    }

    public void deleteContact(SaveContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "DELETE FROM contacts WHERE first_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getFirstName());

            preparedStatement.executeUpdate();

        }
    }
}

