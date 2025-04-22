package ERP.BackEnd_ERP.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String civilite;

    private String nom;
    private String prenom;
    private String fonction;
    private String service;
    private String manager;
    private String type;
    private String statut;
    private String provenance;

    private String precisiez;
    private String agence;
    private String email;
    private String telephone;
    private String adresse;
    private String postalCode;
    private String ville;
    private String pays;
    private String reseauxsociaux;
    private String perimetreTechnique;
    private List<String> Domaines= new ArrayList<>();
    private List<String> outils = new ArrayList<>();
    private String informationsComplementaires;

    

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties("contacts")  // Ignore la liste des contacts dans la société lors de la sérialisation d'un contact
    private Company company;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("contact") // Ignore le contact lors de la sérialisation d'un besoin
    private List<Besoin> besoins = new ArrayList<>();
}
