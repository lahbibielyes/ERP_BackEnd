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

import ERP.BackEnd_ERP.model.Action_besoin;
import ERP.BackEnd_ERP.model.Attachment;
import ERP.BackEnd_ERP.repository.Action_besoinRepository;
import ERP.BackEnd_ERP.repository.AttachmentRepository;

@Service
public class Action_besoinService {

    @Autowired
    private Action_besoinRepository action_besoinRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    private final Path storageDirectory = Paths.get("uploads");

    public Action_besoinService() throws IOException {
        // Création du répertoire de stockage s'il n'existe pas
        if (!Files.exists(storageDirectory)) {
            Files.createDirectories(storageDirectory);
        }
   
}

public void addAction(Action_besoin action_besoin, MultipartFile[] files) throws IOException {
    List<Attachment> attachments = new ArrayList<>();

    if (files != null) {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String uniquefileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path filePath = storageDirectory.resolve(uniquefileName);
                Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);

                Attachment attachment = new Attachment();
                attachment.setFileName(file.getOriginalFilename());
                attachment.setFileType(file.getContentType());
                attachment.setFilePath(filePath.toString());
                attachment.setAction_besoin(action_besoin); // relation inversée

                attachments.add(attachment);
            }
        }
    }

    action_besoin.setAttachments(attachments);
    action_besoinRepository.save(action_besoin); // si CascadeType.ALL, ceci suffit
    attachmentRepository.saveAll(attachments); // sinon, ajoute cette ligne pour être sûr
}


    public Action_besoin findActionById(Long id) {
        return action_besoinRepository.findById(id)
        .orElseThrow(()->new RuntimeException( "Action not found"));
    }

    public List<Action_besoin> findByBesoinId(Long besoinId) {
        return action_besoinRepository.findAllByBesoinId(besoinId);
    }

    public Action_besoin updateAction(Long id, Action_besoin action_besoin, MultipartFile[] files) throws IOException {
        Action_besoin existingAction = action_besoinRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Action not found"));
    
        existingAction.setDescription(action_besoin.getDescription());
        existingAction.setTypeAction(action_besoin.getTypeAction());
        existingAction.setManager(action_besoin.getManager());
    
        if (files != null && files.length > 0) {
            List<Attachment> newAttachments = new ArrayList<>();
    
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    Path filePath = storageDirectory.resolve(uniqueFileName);
                    Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
    
                    Attachment attachment = new Attachment();
                    attachment.setFileName(file.getOriginalFilename());
                    attachment.setFileType(file.getContentType());
                    attachment.setFilePath(filePath.toString());
                    attachment.setAction_besoin(existingAction);
                    newAttachments.add(attachment);
                }
            }
    
            // Supprimer les anciens fichiers + vider proprement la liste (sans la remplacer)
            List<Attachment> currentAttachments = existingAction.getAttachments();
            if (currentAttachments != null) {
                for (Attachment attachment : currentAttachments) {
                    Path oldFilePath = Paths.get(attachment.getFilePath());
                    Files.deleteIfExists(oldFilePath);
                }
                currentAttachments.clear();
                currentAttachments.addAll(newAttachments);
            } else {
                existingAction.setAttachments(newAttachments);
            }
        }
    
        return action_besoinRepository.save(existingAction);
    }
    
    

    public void deleteAction(Long id) throws IOException{
        Action_besoin existingAction=action_besoinRepository.findById(id)
        .orElseThrow(()->new RuntimeException( "Action not found"));

        if(existingAction!=null){
            if(existingAction.getAttachments()!=null){
                for(Attachment attachment : existingAction.getAttachments()){
                    Path filePath = Paths.get(attachment.getFilePath());
                    Files.deleteIfExists(filePath);
                }
            }
            action_besoinRepository.deleteById(id);
        }

    }

    public Optional<Attachment> getAttachment(Long id) {
        return attachmentRepository.findById(id);
    }

    public void deleteAttachment(Long id) throws IOException {
        Optional<Attachment> attachmentOpt = attachmentRepository.findById(id);
        if (attachmentOpt.isPresent()) {
            Attachment attachment = attachmentOpt.get();
            Path filePath = Paths.get(attachment.getFilePath());
            Files.deleteIfExists(filePath);
            attachmentRepository.deleteById(id);
        }
    }
    
}

