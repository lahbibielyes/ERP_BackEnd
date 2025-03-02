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
    private String name;

    @Column(nullable = false)
    private String activitysector;

    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int phone;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonManagedReference // Empêche la récursion infinie
    private List<Contact> contacts = new ArrayList<>();
}
