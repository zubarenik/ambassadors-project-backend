package ru.ambassadors.competition.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ambassadors.competition.backend.entities.UserEntity;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Integer> {
}
