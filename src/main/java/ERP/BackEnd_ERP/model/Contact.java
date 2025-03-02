package ERP.BackEnd_ERP.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne()
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonBackReference // Empêche la récursion infinie
    private Company company;
}
