package com.TMC.WebFootballers.Repositories;

import com.TMC.WebFootballers.Entities.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer,Long> {
}
