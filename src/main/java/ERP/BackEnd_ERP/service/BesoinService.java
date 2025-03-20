package ERP.BackEnd_ERP.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.BackEnd_ERP.model.Besoin;
import ERP.BackEnd_ERP.repository.BesoinRepository;

@Service
public class BesoinService {

    @Autowired
    private BesoinRepository besoinRepository;

    public List<Besoin> findAllBesoin() {
        return besoinRepository.findAll();
    }

    public Besoin findBesoinById(Long id) {
        return besoinRepository.findById(id)
        .orElseThrow(()->new RuntimeException( "Besoin not found"));
    }

    public List<Besoin> findBesoinByTitle(String title) {
        return besoinRepository.findByTitle(title);
    }

    public List<Besoin> findBesoinByStatus(String status) {
        return besoinRepository.findByStatus(status);

    }

    public List<Besoin> findBesoinByContact(Long contactId) {
        return besoinRepository.findByContactId(contactId);
    }

    @Transactional
    public Besoin updateBesoin(Long id,Besoin besoin) {
        Besoin b=besoinRepository.findById(besoin.getId())
        .orElseThrow(()->new RuntimeException( "Besoin not found"));
        b.setTitle(besoin.getTitle());
        b.setDescription(besoin.getDescription());
        b.setStatus(besoin.getStatus());
        
        return besoinRepository.save(b);
    }

    public void deleteBesoin(Long id) {
        besoinRepository.deleteById(id);
    }

    public List<Besoin> findBesoinByCreationDate(Date date) {
        return besoinRepository.findByCreationDate(date);
    }

    public List<String> findStatus() {
        return besoinRepository.findStatus();
    }

    public void saveBesoin(Besoin besoin) {
        besoinRepository.save(besoin);
    }
}
    

