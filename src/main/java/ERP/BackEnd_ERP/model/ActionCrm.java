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

public class ActionCrm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String type;
   private String Manager;
   private Date date;
   private String description;
   
}
