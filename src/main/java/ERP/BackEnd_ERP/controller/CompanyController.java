package ERP.BackEnd_ERP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ERP.BackEnd_ERP.model.Company;
import ERP.BackEnd_ERP.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @GetMapping("/all")
    public ResponseEntity<?> findAllCompanies() {
        try{
            List<Company> companies = companyService.findAllCompanies();
            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findCompanyById(@PathVariable("id") Long id) {
        try{
            Company company = companyService.findCompanyById(id).get();
            return ResponseEntity.ok().body(company);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/name/{name}") 
    public ResponseEntity<?> findByName(@PathVariable("name") String name) {
        try{
            Company company = companyService.findByName(name);
            return ResponseEntity.ok().body(company);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/addres/{address}")
    public ResponseEntity<?> findByAddress(@PathVariable("address") String adress) {
        try{
            List<Company> companies = companyService.findByAddress(adress);
            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/sector/{sector}")
    public ResponseEntity<?> findByActivitySector(@PathVariable("sector") String sector) {
        try{
            List<Company> companies = companyService.findByActivitySector(sector);
            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable("status") String status) {
        try{
            List<Company> companies = companyService.findByStatus(status);
            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable("id") Long id) {
        try{
            companyService.deleteCompanyById(id);
            return ResponseEntity.ok().body("Company deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveCompany(Company company) {
        try{
            companyService.saveCompany(company);
            return ResponseEntity.ok().body("Company saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
    
}
