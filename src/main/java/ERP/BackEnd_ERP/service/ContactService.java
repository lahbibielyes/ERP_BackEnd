package ERP.BackEnd_ERP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.BackEnd_ERP.model.Contact;
import ERP.BackEnd_ERP.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findContactById(Long id) {
        return contactRepository.findById(id);
    
}
    public void createContact(Contact contact) {
        System.out.println(contact);
        contactRepository.save(contact);
    }
    
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
 
    @Transactional
    public Contact updateContact(Long id, Contact contact) {
        Contact c = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        
        // Mise à jour de tous les champs
        c.setCivility(contact.getCivility());
        c.setLastname(contact.getLastname());
        c.setFirstname(contact.getFirstname());
        c.setFunction(contact.getFunction());
        c.setService(contact.getService());
        c.setCreatedBy(contact.getCreatedBy());
        c.setType(contact.getType());
        c.setDomains(contact.getDomains());
        c.setTools(contact.getTools());
        
       c.setActive(contact.isActive());
        c.setProvenance(contact.getProvenance());
        
        c.setAgency(contact.getAgency());
        c.setEmail(contact.getEmail());
        c.setPhone(contact.getPhone());
        c.setAddress(contact.getAddress());
        c.setPostalCode(contact.getPostalCode());
        c.setCity(contact.getCity());
       
        c.setCountry(contact.getCountry());
         // On désactive le contact au lieu de le supprimer
        
        
        
        // Pour les listes, on peut soit les remplacer complètement, soit faire un addAll après clear
     
      
        c.setCompany(contact.getCompany());
        
        return contactRepository.save(c);
    }
     
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
public void inAtiveContact(Long id) {
    Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));
         boolean c =contact.isActive();
    contact.setActive(!c); // Inverse l'état actif du contact
    contactRepository.save(contact); // Enregistre le contact mis à jour
     
    }
}
    
    
  
 
    
    
    

