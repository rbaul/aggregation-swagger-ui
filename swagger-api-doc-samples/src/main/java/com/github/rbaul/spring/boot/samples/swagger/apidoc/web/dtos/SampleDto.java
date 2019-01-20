package com.github.rbaul.spring.boot.samples.swagger.apidoc.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SampleDto {
    @Max(2)
    private Long id;

    @NotNull
    private String name;

    private String description;

}
