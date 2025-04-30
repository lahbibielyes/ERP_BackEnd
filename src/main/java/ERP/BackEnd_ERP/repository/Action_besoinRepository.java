package ERP.BackEnd_ERP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ERP.BackEnd_ERP.model.Action_besoin;

@Repository
public interface Action_besoinRepository extends JpaRepository<Action_besoin, Long> {
    
    public List<Action_besoin> findAllByBesoinId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Action_besoin a where a.id = ?1")
    public void deleteAction(Long id);
}
