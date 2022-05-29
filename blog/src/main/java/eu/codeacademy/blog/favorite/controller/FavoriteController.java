package eu.codeacademy.blog.favorite.controller;

import eu.codeacademy.blog.favorite.dto.FavoriteDto;
import eu.codeacademy.blog.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.UUID;

@Controller
@RequestMapping("/favorite")
@SessionAttributes("favoriteSession")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @ModelAttribute("favoriteSession")
    public FavoriteDto createCart() {

        return new FavoriteDto();
    }

    @GetMapping
    public String openFavorite(@ModelAttribute("favoriteSession") FavoriteDto favorite) {
        return "favorite/favorite";
    }

    @PostMapping
    public String favorite(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/favorite";
    }

    @PostMapping("/{blogId}")
    public String addToFavorite(@PathVariable UUID blogId, @ModelAttribute("favoriteSession") FavoriteDto favorite) {
        favoriteService.addToFavoriteByBlogId(blogId, favorite);

        return "redirect:/blogs/list";
    }


    @PostMapping("/{blogId}/remove")
    public String removeFromFavorite(@PathVariable UUID blogId, @ModelAttribute("favoriteSession") FavoriteDto favorite) {
        favoriteService.removeFromFavoriteByBlogId(blogId, favorite);
        return "redirect:/favorite";
    }
}
