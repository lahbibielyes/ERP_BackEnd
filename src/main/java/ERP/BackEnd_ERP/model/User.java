package ERP.BackEnd_ERP.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "users")
public class User {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String firstname;

        @Column(nullable = false)
        private String lastname;
    
        @Column(unique = true, nullable = false)
        private String username;

        @Column(nullable = false)
        private String role;

        @Column(nullable = false)
        private int phone;
    
        @Column(nullable = false)
        private String password;

        @Column(unique = true, nullable = false)
        private String email;

        @OneToMany(mappedBy = "managerResponsable",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        @JsonIgnoreProperties("managerResponsable")
        private List<Besoin> besoins;

        @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        @JsonIgnoreProperties("manager")
        private List<Action_besoin> action_besoins;

    
       
    
    
}
