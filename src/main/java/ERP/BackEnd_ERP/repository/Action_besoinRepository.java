package ERP.BackEnd_ERP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ERP.BackEnd_ERP.model.Action_besoin;

@Repository
public interface Action_besoinRepository extends JpaRepository<Action_besoin, Long> {
    
    public List<Action_besoin> findAllByBesoinId(Long id);
}
