package ERP.BackEnd_ERP.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "besoins")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Besoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "created_by", nullable = false)
    private long createdBy;

    @Column(name = "priority", nullable = false)    
    private String priority;

    @ManyToOne
    @JoinColumn(name = "manager_responsable", referencedColumnName = "id",nullable = true)
    @JsonIgnoreProperties("besoins")
    private User managerResponsable;

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
     // Évite la boucle infinie lors de la sérialisation
     @JsonIgnoreProperties("besoins")
     //@JsonBackReference
    private Contact contact;
}