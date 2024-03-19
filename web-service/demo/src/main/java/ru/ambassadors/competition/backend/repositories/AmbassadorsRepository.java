package ru.ambassadors.competition.backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ambassadors.competition.backend.entities.AmbassadorEntity;

import java.util.List;

@Repository
public interface AmbassadorsRepository extends CrudRepository<AmbassadorEntity, Integer> {
    List<AmbassadorEntity> findByOrderById(Pageable pageable);
    List<AmbassadorEntity> findByCategoryOrderById(Integer category, Pageable pageable);
    List<AmbassadorEntity> findByWinnerTrue();
}
