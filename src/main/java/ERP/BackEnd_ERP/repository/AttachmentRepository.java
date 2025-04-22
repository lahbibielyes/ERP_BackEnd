package ERP.BackEnd_ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ERP.BackEnd_ERP.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    
}
