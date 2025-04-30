package ERP.BackEnd_ERP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Company findByName(String name) {
        return companyRepository.findOneByName(name);
    }
    public List<Company> findByStatus(String status) {
        return companyRepository.findByStatus(status);
    }


    public List<Company> findByAddress(String address) {
        return companyRepository.findByAddress(address);
    }

    public List<Company> findBySector(String sector) {
        return companyRepository.findBysector(sector);
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
        
        c.setStatus(company.getStatus());
        
        return companyRepository.save(c);
    }

    @Transactional
    public Company updateCompany(Long id, Company company) {
        Company c = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        
        // Update all fields except ID
        c.setName(company.getName());
        c.setStatus(company.getStatus());
        c.setEffective(company.getEffective());
        c.setSector(company.getSector());
        
       
        
        
        c.setPole(company.getPole());
        c.setAgency(company.getAgency());
        c.setPhone(company.getPhone());
        c.setAddress(company.getAddress());
        c.setPostalCode(company.getPostalCode());
        c.setCity(company.getCity());
        c.setCountry(company.getCountry());
        
        c.setInformations(company.getInformations());
        c.setLegalStatus(company.getLegalStatus());
        
        c.setSiret(company.getSiret());
       
        c.setApeCode(company.getApeCode());
        
        
        return companyRepository.save(c);
    }

    
}
