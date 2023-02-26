package com.we.elearning.playgrounds.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "VOLUMES")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Volume {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String size;
}
