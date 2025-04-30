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
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int effective; 

    @Column(nullable = false)
    private String sector; 

    @Column(nullable = false)
    private String pole;

    @Column(nullable = false)
    private String agency  ;

    @Column(nullable = false)
    private String phone;

    private String address;

    @Column(name = "postal_code")    
    private String postalCode;

    private String city;

    @Column(nullable = false)
    private String country;

   
    private String informations;

    @Column(name = "legal_status")
    private String legalStatus;


    private String siret;

    @Column(name = "ape_code")
    private String apeCode;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("company") // Quand on sérialise les contacts, on ignore leur champ "company"
    private List<Contact> contacts = new ArrayList<>();
}
