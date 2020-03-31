package com.buk.inzynier.HangMan.repositories;


import com.buk.inzynier.HangMan.domain.entities.ActualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualRepository extends JpaRepository<ActualEntity,Long> {
}
