package ERP.BackEnd_ERP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ERP.BackEnd_ERP.model.Action_crm;

@Repository
public interface Action_crmRepository extends JpaRepository<Action_crm, Long> {
    
    public List<Action_crm> findAllByContactId(Long id);
}
