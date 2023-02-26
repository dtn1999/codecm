package com.we.elearning.templates.dtos;

import com.we.elearning.templates.entities.Template;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TemplateMapper {
    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);
    TemplateDto toTemplateDto(Template template);
    Template toTemplate(TemplateDto templateDto);
}
