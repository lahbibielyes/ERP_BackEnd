package ERP.BackEnd_ERP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import ERP.BackEnd_ERP.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    //@Query("SELECT c FROM Company c WHERE c.name_company = :name")
    Company findOneByName(String name);
    //List<Company> findAllCompanies();


    List<Company> findByAddress(String adress);
    List<Company> findByActivitysector(String sector);
    List<Company> findByStatus(String status);

}
