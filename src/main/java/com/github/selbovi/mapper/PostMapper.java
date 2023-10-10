package com.github.selbovi.mapper;

import com.github.selbovi.domain.Post;
import com.github.selbovi.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    public static PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    PostDto postToPostDto(Post post);
}
