package com.tekabs.absence.repositories;

import com.tekabs.absence.models.Groupe;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GroupeRepository extends CrudRepository<Groupe, Long> {

}