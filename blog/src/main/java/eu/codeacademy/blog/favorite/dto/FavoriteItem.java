package eu.codeacademy.blog.favorite.dto;

import eu.codeacademy.blog.blog.dto.BlogDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FavoriteItem {

    private final BlogDto blogDto;

}
