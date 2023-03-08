package com.we.elearning.playgrounds.repositories;

import com.we.elearning.playgrounds.entities.Playground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
    List<Playground> findAllByUserId(String userId);
}
