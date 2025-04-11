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

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)  
    private String email;

    @Column(nullable = false)
    private int phone;

    @Column(nullable = false)
    private String function;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties("contacts")  // Ignore la liste des contacts dans la société lors de la sérialisation d'un contact
    private Company company;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("contact") // Ignore le contact lors de la sérialisation d'un besoin
    private List<Besoin> besoins = new ArrayList<>();
}
