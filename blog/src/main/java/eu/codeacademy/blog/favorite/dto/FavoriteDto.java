package eu.codeacademy.blog.favorite.dto;

import eu.codeacademy.blog.blog.dto.BlogDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<FavoriteItem> getFavoriteItem(UUID blogId) {
        return blogItems.stream()
                .filter(fi -> fi.getBlogDto().getBlogId().equals(blogId))
                .findAny();
    }

}
