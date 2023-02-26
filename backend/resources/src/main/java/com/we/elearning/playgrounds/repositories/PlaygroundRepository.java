package com.we.elearning.playgrounds.repositories;

import com.we.elearning.playgrounds.entities.Playground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
}
