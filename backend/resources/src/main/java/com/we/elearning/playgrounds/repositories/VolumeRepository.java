package com.we.elearning.playgrounds.repositories;

import com.we.elearning.playgrounds.entities.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolumeRepository extends JpaRepository<Volume, Long> {
}
