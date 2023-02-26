package com.we.elearning.templates.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemplateDto {
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Size(min = 10, max = 1000)
    private String description;
    private String imageUrl;
    @NotNull
    private String githubUrl;
}
