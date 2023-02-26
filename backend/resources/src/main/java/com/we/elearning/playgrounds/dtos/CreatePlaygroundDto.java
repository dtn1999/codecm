package com.we.elearning.playgrounds.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePlaygroundDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Pattern(regexp = "^(http|https)://github.com/.*$")
    private String githubRepoUrl;
    @NotNull
    @Size(min = 3, max = 100)
    private String description;
}