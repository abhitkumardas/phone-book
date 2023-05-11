package com.example.phonebook.service;

import com.example.phonebook.model.Contact;
import com.example.phonebook.model.ContactReq;
import com.example.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Please provide valid ID"));
    }

    public Page<Contact> getAll(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    public Contact create(ContactReq contactReq) {
        Contact contact = new Contact();

        if (Objects.isNull(contactReq.getFirstName()) || contactReq.getFirstName().isEmpty()) {
            throw new RuntimeException("Please provide valid FirstName");
        }
        if (Objects.isNull(contactReq.getLastName()) || contactReq.getLastName().isEmpty()) {
            throw new RuntimeException("Please provide valid LastName");
        }
        if (Objects.isNull(contactReq.getMobile()) || contactReq.getMobile().isEmpty()) {
            throw new RuntimeException("Please provide valid Mobile No");
        }

        Contact existingContactByMobile = contactRepository.findByMobile(contactReq.getMobile());
        if (!Objects.isNull(existingContactByMobile)) {
            throw new RuntimeException("Mobile Number Already Exists");
        }

        contact.setFirstName(contactReq.getFirstName());
        contact.setLastName(contactReq.getLastName());
        contact.setMobile(contactReq.getMobile());

        return contactRepository.save(contact);
    }
    public Contact update(Long id, ContactReq contactReq) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Please provide valid ID"));

        if (!Objects.isNull(contactReq.getFirstName()) && !contactReq.getFirstName().isEmpty()) {
            contact.setFirstName(contactReq.getFirstName());
        }
        if (!Objects.isNull(contactReq.getLastName()) && !contactReq.getLastName().isEmpty()) {
            contact.setLastName(contactReq.getLastName());
        }
        if (!Objects.isNull(contactReq.getMobile()) && !contactReq.getMobile().isEmpty()) {
            Contact existingContactByMobile = contactRepository.findByMobile(contactReq.getMobile());
            if (!Objects.isNull(existingContactByMobile)) {
                throw new RuntimeException("Mobile Number Already Exists with OtherName Please provide different number");
            }

            contact.setMobile(contactReq.getMobile());
        }

        return contactRepository.save(contact);
    }

    public String deleteById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Please provide valid ID"));
        contactRepository.delete(contact);
        return "Contact Successfully Deleted";
    }
}
