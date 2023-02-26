package com.we.elearning.playgrounds.dtos;

import com.we.elearning.playgrounds.entities.Playground;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class PlaygroundMapperTest {
    long id = 1L;
    String name = "Playground 1";
    String description = "Playground 1 description";
    long workspaceId = 1L;
    String githubRepoUrl = "https://github.com/username/repo";
    String instanceUrl = "https://playground1.com";
    @Test
    @DisplayName("Should map Playground to PlaygroundDto")
    void toPlaygroundDto() {
        Playground playground = Playground.builder()
                .id(id)
                .name(name)
                .description(description)
                .workspaceId(workspaceId)
                .githubRepoUrl(githubRepoUrl)
                .instanceUrl(instanceUrl)
                .build();
        PlaygroundDto playgroundDto = PlaygroundMapper.INSTANCE.toPlaygroundDto(playground);
        assertThat(playgroundDto.getId()).isEqualTo(id);
        assertThat(playgroundDto.getName()).isEqualTo(name);
        assertThat(playgroundDto.getDescription()).isEqualTo(description);
        assertThat(playgroundDto.getWorkspaceId()).isEqualTo(workspaceId);
        assertThat(playgroundDto.getGithubRepoUrl()).isEqualTo(githubRepoUrl);
        assertThat(playgroundDto.getInstanceUrl()).isEqualTo(instanceUrl);
    }

    @Test
    void toPlayground() {
        PlaygroundDto playgroundDto = PlaygroundDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .workspaceId(workspaceId)
                .githubRepoUrl(githubRepoUrl)
                .instanceUrl(instanceUrl)
                .build();
        Playground playground = PlaygroundMapper.INSTANCE.toPlayground(playgroundDto);
        assertThat(playground.getId()).isEqualTo(id);
        assertThat(playground.getName()).isEqualTo(name);
        assertThat(playground.getDescription()).isEqualTo(description);
        assertThat(playground.getWorkspaceId()).isEqualTo(workspaceId);
        assertThat(playground.getGithubRepoUrl()).isEqualTo(githubRepoUrl);
        assertThat(playground.getInstanceUrl()).isEqualTo(instanceUrl);
    }
}