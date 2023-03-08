package com.we.elearning.playgrounds.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.playgrounds.dtos.CreatePlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundMapper;
import com.we.elearning.playgrounds.entities.Playground;
import com.we.elearning.playgrounds.exceptions.PlaygroundNotCreatedException;
import com.we.elearning.playgrounds.repositories.PlaygroundRepository;
import com.we.elearning.playgrounds.webclients.WorkspaceManagerService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlaygroundServiceTest {
    static MockWebServer mockWorkspacemanagerServer;
    @MockBean
    PlaygroundRepository playgroundRepository;
    PlaygroundService playgroundService;
    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeAll
    static void setUp() throws IOException {
        mockWorkspacemanagerServer = new MockWebServer();
        mockWorkspacemanagerServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWorkspacemanagerServer.shutdown();
    }

    @BeforeEach
    void beforeEach() {
        String baseUrl = String.format("http://localhost:%s", mockWorkspacemanagerServer.getPort());
        WebClient webClient = WebClient.create(baseUrl);
        WorkspaceManagerService workspaceService = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build()
                .createClient(WorkspaceManagerService.class);
        playgroundService = new PlaygroundService(playgroundRepository, workspaceService);
    }

    @Test
    @DisplayName("Should return list of playgrounds when playgrounds are present")
    void getAllPlaygrounds() {
        // given
        List<Playground> playgrounds = List.of(
                Playground.builder()
                        .id(1L)
                        .name("name1")
                        .description("description1")
                        .instanceUrl("instanceUrl1")
                        .githubRepoUrl("https://github/user/repo1.gt")
                        .instanceUrl("https://instance1.com")
                        .build(),
                Playground.builder()
                        .id(2L)
                        .name("name2")
                        .description("description2")
                        .githubRepoUrl("https://github/user/repo2.gt")
                        .instanceUrl("https://instance2.com")
                        .build()
        );
        List<PlaygroundDto> playgroundDtos = playgrounds
                .stream()
                .map(PlaygroundMapper.INSTANCE::toPlaygroundDto)
                .toList();

        ApiResponse expected = ResponseBuilder.success(playgroundDtos);
        // when
        when(playgroundRepository.findAll()).thenReturn(playgrounds);
        // then
        ApiResponse result = playgroundService.getAllPlaygrounds();
        assertThat(result).isEqualTo(expected);
        verify(playgroundRepository).findAll();
    }

    @Test
    @DisplayName("Should return empty list when there is no playgrounds")
    void getAllPlaygrounds_emptyList() {
        // given
        List<PlaygroundDto> playgroundDtos = List.of();
        List<Playground> playgrounds = List.of();
        ApiResponse expected = ResponseBuilder.success(playgroundDtos);
        // when
        when(playgroundRepository.findAll()).thenReturn(playgrounds);
        // then
        ApiResponse result = playgroundService.getAllPlaygrounds();
        assertThat(result).isEqualTo(expected);
        verify(playgroundRepository).findAll();
    }

    @Test
    void getPlaygroundById() {
        // given
        Playground playground = Playground.builder()
                .id(1L)
                .name("name1")
                .description("description1")
                .githubRepoUrl("https://github/user/repo1.gt")
                .instanceUrl("https://instance1.com")
                .build();
        PlaygroundDto playgroundDto = PlaygroundMapper.INSTANCE.toPlaygroundDto(playground);
        ApiResponse expected = ResponseBuilder.success(playgroundDto);
        // when
        when(playgroundRepository.findById(1L)).thenReturn(java.util.Optional.of(playground));
        // then
        ApiResponse result = playgroundService.getPlaygroundById(1L);
        assertThat(result).isEqualTo(expected);
        verify(playgroundRepository).findById(1L);
    }

    @Test
    @DisplayName("Should return not found when playground is not found")
    void getPlaygroundById_notFound() {
        // given
        String expectedErrorMessage = String.format("Playground with id %s not found", 1L);
        // when
        when(playgroundRepository.findById(any())).thenReturn(Optional.empty());
        // then
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> playgroundService.getPlaygroundById(1L));
        verify(playgroundRepository).findById(1L);
    }

    @Test
    @DisplayName("Should create playground and return created playground")
    void createPlayground() throws JsonProcessingException, InterruptedException {
        // given
        long expectedWorkspaceId = 1L;
        String expectedWorkspaceHost = "instance1";
        int expectedWorkspacePort = 8000;
        String expectedWorkspaceUrl = String.format("http://%s:%s", expectedWorkspaceHost, expectedWorkspacePort);

        CreatePlaygroundDto createPlaygroundDto = CreatePlaygroundDto.builder()
                .name("name1")
                .description("description1")
                .githubRepoUrl("https://github/user/repo1.gt")
                .build();

        Map<String, Object> createWorkspaceRequest = Map.of(
                "githubRepoUrl", createPlaygroundDto.getGithubRepoUrl()
        );

        ApiResponse createWorkspaceResponse = ResponseBuilder.success(
                Map.of(
                        "id", expectedWorkspaceId,
                        "runnerId", "runner1",
                        "host", expectedWorkspaceHost,
                        "port", expectedWorkspacePort,
                        "status", "RUNNING",
                        "workspaceVolume", Map.of(
                                "id", 1L,
                                "name", "workspace_volume_name",
                                "size", 1000L
                        )
                )
        );

        Playground playground = Playground.builder()
                .name(createPlaygroundDto.getName())
                .description(createPlaygroundDto.getDescription())
                .githubRepoUrl(createPlaygroundDto.getGithubRepoUrl())
                .workspaceId(expectedWorkspaceId)
                .instanceUrl(expectedWorkspaceUrl)
                .build();
        Playground savedPlayground = Playground.builder().build();
        BeanUtils.copyProperties(playground, savedPlayground);
        savedPlayground.setId(1L);
        PlaygroundDto playgroundDto = PlaygroundMapper.INSTANCE.toPlaygroundDto(savedPlayground);
        playgroundDto.setId(1L);
        ApiResponse expected = ResponseBuilder.success(playgroundDto);
        // when
        mockWorkspacemanagerServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(objectMapper.writeValueAsString(createWorkspaceResponse))
                .addHeader("Content-Type", "application/json")
        );

        when(playgroundRepository.save(playground)).thenReturn(savedPlayground);
        // then
        Mono<ApiResponse> result = playgroundService.createPlayground(createPlaygroundDto, null);
        assertThat(result.block()).isEqualTo(expected);

        RecordedRequest recordedRequest = mockWorkspacemanagerServer.takeRequest();
        assertThat(recordedRequest.getMethod()).isEqualTo("POST");
        assertThat(recordedRequest.getPath()).isEqualTo("/v1/workspacemanager/workspaces");
        String requestBody = new String(recordedRequest.getBody().readByteArray(), StandardCharsets.UTF_8);
        assertThat(requestBody).isEqualTo(objectMapper.writeValueAsString(createWorkspaceRequest));
        verify(playgroundRepository).save(playground);
    }

    @Test
    @DisplayName("Should Throw when workspace manager returns error")
    void createPlayground_workspaceManagerBadRequest() throws JsonProcessingException, InterruptedException {
        // given
        CreatePlaygroundDto createPlaygroundDto = CreatePlaygroundDto.builder()
                .name("name1")
                .description("description1")
                .githubRepoUrl("https://wrong-git-hub/user/repo1.gt")
                .build();

        Map<String, Object> createWorkspaceRequest = Map.of(
                "githubRepoUrl", createPlaygroundDto.getGithubRepoUrl()
        );

        ApiResponse createWorkspaceResponse = ResponseBuilder.error(
                Map.of("error", List.of("github"))
        );
        // when
        mockWorkspacemanagerServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(objectMapper.writeValueAsString(createWorkspaceResponse))
                .addHeader("Content-Type", "application/json")
        );
        // then
        assertThatExceptionOfType(PlaygroundNotCreatedException.class).isThrownBy(() -> playgroundService.createPlayground(createPlaygroundDto, null));
        RecordedRequest recordedRequest = mockWorkspacemanagerServer.takeRequest();
        assertThat(recordedRequest.getMethod()).isEqualTo("POST");
        assertThat(recordedRequest.getPath()).isEqualTo("/api/v1/workspacemanager/workspaces");
        String requestBody = new String(recordedRequest.getBody().readByteArray(), StandardCharsets.UTF_8);
        assertThat(requestBody).isEqualTo(objectMapper.writeValueAsString(createWorkspaceRequest));
        verify(playgroundRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should successfully delete playground")
    void deletePlayground() throws InterruptedException {
        // given
        long expectedWorkspaceId = 1L;
        String expectedWorkspaceHost = "instance1";
        int expectedWorkspacePort = 8000;
        long playgroundId = 1L;
        String expectedWorkspaceUrl = String.format("http://%s:%s", expectedWorkspaceHost, expectedWorkspacePort);

        Playground playground = Playground.builder()
                .id(playgroundId)
                .name("name1")
                .description("description1")
                .githubRepoUrl("https://github/user/repo1.gt")
                .workspaceId(expectedWorkspaceId)
                .instanceUrl(expectedWorkspaceUrl)
                .build();
        // when
        mockWorkspacemanagerServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("")
                .addHeader("Content-Type", "application/json")
        );
        when(playgroundRepository.findById(playgroundId)).thenReturn(Optional.of(playground));
        // then
        ApiResponse result = playgroundService.deletePlayground(playgroundId);
        assertThat(result).isEqualTo(ResponseBuilder.success());
        RecordedRequest recordedRequest = mockWorkspacemanagerServer.takeRequest();
        assertThat(recordedRequest.getMethod()).isEqualTo("DELETE");
        assertThat(recordedRequest.getPath()).isEqualTo(String.format("/api/v1/workspacemanager/workspaces/%s",
                expectedWorkspaceId));
        verify(playgroundRepository).findById(playgroundId);
        verify(playgroundRepository).delete(playground);
    }

    @Test
    @DisplayName("Should Throw when workspace manager returns with error")
    void deletePlayground_workspaceManagerRespondWithError() throws InterruptedException {
        // given
        long expectedWorkspaceId = 1L;
        String expectedWorkspaceHost = "instance1";
        int expectedWorkspacePort = 8000;
        long playgroundId = 1L;
        String expectedWorkspaceUrl = String.format("http://%s:%s", expectedWorkspaceHost, expectedWorkspacePort);

        Playground playground = Playground.builder()
                .id(playgroundId)
                .name("name1")
                .description("description1")
                .githubRepoUrl("https://github/user/repo1.gt")
                .workspaceId(expectedWorkspaceId)
                .instanceUrl(expectedWorkspaceUrl)
                .build();
        // when
        mockWorkspacemanagerServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("")
                .addHeader("Content-Type", "application/json")
        );
        when(playgroundRepository.findById(playgroundId)).thenReturn(Optional.of(playground));
        // then
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> playgroundService.deletePlayground(playgroundId));
        RecordedRequest recordedRequest = mockWorkspacemanagerServer.takeRequest();
        assertThat(recordedRequest.getMethod()).isEqualTo("DELETE");
        assertThat(recordedRequest.getPath()).isEqualTo(String.format("/api/v1/workspacemanager/workspaces/%s",
                expectedWorkspaceId));
        verify(playgroundRepository).findById(playgroundId);
        verify(playgroundRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Should Throw when playground does not exists")
    void deletePlayground_playgroundWithIdDoesNotExists() {
        // given
        long playgroundId = 1L;
        when(playgroundRepository.findById(playgroundId)).thenReturn(Optional.empty());
        // then
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> playgroundService.deletePlayground(playgroundId));
        verify(playgroundRepository).findById(playgroundId);
        verify(playgroundRepository, never()).delete(any());
    }
}