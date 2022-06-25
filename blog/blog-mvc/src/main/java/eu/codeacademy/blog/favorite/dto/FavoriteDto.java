package eu.codeacademy.blog.favorite.dto;

import eu.codeacademy.blog.blog.dto.BlogDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FavoriteDto {

    private final List<FavoriteItem> blogItems = new ArrayList<>();

    public void add(BlogDto blogDto) {
        blogItems.add(FavoriteItem.builder()
                .blogDto(blogDto)
                .build());
    }

    public void remove(FavoriteItem favoriteItem) {
        blogItems.remove(favoriteItem);
    }
}
