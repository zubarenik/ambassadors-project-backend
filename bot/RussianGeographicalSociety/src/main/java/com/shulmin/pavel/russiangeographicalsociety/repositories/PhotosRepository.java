package com.shulmin.pavel.russiangeographicalsociety.repositories;

import com.shulmin.pavel.russiangeographicalsociety.entity.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepository extends CrudRepository<Photo, Integer> {
}
