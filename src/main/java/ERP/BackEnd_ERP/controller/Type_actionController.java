package ERP.BackEnd_ERP.controller;

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

import ERP.BackEnd_ERP.model.Type_action;
import ERP.BackEnd_ERP.service.Type_actionService;

@RestController
@RequestMapping("/api/type-actions")
public class Type_actionController {

    @Autowired
    private Type_actionService type_actionService;

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateType_action(@PathVariable Long id, @RequestBody Type_action type_action) {
        Type_action updatedType_action = type_actionService.updateType_action(id, type_action);
        return ResponseEntity.ok(updatedType_action);
    }

    @GetMapping("belong-to/{belongTo}")
    public ResponseEntity<?> findType_actionByBelongTo(@PathVariable String belongTo) {
        try {
            return ResponseEntity.ok().body(type_actionService.findByBelong_to(belongTo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findType_actionById(@PathVariable Long id) {
        try {
            Type_action type_action = type_actionService.findById(id);
            return ResponseEntity.ok().body(type_action);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteType_actionById(@PathVariable Long id) {
        try {
            type_actionService.deleteType_actionById(id);
            return ResponseEntity.ok().body(Map.of("message", "Type_action deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error: " + e.getMessage()));
        }
    }
    @PostMapping("/add")
public ResponseEntity<?> saveType_action(@RequestBody Type_action type_action) {
    try {
        System.out.println("Objet reçu : " + type_action);
        type_actionService.saveType_action(type_action);
        return ResponseEntity.ok().body(Map.of("message","Type_action saved"));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(Map.of("error", "Error: " + e.getMessage()));
    }
}

@GetMapping("/exists/{name}/{belongTo}")
public ResponseEntity<?> existsByNameAndBelongTo(@PathVariable String name, @PathVariable String belongTo) {
    try {
        return ResponseEntity.ok().body(Map.of("message", type_actionService.existsByNameAndBelongTo(name, belongTo)));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(Map.of("error", "Error: " + e.getMessage()));
    }
}

    
}
