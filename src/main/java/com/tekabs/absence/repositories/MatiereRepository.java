package com.tekabs.absence.repositories;

import com.tekabs.absence.models.Matiere;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MatiereRepository extends CrudRepository<Matiere, Long> {

}