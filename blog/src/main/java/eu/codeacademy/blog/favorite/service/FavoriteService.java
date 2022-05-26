package eu.codeacademy.blog.favorite.service;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.favorite.dto.FavoriteDto;
import eu.codeacademy.blog.favorite.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteMapper mapper;

    public FavoriteDto getFavoriteDto(BlogDto blogDto) {
        return mapper.mapTo(blogDto);
    }

}
