package ERP.BackEnd_ERP.controller;

import java.sql.Date;
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

import ERP.BackEnd_ERP.model.Besoin;
import ERP.BackEnd_ERP.service.BesoinService;

@RestController
@RequestMapping("/api/besoin")
public class BesoinController {

    @Autowired
    private BesoinService besoinService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllBesoin() {
        try {
            List<Besoin> besoins = besoinService.findAllBesoin();
            return ResponseEntity.ok().body(besoins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> findBesoinByTitle(@PathVariable("title")  String title) {
        try {
            List<Besoin> besoins = besoinService.findBesoinByTitle(title);
            return ResponseEntity.ok().body(besoins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> findBesoinByStatus(@PathVariable("status") String status) {
        try {
            List<Besoin> besoins = besoinService.findBesoinByStatus(status);
            return ResponseEntity.ok().body(besoins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<?> findBesoinByContact(@PathVariable("id") Long id) {
        try {
            List<Besoin> besoins = besoinService.findBesoinByContact(id);
            return ResponseEntity.ok().body(besoins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/creationDate/{date}")
    public ResponseEntity<?> findBesoinByCreationDate(@PathVariable("date") Date date) {
        try {
            List<Besoin> besoins = besoinService.findBesoinByCreationDate(date);
            return ResponseEntity.ok().body(besoins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBesoinById(@PathVariable Long id) {
        try {
            Besoin besoin = besoinService.findBesoinById(id);
            return ResponseEntity.ok().body(besoin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBesoin(@PathVariable Long id, @RequestBody Besoin besoin) {
        
       Besoin updatedBesoin = besoinService.updateBesoin(id,besoin);
        return ResponseEntity.ok().body(updatedBesoin);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBesoin(Long id) {
        try {
            besoinService.deleteBesoin(id);
            return ResponseEntity.ok().body("Besoin deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @PostMapping("/add")
public ResponseEntity<?> saveBesoin(@RequestBody Besoin besoin) {
    try {
        besoinService.saveBesoin(besoin);
        return ResponseEntity.ok().body(Map.of("message","besoin saved")); // Return the created object, or an appropriate response
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}


    @GetMapping("/status")
    public ResponseEntity<?> findStatus() {
        try {
            List<String> status = besoinService.findStatus();
            return ResponseEntity.ok().body(status);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    
    
}
