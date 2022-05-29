package eu.codeacademy.blog.favorite.mapper;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.favorite.dto.FavoriteDto;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {

    public FavoriteDto mapTo(BlogDto blogDto) {
//        return FavoriteDto.builder()
//                .blogId(blogDto.getBlogId())
//                .subject(blogDto.getSubject())
//                .description(blogDto.getDescription())
//                .createDate(blogDto.getCreateDate())
//                .updateDate(blogDto.getUpdateDate())
//                .deleteDate(blogDto.getDeleteDate())
//                .author(blogDto.getAuthor())
//                .status(blogDto.getStatus())
//                .build();
        return null;
    }
}
