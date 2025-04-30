package ERP.BackEnd_ERP.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import ERP.BackEnd_ERP.model.Action_crm;

import ERP.BackEnd_ERP.model.Attachment_crm;
import ERP.BackEnd_ERP.repository.Action_crmRepository;
import ERP.BackEnd_ERP.repository.Attachment_crmRepository;

@Service
public class Action_crmService {

    @Autowired
    private Action_crmRepository action_crmRepository;

    @Autowired
    private Attachment_crmRepository attachment_crmRepository;

    private final Path storageDirectory = Paths.get("uploads_crm");

    public Action_crmService() throws IOException {
        // Création du répertoire de stockage s'il n'existe pas
        if (!Files.exists(storageDirectory)) {
            Files.createDirectories(storageDirectory);
        }
   
}

public void addAction(Action_crm action_crm, MultipartFile[] files) throws IOException {
    List<Attachment_crm> attachments = new ArrayList<>();

    if (files != null) {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String uniquefileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path filePath = storageDirectory.resolve(uniquefileName);
                Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);

                Attachment_crm attachment = new Attachment_crm();
                attachment.setFileName(file.getOriginalFilename());
                attachment.setFileType(file.getContentType());
                attachment.setFilePath(filePath.toString());
                attachment.setAction_crm(action_crm); // relation inversée

                attachments.add(attachment);
            }
        }
    }

    action_crm.setAttachments(attachments);
    action_crmRepository.save(action_crm); // si CascadeType.ALL, ceci suffit
    attachment_crmRepository.saveAll(attachments); // sinon, ajoute cette ligne pour être sûr
}


    public Action_crm findActionById(Long id) {
        return action_crmRepository.findById(id)
        .orElseThrow(()->new RuntimeException( "Action not found"));
    }

    public List<Action_crm> findByContactId(Long contactId) {
        return action_crmRepository.findAllByContactId(contactId);
    }

    public Action_crm updateAction(Long id, Action_crm action_crm, MultipartFile[] files) throws IOException {
        Action_crm existingAction = action_crmRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Action not found"));
    
        existingAction.setDescription(action_crm.getDescription());
        existingAction.setTypeAction(action_crm.getTypeAction());
        existingAction.setManager(action_crm.getManager());
    
        if (files != null && files.length > 0) {
            List<Attachment_crm> newAttachments = new ArrayList<>();
    
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    Path filePath = storageDirectory.resolve(uniqueFileName);
                    Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
    
                    Attachment_crm attachment = new Attachment_crm();
                    attachment.setFileName(file.getOriginalFilename());
                    attachment.setFileType(file.getContentType());
                    attachment.setFilePath(filePath.toString());
                    attachment.setAction_crm(existingAction);
                    newAttachments.add(attachment);
                }
            }
    
            // Supprimer les anciens fichiers + vider proprement la liste (sans la remplacer)
            List<Attachment_crm> currentAttachments = existingAction.getAttachments();
            if (currentAttachments != null) {
                for (Attachment_crm attachment : currentAttachments) {
                    Path oldFilePath = Paths.get(attachment.getFilePath());
                    Files.deleteIfExists(oldFilePath);
                }
                currentAttachments.clear();
                currentAttachments.addAll(newAttachments);
            } else {
                existingAction.setAttachments(newAttachments);
            }
        }
    
        return action_crmRepository.save(existingAction);
    }
    
    

    public void deleteAction(Long id) throws IOException{
        Action_crm existingAction=action_crmRepository.findById(id)
        .orElseThrow(()->new RuntimeException( "Action not found"));

        if(existingAction!=null){
            if(existingAction.getAttachments()!=null){
                for(Attachment_crm attachment : existingAction.getAttachments()){
                    Path filePath = Paths.get(attachment.getFilePath());
                    Files.deleteIfExists(filePath);
                }
            }
            action_crmRepository.deleteById(id);
        }

    }

    public Optional<Attachment_crm> getAttachment(Long id) {
        return attachment_crmRepository.findById(id);
    }

    public void deleteAttachment(Long id) throws IOException {
        Optional<Attachment_crm> attachmentOpt = attachment_crmRepository.findById(id);
        if (attachmentOpt.isPresent()) {
            Attachment_crm attachment = attachmentOpt.get();
            Path filePath = Paths.get(attachment.getFilePath());
            Files.deleteIfExists(filePath);
            attachment_crmRepository.deleteById(id);
        }
    }
    
}

