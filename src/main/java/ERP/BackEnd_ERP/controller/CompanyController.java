package ERP.BackEnd_ERP.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ERP.BackEnd_ERP.model.Company;
import ERP.BackEnd_ERP.service.CompanyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

   

    @GetMapping("/{id}")
    public ResponseEntity<?> findCompanyById(@PathVariable("id") Long id) {
        try {
            Company company = companyService.findCompanyById(id).get();
            return ResponseEntity.ok().body(company);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/name/{nom}")
    public ResponseEntity<?> findByName(@PathVariable("nom") String name) {
        try {
            Company company = companyService.findByName(name);
            return ResponseEntity.ok().body(company);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/addrese/{addresse}")
    public ResponseEntity<?> findByAddress(@PathVariable("addresse") String address) {
        try {
            List<Company> companies = companyService.findByAddress(address);
            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/secteur/{secteur}")
    public ResponseEntity<?> findBySector(@PathVariable("secteur") String sector) {
        try {
            List<Company> companies = companyService.findBySector(sector);
            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable("status") String status) {
        try {
            List<Company> companies = companyService.findByStatus(status);
            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllCompanies() {
        try {
            List<Company> companies = companyService.findAllCompanies();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+companies);
            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable("id") Long id) {
        try {
            companyService.deleteCompanyById(id);
            return ResponseEntity.ok().body(Map.of("message","company deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveCompany(@RequestBody Company company) {
        try {
            companyService.saveCompany(company);
            return ResponseEntity.ok().body(Map.of("message","company saved"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        try {
            Company updatedCompany = companyService.updateCompany(id, company);
            return ResponseEntity.ok().body(updatedCompany);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @PutMapping("/updatestatus/{id}")
    public ResponseEntity<?> updateCompanystatus(@PathVariable Long id, @RequestBody Company company) {
        try {
            Company updatedCompany = companyService.updateCompanystatus(id, company);
            return ResponseEntity.ok().body(updatedCompany);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
   

   
}
