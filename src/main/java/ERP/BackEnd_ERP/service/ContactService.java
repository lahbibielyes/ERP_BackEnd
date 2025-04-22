package ERP.BackEnd_ERP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.BackEnd_ERP.model.Company;
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
    public Contact updateContactstatus(Long id,Contact contact) {
        Contact c=contactRepository.findById(contact.getId())
        .orElseThrow(()->new RuntimeException( "Contact not found"));
        
        c.setStatut(contact.getStatut());
        
        return contactRepository.save(c);
    }
    @Transactional
    public Contact updateContactstatut(Long id,String statut) {
        Contact c=contactRepository.findById(id)
        .orElseThrow(()->new RuntimeException( "Contact not found"));
        System.out.println("contact"+c);
        c.setStatut(statut);
        
        return contactRepository.save(c);
    }

    @Transactional
    public Contact updateContact(Long id, Contact contact) {
        Contact c = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        
        // Mise à jour de tous les champs
        c.setCivilite(contact.getCivilite());
        c.setNom(contact.getNom());
        c.setPrenom(contact.getPrenom());
        c.setFonction(contact.getFonction());
        c.setService(contact.getService());
        c.setManager(contact.getManager());
        c.setType(contact.getType());
        c.setStatut(contact.getStatut());
        c.setProvenance(contact.getProvenance());
        c.setPrecisiez(contact.getPrecisiez());
        c.setAgence(contact.getAgence());
        c.setEmail(contact.getEmail());
        c.setTelephone(contact.getTelephone());
        c.setAdresse(contact.getAdresse());
        c.setPostalCode(contact.getPostalCode());
        c.setVille(contact.getVille());
        c.setPays(contact.getPays());
        c.setReseauxsociaux(contact.getReseauxsociaux());
        c.setPerimetreTechnique(contact.getPerimetreTechnique());
        
        // Pour les listes, on peut soit les remplacer complètement, soit faire un addAll après clear
        if (contact.getDomaines() != null) {
            c.getDomaines().clear();
            c.getDomaines().addAll(contact.getDomaines());
        }
        
        if (contact.getOutils() != null) {
            c.getOutils().clear();
            c.getOutils().addAll(contact.getOutils());
        }
        
        c.setInformationsComplementaires(contact.getInformationsComplementaires());
        c.setCompany(contact.getCompany());
        
        return contactRepository.save(c);
    }
      public List<Contact> findByStatut(String statut) {
        return contactRepository.findByStatut(statut);
    }
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }

     
    }
    
    
    
  
 
    
    
    

