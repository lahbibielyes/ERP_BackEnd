package ERP.BackEnd_ERP.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "action_besoin")
public class Action_besoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_action",nullable = false)
    private Date dateAction;

    @Column(name="type_action",nullable = false)
    private String typeAction;

    @Column(nullable = false)
    private String description;

    @Column(name = "created_by", nullable = false)
    private long createdBy;

    @Column(name = "besoin_id", nullable = false)
    private Long besoinId;

    @ManyToOne
    @JoinColumn(name = "manager", referencedColumnName = "id",nullable = true)
    @JsonIgnoreProperties("action_besoins")
    private User manager;

    @OneToMany(mappedBy = "action_besoin",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("action_besoin")
    private List<Attachment> attachments;

}
