package me.ratna.spriingboot14.repositories;

import me.ratna.spriingboot14.models.Director;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<Director,Long>{
    Director findFirstByNameContains(String partialString);
    Iterable<Director> findAllByNameContains(String partialString);
}
