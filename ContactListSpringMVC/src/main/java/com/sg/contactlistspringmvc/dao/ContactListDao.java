/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sabaaslam
 */
public interface ContactListDao {
    //add the given contact to the data store
    public Contact addContact(Contact contact);
    //remove the Contact with the given id from the data store
    public void removeContact(long contactId);
    
    //update the givin Contact in the data store
    public void updateContact(Contact contact);
    //retrive all Contacts from the data store
    public List<Contact> getAllContacts();
    
    //retrieve the contact wit the given ID
    public Contact getContactById(long contactId);
    //search for Contacts by the given search crieteria
    public List<Contact> searchContacts(Map<SearchTerm,String> criteria);
    
    
}