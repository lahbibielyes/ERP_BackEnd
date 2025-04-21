package ERP.BackEnd_ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ERP.BackEnd_ERP.model.Contact;
import ERP.BackEnd_ERP.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/all")
    public List<Contact> findAllContacts() {
        return contactService.findAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findContactById(@PathVariable("id") Long id) {
        return contactService.findContactById(id)
                .map(contact -> ResponseEntity.ok().body(contact))
                .orElse(ResponseEntity.notFound().build());
    }
     @PostMapping("/add")
    public ResponseEntity<?> saveContact(@RequestBody Contact c) {
        try {
            contactService.createContact(c);
            System.out.println("contact :" +c);
            return ResponseEntity.ok().body("Contact saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
    


    
}
