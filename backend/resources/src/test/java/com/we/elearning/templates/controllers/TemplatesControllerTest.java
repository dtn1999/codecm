package com.we.elearning.templates.controllers;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.templates.dtos.TemplateDto;
import com.we.elearning.templates.dtos.TemplateMapper;
import com.we.elearning.templates.entities.Template;
import com.we.elearning.templates.services.TemplatesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest(TemplatesController.class)
class TemplatesControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    TemplatesService templatesService;

    @Test
    @DisplayName("Should return empty list when no templates are present")
    void getAllTemplates_emptyList(){
        // given
        // when
        when(templatesService.getAllTemplates()).thenReturn(ResponseBuilder.success(List.of()));
        // then
        webTestClient
                .get()
                .uri("/api/v1/resources/templates")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse<List<TemplateDto>, ?>>() {
                })
                .isEqualTo(ResponseBuilder.success(List.of()));
        verify(templatesService).getAllTemplates();
    }


    @Test
    @DisplayName("Should return list of templates when templates are present")
    void getAllTemplates_nonEmptyList() {
        // given
        List<TemplateDto> templateDtos = List.of(
                TemplateDto.builder()
                        .id(1L)
                        .description("description1")
                        .name("name1")
                        .githubUrl("githubUrl1")
                        .build(),
                TemplateDto.builder()
                        .id(2L)
                        .description("description2")
                        .name("name2")
                        .githubUrl("githubUrl2")
                        .build()
        );

        // when
        when(templatesService.getAllTemplates()).thenReturn(ResponseBuilder.success(templateDtos));
        // then
        webTestClient
                .get()
                .uri("/api/v1/resources/templates")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse<List<TemplateDto>, ?>>() {
                })
                .isEqualTo(ResponseBuilder.success(templateDtos));
        verify(templatesService).getAllTemplates();
    }

    @Test
    @DisplayName("Should return template when template is present")
    void getTemplateById() {
        // given
        TemplateDto templateDto = TemplateDto.builder()
                .id(1L)
                .description("description1")
                .name("name1")
                .githubUrl("githubUrl1")
                .build();
        // when
        when(templatesService.getTemplateById(1L)).thenReturn(ResponseBuilder.success(templateDto));
        // then
        webTestClient
                .get()
                .uri("/api/v1/resources/templates/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ApiResponse<TemplateDto, ?>>() {
                })
                .isEqualTo(ResponseBuilder.success(templateDto));
        verify(templatesService).getTemplateById(1L);
    }

    @Test
    @DisplayName("Should return 404 when template is not present")
    void getTemplateById_notFound() {
        // given
        String expectedMessage = "Template with id 1 not found";
        // when
        when(templatesService.getTemplateById(1L)).thenThrow(new NoSuchElementException(expectedMessage));
        // then
        webTestClient
                .get()
                .uri("/api/v1/resources/templates/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(new ParameterizedTypeReference<ApiResponse<TemplateDto, ?>>() {
                })
                .consumeWith(response -> {
                    ApiResponse<TemplateDto, ?> responseBody = response.getResponseBody();
                    assertThat(responseBody).isNotNull();
                    assertThat(responseBody.isSuccess()).isFalse();
                    assertThat(responseBody.getData()).isNull();
                    assertThat(responseBody.getMessage()).isEqualTo(expectedMessage);
                });
        verify(templatesService).getTemplateById(1L);
    }


    @Test
    @DisplayName("Should create and return template")
    void createTemplate() {
        // given
        TemplateDto templateDto = TemplateDto.builder()
                .description("description1")
                .name("name1")
                .githubUrl("githubUrl1")
                .build();
        Template template = TemplateMapper.INSTANCE.toTemplate(templateDto);
        template.setId(1L);
        // when
        when(templatesService.createTemplate(templateDto))
                .thenReturn(ResponseBuilder.success(TemplateMapper.INSTANCE.toTemplateDto(template)));
        // then
        webTestClient
                .post()
                .uri("/api/v1/resources/templates")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(templateDto)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(new ParameterizedTypeReference<ApiResponse<TemplateDto, ?>>() {
                })
                .isEqualTo(ResponseBuilder.success(TemplateMapper.INSTANCE.toTemplateDto(template)));
        verify(templatesService).createTemplate(templateDto);
    }


    @Test
    @DisplayName("Should return 400 when template is not valid")
    void createTemplate_withBadRequest() {
        // given
        TemplateDto templateDto = TemplateDto.builder().build();
        String expectedMessage = "Make sure you have filled all the required fields with valid values";
        // when
        // then
        webTestClient
                .post()
                .uri("/api/v1/resources/templates")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(templateDto)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(new ParameterizedTypeReference<ApiResponse<TemplateDto, ?>>() {
                })
                .consumeWith(response -> {
                            ApiResponse<TemplateDto, ?> responseBody = response.getResponseBody();
                            assertThat(responseBody).isNotNull();
                            assertThat(responseBody.isSuccess()).isFalse();
                            assertThat(responseBody.getData()).isNull();
                            assertThat(responseBody.getMessage()).isEqualTo(expectedMessage);
                            assertThat(responseBody.getError()).isNotNull();
                        }
                )
        ;

    }

    @Test
    @DisplayName("Should update template")
    void updateTemplate() {
    }

    @Test
    @DisplayName("Should delete template")
    void deleteTemplate() {
    }
}