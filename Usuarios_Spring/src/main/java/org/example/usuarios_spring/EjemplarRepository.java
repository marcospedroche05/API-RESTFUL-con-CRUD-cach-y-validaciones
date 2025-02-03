package org.example.usuarios_spring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends CrudRepository<Ejemplar, Integer> {

}
