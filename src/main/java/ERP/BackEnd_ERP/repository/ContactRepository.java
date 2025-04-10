package ERP.BackEnd_ERP.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ERP.BackEnd_ERP.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {


    
}
