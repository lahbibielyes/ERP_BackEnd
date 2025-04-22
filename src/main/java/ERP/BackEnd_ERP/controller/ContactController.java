package ERP.BackEnd_ERP.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ERP.BackEnd_ERP.model.Contact;
import ERP.BackEnd_ERP.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

   
    
    @GetMapping("/all")
    public ResponseEntity<?> findAllContacts() {
        try {
            List<Contact> contacts = contactService.findAllContacts();
            return ResponseEntity.ok().body(contacts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
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
            return ResponseEntity.ok().body(Map.of("message","contact saved"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        try {
            Contact updatedContact = contactService.updateContact(id, contact);
            return ResponseEntity.ok().body(updatedContact);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/updatestatus/{id}")
    public ResponseEntity<?> updateContactstatus(@PathVariable Long id, @RequestBody Contact contact) {
        try {
            Contact updatedContact = contactService.updateContactstatus(id, contact);
            return ResponseEntity.ok().body(updatedContact);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @PutMapping("/updatestatut/{id}")
    public ResponseEntity<?> updateContactstatut(@PathVariable Long id, @RequestBody String statut) {
        try {
            Contact updatedContact = contactService.updateContactstatut(id, statut);
            return ResponseEntity.ok().body(updatedContact);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/statut/{statut}")
    public ResponseEntity<?> findByStatus(@PathVariable("statut") String statut) {
        try {
            List<Contact> contacts = contactService.findByStatut(statut);
            return ResponseEntity.ok().body(contacts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
   

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContactById(@PathVariable("id") Long id) {
        try {
            contactService.deleteContactById(id);
            return ResponseEntity.ok().body(Map.of("message","contact deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    
}
