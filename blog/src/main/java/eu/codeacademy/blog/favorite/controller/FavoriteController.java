package eu.codeacademy.blog.favorite.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.service.BlogService;
import eu.codeacademy.blog.favorite.dto.FavoriteDto;
import eu.codeacademy.blog.favorite.dto.FavoriteItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/favorite")
@SessionAttributes("favoriteSession")
@RequiredArgsConstructor
public class FavoriteController {

    private final BlogService blogService;

    @ModelAttribute("favoriteSession")
    public FavoriteDto createCart() {

        return new FavoriteDto();
    }

    @GetMapping
    public String openFavorite(@ModelAttribute("favoriteSession") FavoriteDto favorite) {
        return "favorite/favorite";
    }

    @PostMapping("/{blogId}")
    public String addToFavorite(@PathVariable UUID blogId, @ModelAttribute("favoriteSession") FavoriteDto favorite) {
        favorite.getFavoriteItem(blogId).ifPresentOrElse(
                FavoriteItem::getBlogDto,
                () -> addBlogToFavorite(blogId, favorite)
        );

        return "redirect:/blogs/list";
    }

    private void addBlogToFavorite(UUID blogId, FavoriteDto favorite) {
        BlogDto blogDto = blogService.getBlogByUUID(blogId);
        favorite.add(blogDto);
    }

    @PostMapping("/{blogId}/remove")
    public String removeFromFavorite(@PathVariable UUID blogId, @ModelAttribute("favoriteSession") FavoriteDto favorite) {
        favorite.getFavoriteItem(blogId).ifPresent(favorite::remove);

        return "redirect:/favorite";
    }
}
