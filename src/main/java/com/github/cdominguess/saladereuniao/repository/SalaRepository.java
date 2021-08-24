package com.github.cdominguess.saladereuniao.repository;

import com.github.cdominguess.saladereuniao.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
}