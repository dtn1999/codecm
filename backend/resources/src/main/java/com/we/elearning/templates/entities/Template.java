package com.we.elearning.templates.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TEMPLATES")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Template {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @NotNull
    @NotBlank
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Size(min = 10, max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Column(unique = true,name = "GITHUB_URL")
    @NotNull
    private String githubRepoUrl;
}
