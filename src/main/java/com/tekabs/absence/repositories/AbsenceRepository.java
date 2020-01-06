package com.tekabs.absence.repositories;

import java.util.Optional;

import com.tekabs.absence.models.Absence;
import com.tekabs.absence.models.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AbsenceRepository extends CrudRepository<Absence, Long> {
    @Query(value = "SELECT * FROM absence abs where abs.matiere_id = ?1 AND abs.matricule = ?2", nativeQuery = true)
    Optional<Absence> findAbsenceByMatiereAndMatricule(@Param("matiere_id") Long matiere_id,
            @Param("matricule") Long matricule);
}