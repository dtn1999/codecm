package com.we.elearning.playgrounds.controllers;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.playgrounds.dtos.CreatePlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundDto;
import com.we.elearning.playgrounds.exceptions.PlaygroundManagementException;
import com.we.elearning.playgrounds.services.PlaygroundService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@WebFluxTest(PlaygroundController.class)
class PlaygroundControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    PlaygroundService playgroundService;

    @Test
    @DisplayName("Get all playgrounds")
    void getAllPlaygrounds() {
        // given
        List<PlaygroundDto> playgroundDtos = List.of(
                PlaygroundDto.builder()
                        .id(1L)
                        .name("Playground 1")
                        .description("Description 1")
                        .githubRepoUrl("https://github.com/username/repo1.git")
                        .instanceUrl("https://instance1.com")
                        .workspaceId(1L)
                        .build(),
                PlaygroundDto.builder()
                        .id(2L)
                        .name("Playground 2")
                        .description("Description 2")
                        .githubRepoUrl("https://github.com/username/repo2.git")
                        .instanceUrl("https://instance2.com")
                        .workspaceId(2L)
                        .build()
        );
        // when
        when(playgroundService.getAllPlaygrounds()).thenReturn(Mono.just(ResponseBuilder.success(playgroundDtos)));
        // then
        webTestClient.get().uri("/api/v1/resources/playgrounds")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .isEqualTo(ResponseBuilder.success(playgroundDtos));
        verify(playgroundService).getAllPlaygrounds();
    }

    @Test
    @DisplayName("Get all playgrounds with empty list")
    void getAllPlaygrounds_emptyList() {
        // given

        // when
        when(playgroundService.getAllPlaygrounds()).thenReturn(Mono.just(ResponseBuilder.success(List.of())));
        // then
        webTestClient.get().uri("/api/v1/resources/playgrounds")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .isEqualTo(ResponseBuilder.success(List.of()));
        verify(playgroundService).getAllPlaygrounds();
    }

    @Test
    @DisplayName("Get playground by id")
    void getPlaygroundById() {
        // given
        long playgroundId = 1L;
        PlaygroundDto playgroundDto = PlaygroundDto.builder()
                .id(playgroundId)
                .name("Playground 1")
                .description("Description 1")
                .githubRepoUrl("https://github.com/username/repo1.git")
                .instanceUrl("https://instance1.com")
                .workspaceId(1L)
                .build();
        // when
        when(playgroundService.getPlaygroundById(playgroundId)).thenReturn(Mono.just(ResponseBuilder.success(playgroundDto)));
        // then
        webTestClient.get().uri(String.format("/api/v1/resources/playgrounds/%s", playgroundId))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .isEqualTo(ResponseBuilder.success(playgroundDto));
        verify(playgroundService).getPlaygroundById(playgroundId);
    }

    @Test
    @DisplayName("Get playground by id not found")
    void getPlaygroundById_notFound() {
        // given
        long playgroundId = 1L;
        String expectedMessage = String.format("No playground found with id: %d", playgroundId);
        // when
        when(playgroundService.getPlaygroundById(playgroundId)).thenThrow( new NoSuchElementException(expectedMessage));
        // then
        webTestClient.get().uri(String.format("/api/v1/resources/playgrounds/%s", playgroundId))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .isEqualTo(ResponseBuilder.error(expectedMessage, expectedMessage));
        verify(playgroundService).getPlaygroundById(playgroundId);
    }

    @Test
    @DisplayName("Create playground")
    void createPlayground() {
        // given
        CreatePlaygroundDto createPlaygroundDto = CreatePlaygroundDto.builder()
                .name("Playground 1")
                .description("Description 1")
                .githubRepoUrl("https://github.com/username/repo1.git")
                .build();

        PlaygroundDto playgroundDto = PlaygroundDto.builder()
                .id(1L)
                .name("Playground 1")
                .description("Description 1")
                .githubRepoUrl("https://github.com/username/repo1.git")
                .instanceUrl("https://instance1.com")
                .workspaceId(1L)
                .build();
        // when
        when(playgroundService.createPlayground(createPlaygroundDto, null)).thenReturn(Mono.just(ResponseBuilder.success(playgroundDto)));
        // then
        webTestClient
                .post()
                .uri("/api/v1/resources/playgrounds")
                .bodyValue(createPlaygroundDto)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .isEqualTo(ResponseBuilder.success(playgroundDto));
        verify(playgroundService).createPlayground(createPlaygroundDto, null);
    }

    @Test
    @DisplayName("Create playground fails with bad request")
    void createPlayground_badRequest() {
        // given
        CreatePlaygroundDto createPlaygroundDto = CreatePlaygroundDto.builder()
                .name("Playground 1")
                .description("Description 1")
                .githubRepoUrl("https://not-github.com/username/repo1.git")
                .build();
        // when

        // then
        webTestClient
                .post()
                .uri("/api/v1/resources/playgrounds")
                .bodyValue(createPlaygroundDto)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .consumeWith(response -> {
                    ApiResponse responseBody = response.getResponseBody();
                    assertThat(responseBody).isNotNull();
                    assertThat(responseBody.isSuccess()).isFalse();
                    assertThat(responseBody.getData()).isNull();
                    assertThat(responseBody.getError()).isNotNull();
                });
        verify(playgroundService, never()).createPlayground(createPlaygroundDto, null);
    }

    @Test
    @DisplayName("Should not create playground because service throw exception PlaygroundManagementException")
    void createPlayground_playgroundNotCreatedException(){
        // given
        CreatePlaygroundDto createPlaygroundDto = CreatePlaygroundDto.builder()
                .name("Playground 1")
                .description("Description 1")
                .githubRepoUrl("https://github.com/username/repo1.git")
                .build();
        // when
        when(playgroundService.createPlayground(createPlaygroundDto, null)).thenThrow(new PlaygroundManagementException("Playground not created"));
        // then
        webTestClient
                .post()
                .uri("/api/v1/resources/playgrounds")
                .bodyValue(createPlaygroundDto)
                .exchange()
                .expectStatus()
                .is5xxServerError()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .consumeWith(response -> {
                    ApiResponse responseBody = response.getResponseBody();
                    assertThat(responseBody).isNotNull();
                    assertThat(responseBody.isSuccess()).isFalse();
                    assertThat(responseBody.getData()).isNull();
                    assertThat(responseBody.getError()).isNotNull();
                });
    }


    @Test
    @DisplayName("Delete playground")
    void deletePlayground() {
        // given
        long playgroundId = 1L;
        // when
        when(playgroundService.deletePlayground(playgroundId)).thenReturn(Mono.just(ResponseBuilder.success()));
        // then
        webTestClient
                .delete()
                .uri(String.format("/api/v1/resources/playgrounds/%s", playgroundId))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .isEqualTo(ResponseBuilder.success());
        verify(playgroundService).deletePlayground(playgroundId);
    }

    @Test
    @DisplayName("Should not Delete playground because not found")
    void deletePlayground_notFound() {
        // given
        long playgroundId = 1L;
        String exceptionMessage = String.format("No playground found with id: %d", playgroundId);
        // when
        when(playgroundService.deletePlayground(playgroundId)).thenThrow(new NoSuchElementException(exceptionMessage));
        // then
        webTestClient
                .delete()
                .uri(String.format("/api/v1/resources/playgrounds/%s", playgroundId))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .isEqualTo(ResponseBuilder.error(exceptionMessage, exceptionMessage));
        verify(playgroundService).deletePlayground(playgroundId);
    }

    @Test
    @DisplayName("Should not Delete playground because service throws RuntimeException")
    void deletePlayground_workspaceManager() {
        // given
        long playgroundId = 1L;
        // when
        when(playgroundService.deletePlayground(playgroundId)).thenThrow(new RuntimeException("Error"));
        // then
        webTestClient
                .delete()
                .uri(String.format("/api/v1/resources/playgrounds/%s", playgroundId))
                .exchange()
                .expectStatus()
                .is5xxServerError()
                .expectBody(new ParameterizedTypeReference<ApiResponse>() {
                })
                .consumeWith(response -> {
                    ApiResponse responseBody = response.getResponseBody();
                    assertThat(responseBody).isNotNull();
                    assertThat(responseBody.isSuccess()).isFalse();
                    assertThat(responseBody.getData()).isNull();
                    assertThat(responseBody.getError()).isNotNull();
                });
        verify(playgroundService).deletePlayground(playgroundId);
    }
}