package com.shulmin.pavel.russiangeographicalsociety.repositories;

import com.shulmin.pavel.russiangeographicalsociety.entity.Photo;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PhotosRepository extends CrudRepository<Photo, Integer> {
    void deleteAllByAmbassador(Integer ambassador);
}
