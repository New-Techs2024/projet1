package com.jee.projet1.repository;


import com.jee.projet1.model.Medicament;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MedicamentRepository extends CrudRepository<Medicament, Long> {

	List<Medicament> findByNomContainingIgnoreCase(String searchTerm);
}
