package ERP.BackEnd_ERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ERP.BackEnd_ERP.model.Besoin;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface BesoinRepository  extends JpaRepository<Besoin, Long> {

    List<Besoin> findByTitle(String title);
    Optional<Besoin> findById(Long id);
    List<Besoin> findByCreationDate(Date date);
    List<Besoin> findByStatus(String status);
    @Query("SELECT b FROM Besoin b WHERE b.contact.id = :contactId")
    List<Besoin> findByContactId(@Param("contactId") Long contactId);

    @Query("select status from Besoin")
    List<String> findStatus();


    
    
}
