package ERP.BackEnd_ERP.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import ERP.BackEnd_ERP.model.Action_besoin;
import ERP.BackEnd_ERP.service.Action_besoinService;

@RestController
@RequestMapping("/api/action-besoin")
public class Action_besoinController {
    
    @Autowired
    private Action_besoinService actionService;


    @GetMapping("/files/{filename}")
public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
    // Construction du chemin vers le fichier
    Path filePath = Paths.get("uploads/").resolve(filename).normalize();
    System.out.println(filePath);
    Resource resource = new UrlResource(filePath.toUri());
    System.out.println("rrrrrrrrrrrrrrrr"+resource);

    // Vérifie que le fichier existe et est lisible
    if (!resource.exists() || !resource.isReadable()) {
        System.out.println("File not found: " + filename);
        return ResponseEntity.notFound().build();
    }

    // Détermine le type MIME en fonction de l'extension du fichier
    MediaType mediaType;
    if (filename.toLowerCase().endsWith(".pdf")) {
        mediaType = MediaType.APPLICATION_PDF;
    } else if (filename.toLowerCase().endsWith(".docx")) {
        mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    } else {
        // Par défaut, on utilise un type générique pour les flux binaires
        mediaType = MediaType.APPLICATION_OCTET_STREAM;
    }

    // Retourne le fichier avec le type MIME approprié et une disposition inline
    return ResponseEntity.ok()
            .contentType(mediaType)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
            .body(resource);
}



@PostMapping(path = "/add", consumes = "multipart/form-data")
public ResponseEntity<?> createActionBesoinWithFiles(
    @RequestParam String action_besoin,
    @RequestPart(value = "file", required = false) MultipartFile[] file) {
    try {
        System.out.println("Action_besoin: " + action_besoin);
        ObjectMapper mapper = new ObjectMapper();
        Action_besoin action_besoinObj = mapper.readValue(action_besoin, Action_besoin.class);
        System.out.println("Action_besoinObj: " + action_besoinObj);

        action_besoinObj.setDateAction(new java.util.Date());

        if(file!=null && file.length==0){
        actionService.addAction(action_besoinObj, null);
        return ResponseEntity.ok().body(Map.of("message", "Action_besoin saved successfully"));
            
        }
        actionService.addAction(action_besoinObj, file);
        return ResponseEntity.ok().body(Map.of("message", "Action_besoin saved successfully"));

        
    } catch (Exception e) {
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"+e.getMessage());
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
       
    }
}



    


    @GetMapping("/{id}")
    public ResponseEntity<?> findAction_besoinById(@PathVariable("id") Long id) {
        try {
            Action_besoin action_besoin = actionService.findActionById(id);
            return ResponseEntity.ok().body(action_besoin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAction_besoinById(@PathVariable("id") Long id) {
        try {
            System.out.println("iddddddddddddddddddddddddddddddd "+ id);
            actionService.deleteAction(id);
            return ResponseEntity.ok().body(Map.of("message", "Action_besoin deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("besoin/{besoinId}")
    public ResponseEntity<?> findAction_besoinByBesoinId(@PathVariable("besoinId") Long besoinId) {
        try {
            return ResponseEntity.ok().body(actionService.findByBesoinId(besoinId));
        } catch (Exception e) {
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"+e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAction_besoin(@PathVariable("id") Long id, 
        @RequestParam("action_besoin") String action_besoin,
        @RequestParam(value="file",required=false) MultipartFile[] file) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            Action_besoin action_besoinObj = mapper.readValue(action_besoin, Action_besoin.class);
            actionService.updateAction(id, action_besoinObj, file);
            return ResponseEntity.ok().body(Map.of("message","Action_besoin updated successfully"));
        } catch (Exception e) {
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeee"+e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
            
        }
    }


}
