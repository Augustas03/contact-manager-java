package com.augustas.addressbook.controller;

import com.augustas.addressbook.model.Contact;

import java.util.ArrayList;
import java.util.Collections;

public class AddressBookController {

    private ArrayList <Contact> collection ;


    public AddressBookController () {
        this.collection = new ArrayList<Contact>();
    }

    public AddressBookController (ArrayList<Contact> collection) {
        this.collection = collection;
    }

    // CRUD Functionality

    public void addContact (Contact contact){

        if (contact == null) {
            System.out.println("Contact is invalid. Please fill in all the information");
        }
        else{
            try{
                //Check if duplicate
                if(collection.contains(contact)){
                    System.out.println("Contact already exists.");
                }else{
                    collection.add(contact);
                    System.out.println("Contact has been added!");
                }
            }catch(Exception e){
                System.out.println("Adding contact has failed with the error: " + e);
            }
        }
    }

    public void removeContact (Contact contact){

        if(contact == null){
            System.out.println("Contact is empty. Deletion didnt occur");
        }
        else{
            try{
                //check if contact exists
                if(collection.contains(contact)){
                    //Delete contact
                    collection.remove(contact);
                }else{
                    //If contact doesn't exist. Show
                    System.out.println("Contact doesnt exist in storage. Deletion didnt occur");
                }
            }catch(Exception e){
                System.out.println("Deleting contact failed with the error: " + e);
            }
        }

    }

    public void updateContact (Contact oldDetails, Contact newDetails){

        if(oldDetails == null || newDetails == null){
            System.out.println("Contact is empty");
        }else{

            try{
                //check if old contact exists
                if(collection.contains(oldDetails)){
                    oldDetails.setFirstName(newDetails.getFirstName());
                    oldDetails.setLastName(newDetails.getLastName());
                    oldDetails.setPhoneNumber(newDetails.getPhoneNumber());
                    oldDetails.setEmailAddress(newDetails.getEmailAddress());
                }
                else{
                    System.out.println("Contact doesnt exist!");
                }
            }catch(Exception e){
                System.out.println("Updating contact failed with the error: " + e);
            }
        }
    }

    public ArrayList getAllContacts () {
        return new ArrayList<Contact>(collection);
    }


    //Search Functionality

    public ArrayList searchByFirstName ( String firstName ){

        ArrayList<Contact> firstNameResults = new ArrayList<>();

        try{
            //Loop through collection
            for( Contact contact : collection){

                if(firstName.equalsIgnoreCase(contact.getFirstName())){
                    firstNameResults.add(contact);
                }
            }
        }catch( Exception e){
            System.out.println("Searching for first name failed with error: " + e);
        }

        return firstNameResults;
    }

    public ArrayList searchByLastName ( String lastName ){

        ArrayList<Contact> lastNameResults = new ArrayList<>();

        try{
            //Loop through collection
            for( Contact contact : collection){

                if(lastName.equalsIgnoreCase(contact.getLastName())){
                    lastNameResults.add(contact);
                }
            }
        }catch( Exception e){
            System.out.println("Searching for last name failed with error: " + e);
        }

        return lastNameResults;
    }

    public ArrayList searchByName ( String fullName ){

        ArrayList<Contact> fullNameResults = new ArrayList<>();

        try{
            //Loop through collection
            for( Contact contact : collection){

                if(fullName.equalsIgnoreCase(contact.getFullName())){
                    fullNameResults.add(contact);
                }
            }
        }catch( Exception e){
            System.out.println("Searching for full name failed with error: " + e);
        }

        return fullNameResults;
    }

    public ArrayList searchByPhoneNumber ( String phoneNumber ){

        ArrayList<Contact> phoneNumberResults = new ArrayList<>();

        try{
            //Loop through collection
            for( Contact contact : collection){

                if(phoneNumber.equals(contact.getPhoneNumber())){
                    phoneNumberResults.add(contact);
                }
            }
        }catch( Exception e){
            System.out.println("Searching for phone number failed with error: " + e);
        }

        return phoneNumberResults;
    }

    public ArrayList searchByEmail ( String email ){

        ArrayList<Contact> emailResults = new ArrayList<>();

        try{
            //Loop through collection
            for( Contact contact : collection){

                if(email.equalsIgnoreCase(contact.getEmailAddress())){
                    emailResults.add(contact);
                }
            }
        }catch( Exception e){
            System.out.println("Searching for email failed with error: " + e);
        }

        return emailResults;
    }

    //Sorting methods
    public ArrayList<Contact> sortByFirstName() {

        ArrayList<Contact> sortedList = new ArrayList<>(collection);

        Collections.sort(sortedList, (c1, c2) -> c1.getFirstName().compareToIgnoreCase(c2.getFirstName()));
        return sortedList;
    }

    public ArrayList<Contact> sortByLastName() {

        ArrayList<Contact> sortedList = new ArrayList<>(collection);

        Collections.sort(sortedList, (c1, c2) -> c1.getLastName().compareToIgnoreCase(c2.getLastName()));
        return sortedList;
    }


}
