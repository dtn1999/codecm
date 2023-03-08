package com.we.elearning.templates.controllers;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.templates.dtos.TemplateDto;
import com.we.elearning.templates.services.TemplatesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources/templates")
@CrossOrigin(origins = "${we.elearning.security.cors.origins}")
@RequiredArgsConstructor
@Slf4j
public class TemplatesController {
    private final TemplatesService templatesService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTemplates() {
        log.info("getting all templates");
        return ResponseEntity.ok(templatesService.getAllTemplates());
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<ApiResponse> getTemplateById(
            @PathVariable("templateId") @Min(1L) final Long templateId) {
        log.info("getting template with id: {}", templateId);
        return ResponseEntity.ok(templatesService.getTemplateById(templateId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTemplate(
            @Valid @RequestBody final TemplateDto templateDto) {
        log.info("creating template with following properties: {}", templateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(templatesService.createTemplate(templateDto));
    }

    @PutMapping("/{templateId}")
    public ResponseEntity<ApiResponse> updateTemplate(@PathVariable("templateId") @Min(1L) final Long templateId,
                                                            @Valid @RequestBody final TemplateDto templateDto) {
        log.info("updating template with id: {} with following properties: {}", templateId, templateDto);
        return ResponseEntity.ok(templatesService.updateTemplate(templateId, templateDto));
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<ApiResponse> deleteTemplate(
            @PathVariable("templateId") @Min(1L) final Long templateId) {
        log.info("deleting template with id: {}", templateId);
        return ResponseEntity.ok(templatesService.deleteTemplate(templateId));
    }
}
