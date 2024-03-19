package com.shulmin.pavel.russiangeographicalsociety.repositories;

import com.shulmin.pavel.russiangeographicalsociety.entity.BotStateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotStatesRepository extends CrudRepository<BotStateEntity, Long> {
}
