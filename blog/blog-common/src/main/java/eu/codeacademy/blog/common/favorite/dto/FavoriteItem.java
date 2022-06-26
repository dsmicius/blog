package eu.codeacademy.blog.common.favorite.dto;

import eu.codeacademy.blog.common.blog.dto.BlogDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FavoriteItem {

    private final BlogDto blogDto;

}
