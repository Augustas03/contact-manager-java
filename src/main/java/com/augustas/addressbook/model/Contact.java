package com.augustas.addressbook.model;

public class Contact {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;


    public Contact () {
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.emailAddress = "";
    }

    public Contact (String firstName, String lastName, String phoneNumber, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    //Getters

    public String getFirstName (){
        return this.firstName;
    }

    public String getLastName (){
        return this.lastName;
    }

    public String getFullName (){
        return this.firstName + " " + this.lastName;
    }

    public String getPhoneNumber (){
        return this.phoneNumber;
    }

    public String getEmailAddress () {
        return this.emailAddress;
    }

    // Setters

    public void setFirstName (String firstName){
        this.firstName = firstName;
    }

    public void setLastName (String lastName){
        this.lastName = lastName;
    }

    public void setPhoneNumber (String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress (String emailAddress) {
        this.emailAddress = emailAddress;
    }


    // To string method
    @Override
    public String toString() {
        return "Contact: " + this.firstName + " " + this.lastName + " | Phone: " + this.phoneNumber + " | Email: " + this.emailAddress;
    }

    // Equals method
    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;

        if(obj == null || !(obj instanceof Contact)) return false;

        //Cast obj to contact
        Contact contact = (Contact) obj;

        //Compare fields
        return firstName.equals(contact.firstName) &&
                lastName.equals(contact.lastName) &&
                String.valueOf(phoneNumber).equals(String.valueOf(contact.phoneNumber)) &&
                emailAddress.equals(contact.emailAddress);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + emailAddress.hashCode();
        return result;
    }
}
