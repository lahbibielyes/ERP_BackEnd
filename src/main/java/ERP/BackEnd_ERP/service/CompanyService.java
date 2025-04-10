package ERP.BackEnd_ERP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.BackEnd_ERP.model.Besoin;
import ERP.BackEnd_ERP.model.Company;
import ERP.BackEnd_ERP.repository.CompanyRepository;

@Service
public class CompanyService  {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company findByName(String nom) {
        return companyRepository.findOneByNom(nom);
    }
    public List<Company> findByStatut(String statut) {
        return companyRepository.findByStatut(statut);
    }


    public List<Company> findByAddresse(String adresse) {
        return companyRepository.findByAddresse(adresse);
    }

    public List<Company> findBySecteur(String secteur) {
        return companyRepository.findBysecteur(secteur);
    }

    /*public List<Company> findByStatus(String status) {
        return companyRepository.findByStatus(status);
    }*/

    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    public void saveCompany(Company company) {
        System.out.println(company);
        companyRepository.save(company);
    }
    
    @Transactional
    public Company updateCompanystatus(Long id,Company company) {
        Company c=companyRepository.findById(company.getId())
        .orElseThrow(()->new RuntimeException( "Company not found"));
        
        c.setStatut(company.getStatut());
        
        return companyRepository.save(c);
    }
    

    
}
