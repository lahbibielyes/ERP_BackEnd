package ERP.BackEnd_ERP.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Entity
@Table(name = "attachments_crm")
public class Attachment_crm {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("attachments_crm")
    @JoinColumn(name = "action_contact_id", nullable = false)
    private Action_crm action_crm;
    
    
}
