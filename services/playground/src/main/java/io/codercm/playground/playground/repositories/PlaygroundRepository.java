package io.codercm.playground.playground.repositories;

import io.codercm.playground.playground.entities.Playground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaygroundRepository extends JpaRepository<Playground, UUID> {
}