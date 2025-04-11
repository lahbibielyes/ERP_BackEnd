package ERP.BackEnd_ERP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ERP.BackEnd_ERP.model.historique_besoin;
import ERP.BackEnd_ERP.repository.HistoriqueBesoinRepository;

@Service
public class HistoriqueBesoinService {

    @Autowired
    private HistoriqueBesoinRepository historiqueBesoinRepository;
    
    public void deleteHistoriqueBesoin(Long id) {
        historiqueBesoinRepository.deleteById(id);
    }

    public List<historique_besoin> findByBesoinId(Long besoinId) {
        return historiqueBesoinRepository.findByBesoinId(besoinId);
    }

    public List<historique_besoin> findByActionBy(Long actionBy) {
        return historiqueBesoinRepository.findByActionBy(actionBy);
    }

    public historique_besoin saveHistoriqueBesoin(historique_besoin historiqueBesoin) {
        return historiqueBesoinRepository.save(historiqueBesoin);
    }

    public boolean existsByBesoinId(Long besoinId) {
        return historiqueBesoinRepository.existsByBesoinId(besoinId);
    }
    
}
