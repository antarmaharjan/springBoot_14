package me.ratna.spriingboot14.repositories;

import me.ratna.spriingboot14.models.Director;
import me.ratna.spriingboot14.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Long> {

}
