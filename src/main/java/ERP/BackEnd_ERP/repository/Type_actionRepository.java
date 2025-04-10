package ERP.BackEnd_ERP.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ERP.BackEnd_ERP.model.Type_action;

@Repository
public interface Type_actionRepository extends JpaRepository<Type_action, Long> {

    Type_action findById(long id);
    List<Type_action> findByBelongTo(String belongTo);
    boolean existsByNameAndBelongTo(String name, String belongTo);
} 