package com.example.phonebook.repository;

import com.example.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByLastNameContainingIgnoreCase(String lastName);

    Contact findByMobile(String mobile);
}
