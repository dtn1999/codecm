package com.we.elearning.templates.services;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.templates.dtos.TemplateDto;
import com.we.elearning.templates.dtos.TemplateMapper;
import com.we.elearning.templates.entities.Template;
import com.we.elearning.templates.repositories.TemplateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TemplatesServiceTest {
    @MockBean
    TemplateRepository templateRepository;

    TemplatesService templatesService;

    @BeforeEach
    void setUp() {
        templatesService = new TemplatesService(templateRepository);
    }

    @Test
    @DisplayName("Should return empty list when no templates are present")
    void getAllTemplates_emptyList() {
        // given
        List<Template> templates = List.of();
        ApiResponse<List<TemplateDto>, ?> expected = ResponseBuilder.success(List.of());
        // when
        when(templateRepository.findAll()).thenReturn(templates);
        // then
        ApiResponse<List<TemplateDto>, ?> result = templatesService.getAllTemplates();
        assertThat(result).isEqualTo(expected);
        verify(templateRepository).findAll();
    }

    @Test
    @DisplayName("Should return list of templates when templates are present")
    void getAllTemplates_notEmptyList() {
        // given
        List<Template> templates = List.of(
                Template.builder()
                        .id(1L)
                        .description("description1")
                        .name("name1")
                        .githubUrl("githubUrl1")
                        .imageUrl("imageUrl1")
                        .build(),
                Template.builder()
                        .id(2L)
                        .description("description2")
                        .name("name2")
                        .githubUrl("githubUrl2")
                        .imageUrl("imageUrl2")
                        .build()
        );
        ApiResponse<List<TemplateDto>, ?> expected = ResponseBuilder
                .success(templates.stream().map(TemplateMapper.INSTANCE::toTemplateDto).toList());
        // when
        when(templateRepository.findAll()).thenReturn(templates);
        // then
        ApiResponse<List<TemplateDto>, ?> result = templatesService.getAllTemplates();
        assertThat(result).isEqualTo(expected);
        verify(templateRepository).findAll();
    }

    @Test
    @DisplayName("Should return template when template with the given Id")
    void getTemplateById() {
        // given
        Template template = Template.builder()
                .id(1L)
                .description("description1")
                .name("name1")
                .githubUrl("githubUrl1")
                .imageUrl("imageUrl1")
                .build();
        ApiResponse<TemplateDto, ?> expected = ResponseBuilder
                .success(TemplateMapper.INSTANCE.toTemplateDto(template));
        // when
        when(templateRepository.findById(1L)).thenReturn(Optional.of(template));
        // then
        ApiResponse<TemplateDto, ?> result = templatesService.getTemplateById(1L);
        assertThat(result).isEqualTo(expected);
        verify(templateRepository).findById(1L);
    }

    @Test
    @DisplayName("Should throw NoSuchElementException when template not found")
    void getTemplateId_notFound() {
        // given
        String expectedMessage = "Template with id 1 not found";
        // when
        when(templateRepository.findById(1L)).thenReturn(Optional.empty());
        // then
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> templatesService.getTemplateById(1L))
                .withMessage(expectedMessage)
        ;
        verify(templateRepository).findById(1L);
    }

    @Test
    @DisplayName("Should create template when template is valid")
    void createTemplate() {
        // given
        Template template = Template.builder()
                .id(1L)
                .description("description1")
                .name("name1")
                .githubUrl("githubUrl1")
                .imageUrl("imageUrl1")
                .build();
        TemplateDto templateDto = TemplateMapper.INSTANCE.toTemplateDto(template);
        ApiResponse<TemplateDto, ?> expected = ResponseBuilder
                .success(templateDto);
        // when
        when(templateRepository.save(template)).thenReturn(template);
        // then
        ApiResponse<TemplateDto, ?> result = templatesService.createTemplate(templateDto);
        assertThat(result).isEqualTo(expected);
        verify(templateRepository).save(template);
    }

//    @Test
    // TODO: Fix this test
    @DisplayName("Should throw if unexpected exception occurs")
    void createTemplate_unexpectedException() {
        // given

        // when
        when(templateRepository.save(any())).thenThrow(new RuntimeException());
        // then
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> templatesService.createTemplate(TemplateDto.builder().build()));
        verify(templateRepository).save(any());
    }

    @Test
    @DisplayName("Should update template when template is valid")
    void updateTemplate() {
        // given
        Long id = 1L;
        Template template = Template.builder()
                .id(id)
                .description("description1")
                .name("name1")
                .githubUrl("githubUrl1")
                .imageUrl("imageUrl1")
                .build();
        TemplateDto updatedTemplateDto = TemplateDto.builder()
                .id(id)
                .description("updated_description1")
                .name("updated_name1")
                .githubRepoUrl("updated_githubUrl1")
                .imageUrl("updated_imageUrl1")
                .build();
        Template updatedTemplate = TemplateMapper.INSTANCE.toTemplate(updatedTemplateDto);
        BeanUtils.copyProperties(updatedTemplate, template);
        ApiResponse<TemplateDto, ?> expected = ResponseBuilder.success();
        // when
        when(templateRepository.findById(id)).thenReturn(Optional.of(template));
        when(templateRepository.save(template)).thenReturn(template);
        // then
        ApiResponse<?, ?> result = templatesService.updateTemplate(id, updatedTemplateDto);
        assertThat(result).isEqualTo(expected);
        verify(templateRepository).findById(id);
        verify(templateRepository).save(template);
    }
    // TODO: test non-successful update

    @Test
    @DisplayName("Should delete template when template is valid")
    void deleteTemplate() {
        // given
        Long id = 1L;
        ApiResponse<?,?> expected = ResponseBuilder.success();
        // when
        // then
        ApiResponse<?, ?> result = templatesService.deleteTemplate(id);
        assertThat(result).isEqualTo(expected);
        verify(templateRepository).deleteById(id);
    }
}