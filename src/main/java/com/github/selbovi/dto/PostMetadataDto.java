package com.github.selbovi.dto;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class PostMetadataDto {

    private Long id;

    private ZonedDateTime publishDate;

}
