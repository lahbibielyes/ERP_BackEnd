package ERP.BackEnd_ERP.model;

import java.util.ArrayList;
import java.util.Date;
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
    private String civility;

    private String firstname;
    private String lastname;
    private String function;
    private String service;
    private Long createdBy;
    private String type;
   
    private String provenance;

    
    private String agency;
    private String email;
    private String phone;
    private String address;
    @Column(name = "postal_code")    
    private String postalCode;

    private String city;
   
    private String country;

   
   
    private List<String> Domains= new ArrayList<>();
    private String otherDomains;
    private List<String> tools = new ArrayList<>();
    private String otherTools;
   

    @Column(name = "creation_date")
    private Date creationDate;

    

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties("contacts")  // Ignore la liste des contacts dans la société lors de la sérialisation d'un contact
    private Company company;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("contact") // Ignore le contact lors de la sérialisation d'un besoin
    private List<Besoin> besoins = new ArrayList<>();
}
