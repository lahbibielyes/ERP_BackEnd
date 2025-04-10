package ERP.BackEnd_ERP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ERP.BackEnd_ERP.model.Company;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    //@Query("SELECT c FROM Company c WHERE c.name_company = :name")
    Company findOneByNom(String nom);
    //List<Company> findAllCompanies();


    List<Company> findByAddresse(String adresse);
    List<Company> findBysecteur(String secteur);
    List<Company> findByStatut(String statut);

}
