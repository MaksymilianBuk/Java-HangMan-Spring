package com.buk.inzynier.HangMan.repositories;

import com.buk.inzynier.HangMan.domain.entities.PuzzleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuzzleRepository extends JpaRepository<PuzzleEntity,Long> {
}
