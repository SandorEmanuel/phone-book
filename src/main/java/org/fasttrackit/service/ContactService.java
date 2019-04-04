package org.fasttrackit.service;

import org.fasttrackit.domain.Contact;
import org.fasttrackit.persistance.ContactRepository;
import org.fasttrackit.transfer.SaveContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ContactService {

    private ContactRepository contactRepository = new ContactRepository();

    public void createContact (SaveContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating contact:" + request);
        contactRepository.createContact(request);
    }

    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Getting contacts.");
        return contactRepository.getContacts();
    }
    public void updateContact (SaveContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating contact:" + request);
        contactRepository.updateContact(request);
    }
    public void deleteContact (SaveContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Delete contact:" +request);
        contactRepository.deleteContact(request);
    }
}
