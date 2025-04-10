package ERP.BackEnd_ERP.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ERP.BackEnd_ERP.model.historique_besoin;
import ERP.BackEnd_ERP.service.HistoriqueBesoinService;

@RestController
@RequestMapping("/api/historique_besoin")
public class HistoriqueBesoinController {

    @Autowired
    private HistoriqueBesoinService historiqueBesoinService;

    @GetMapping("/besoin/{id}")
    public ResponseEntity<?> findHistoriqueBesoinByBesoinId(@PathVariable("id") Long besoinId) {
        try {
            List<historique_besoin> historiqueBesoins = historiqueBesoinService.findByBesoinId(besoinId);
            return ResponseEntity.ok().body(historiqueBesoins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/actionBy/{id}")
    public ResponseEntity<?> findHistoriqueBesoinByActionBy(@PathVariable("id") Long actionBy) {
        try {
            List<historique_besoin> historiqueBesoins = historiqueBesoinService.findByActionBy(actionBy);
            return ResponseEntity.ok().body(historiqueBesoins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHistoriqueBesoin(@RequestBody historique_besoin historiqueBesoin) {
        try {
            historiqueBesoinService.saveHistoriqueBesoin(historiqueBesoin);
            return ResponseEntity.ok().body(Map.of("message","historic saved"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<?> existsByBesoinId(@PathVariable("id") Long besoinId) {
        try {
            boolean exists = historiqueBesoinService.existsByBesoinId(besoinId);
            return ResponseEntity.ok().body(Map.of("exists", exists));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    
}
