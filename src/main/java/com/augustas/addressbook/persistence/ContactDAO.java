package com.augustas.addressbook.persistence;

import com.augustas.addressbook.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ContactDAO {

    boolean createContact(Contact contact);
    List<Contact> getAllContacts();
    List<Contact> getContactByFirstName(String firstName);
    boolean updateContact(Contact contact);
    boolean deleteContact(int id);


    //Create Contact method

}
