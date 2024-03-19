package com.shulmin.pavel.russiangeographicalsociety.repositories;

import com.shulmin.pavel.russiangeographicalsociety.entity.AmbassadorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmbassadorsRepository extends CrudRepository<AmbassadorEntity, Integer> {
    Optional<AmbassadorEntity> findFirstByChatId(Long chatId);
}
