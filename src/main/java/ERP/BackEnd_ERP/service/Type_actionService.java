package ERP.BackEnd_ERP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ERP.BackEnd_ERP.model.Type_action;
import ERP.BackEnd_ERP.repository.Type_actionRepository;

@Service
public class Type_actionService {

    @Autowired
    private Type_actionRepository type_actionRepository;
    
    public Type_action findById(long id) {
        return type_actionRepository.findById(id);
    }
    public List<Type_action> findByBelong_to(String belong_to) {
        return type_actionRepository.findByBelongTo(belong_to);
    }

    public void deleteType_actionById(Long id) {
        type_actionRepository.deleteById(id);
    }
    public void saveType_action(Type_action type_action) {
        type_actionRepository.save(type_action);
    }

    @Transactional
    public Type_action updateType_action(Long id, Type_action t){
        Type_action type_action = type_actionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type_action not found"));

        type_action.setName(t.getName());
        type_action.setBelongTo(t.getBelongTo());
        return type_actionRepository.save(type_action);
    }

    public boolean existsByNameAndBelongTo(String name, String belongTo) {
        return type_actionRepository.existsByNameAndBelongTo(name, belongTo);
    }
    
}
