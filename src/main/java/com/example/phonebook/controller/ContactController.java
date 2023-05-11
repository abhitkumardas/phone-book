package com.example.phonebook.controller;

import com.example.phonebook.model.ContactReq;
import com.example.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/all")
    public ResponseEntity getAllContacts(Pageable pageable) {
        return ResponseEntity.ok(contactService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity createContact(@RequestBody ContactReq contactReq) {
        return ResponseEntity.ok(contactService.create(contactReq));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateContact(@PathVariable Long id, @RequestBody ContactReq contactReq) {
        return ResponseEntity.ok(contactService.update(id, contactReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity getAllContacts(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.deleteById(id));
    }

}
