package ERP.BackEnd_ERP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Company> findByAddress(String adress) {
        return companyRepository.findByAddress(adress);
    }

    public List<Company> findByActivitySector(String sector) {
        return companyRepository.findByActivitysector(sector);
    }

    public List<Company> findByStatus(String status) {
        return companyRepository.findByStatus(status);
    }

    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }
    
}
