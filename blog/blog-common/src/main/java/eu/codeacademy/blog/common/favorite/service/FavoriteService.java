package eu.codeacademy.blog.common.favorite.service;

import eu.codeacademy.blog.common.blog.dto.BlogDto;
import eu.codeacademy.blog.common.blog.service.BlogService;
import eu.codeacademy.blog.common.favorite.dto.FavoriteItem;
import eu.codeacademy.blog.common.favorite.dto.FavoriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final BlogService blogService;

    public void addToFavoriteByBlogId(UUID blogId, FavoriteDto favorite) {
        this.getFavoriteItem(blogId, favorite.getBlogItems()).ifPresentOrElse(
                FavoriteItem::getBlogDto,
                () -> addBlogToFavorite(blogId, favorite)
        );
    }

    private void addBlogToFavorite(UUID blogId, FavoriteDto favorite) {
        BlogDto blogDto = blogService.getBlogByUUID(blogId);
        favorite.add(blogDto);
    }

    private Optional<FavoriteItem> getFavoriteItem(UUID blogId, List<FavoriteItem> favoriteItems) {
        return favoriteItems.stream()
                .filter(fi -> fi.getBlogDto().getBlogId().equals(blogId))
                .findAny();
    }

    public void removeFromFavoriteByBlogId(UUID blogId, FavoriteDto favorite) {
        this.getFavoriteItem(blogId, favorite.getBlogItems()).ifPresent(favorite::remove);
    }


}
