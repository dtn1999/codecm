package com.we.elearning.templates.services;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.templates.dtos.TemplateDto;
import com.we.elearning.templates.dtos.TemplateMapper;
import com.we.elearning.templates.entities.Template;
import com.we.elearning.templates.repositories.TemplateRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TemplatesService {
    private final TemplateRepository templateRepository;

    /***
     * TODO: Add documentation
     * @return
     */
    public ApiResponse<List<TemplateDto>, ?> getAllTemplates() {
        List<TemplateDto> templateDtos = templateRepository.findAll()
                .stream()
                .map(TemplateMapper.INSTANCE::toTemplateDto)
                .toList();
        return ResponseBuilder.success(templateDtos);
    }

    /**
     * TODO: Add documentation
     *
     * @param templateId
     * @return
     */
    public ApiResponse<TemplateDto, ?> getTemplateById(Long templateId) {
        TemplateDto templateDto = templateRepository.findById(templateId).stream()
                .map(TemplateMapper.INSTANCE::toTemplateDto)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Template with id %d not found", templateId)));
        return ResponseBuilder.success(templateDto);
    }

    /**
     * TODO: Add documentation
     *
     * @return
     */
    public ApiResponse<TemplateDto, ?> createTemplate(@NotNull TemplateDto templateDto) {
        Template template = templateRepository.save(TemplateMapper.INSTANCE.toTemplate(templateDto));
        return ResponseBuilder.success(TemplateMapper.INSTANCE.toTemplateDto(template));
    }

    /**
     * TODO: Add documentation
     *
     * @param templateId
     * @return
     */
    public ApiResponse<?, ?> updateTemplate(Long templateId, @NotNull TemplateDto templateDto) {
        Template currentTemplate = templateRepository.findById(templateId)
                .orElseThrow(() -> new NoSuchElementException(String.format("Template with id %d not found", templateId)));
        Template updatedTemplate = TemplateMapper.INSTANCE.toTemplate(templateDto);
        // TODO: not force the user to send all the fields
        BeanUtils.copyProperties(updatedTemplate, currentTemplate);
        templateRepository.save(currentTemplate);
        return ResponseBuilder.success();
    }

    /**
     * TODO: Add documentation
     *
     * @param templateId
     * @return
     */
    public ApiResponse<?, ?> deleteTemplate(Long templateId) {
        templateRepository.deleteById(templateId);
        return ResponseBuilder.success();
    }
}
