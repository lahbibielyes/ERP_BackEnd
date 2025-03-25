package ERP.BackEnd_ERP.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nom;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private int effectif; 

    @Column(nullable = false)
    private String secteur; 


    private String filiales; 

    @Column(nullable = false)
    private String provenance; 

   
    private String precisiez;

    @Column(nullable = false)
    private String responsableManager ;

    @Column(nullable = false)
    private String pole;

    @Column(nullable = false)
    private String agence  ;

    @Column(nullable = false)
    private String telephone;

    private String addresse;

    private String postalCode;

    private String ville;

    @Column(nullable = false)
    private String pays;

    private String siteWeb;

    private String informations;

    private String statutJuridique;

    private String tva;

    private String siret;

    private String rcs;

    private String codeApe;

    private String numeroFournisseur;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Contact> contacts = new ArrayList<>(); 
}
