package ERP.BackEnd_ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ERP.BackEnd_ERP.model.Attachment_crm;


@Repository
public interface Attachment_crmRepository extends JpaRepository<Attachment_crm, Long> {
    
}
