package ERP.BackEnd_ERP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ERP.BackEnd_ERP.model.historique_besoin;

@Repository
public interface HistoriqueBesoinRepository extends JpaRepository<historique_besoin, Long> {
    
    List<historique_besoin> findByBesoinId(Long besoinId);
    List<historique_besoin> findByActionBy(Long actionBy);
    boolean existsByBesoinId(Long besoinId);
}
