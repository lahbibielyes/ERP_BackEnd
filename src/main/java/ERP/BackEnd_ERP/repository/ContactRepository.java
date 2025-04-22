package ERP.BackEnd_ERP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ERP.BackEnd_ERP.model.Company;
import ERP.BackEnd_ERP.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {


    //@Query("SELECT c FROM Company c WHERE c.name_company = :name")
    Contact findOneByNom(String nom);
    //List<Company> findAllCompanies();


    
    List<Contact> findByStatut(String statut);
    
}
