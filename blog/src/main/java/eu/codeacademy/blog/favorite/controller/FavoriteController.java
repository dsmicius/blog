package eu.codeacademy.blog.favorite.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.service.BlogService;
import eu.codeacademy.blog.favorite.dto.FavoriteDto;
import eu.codeacademy.blog.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/favorite")
@SessionAttributes("favoriteSession")
@RequiredArgsConstructor
public class FavoriteController {

    private final BlogService blogService;
    private final FavoriteService favoriteService;
    @ModelAttribute("favoriteSession")
    public List<FavoriteDto> createCart() {
        return new ArrayList<>();
    }

    @GetMapping
    public String openFavorite(@ModelAttribute("favoriteSession") List<FavoriteDto> favorite) {
        return "favorite/favorite";
    }

    @PostMapping("/{blogId}")
    public String addToFavorite(@PathVariable UUID blogId, @ModelAttribute("favoriteSession") List<FavoriteDto> favorite) {
        BlogDto blogDto = blogService.getBlogByUUID(blogId);
        FavoriteDto favoriteDto = favoriteService.getFavoriteDto(blogDto);
        favorite.add(favoriteDto);
        return "redirect:/blogs/list";
    }

    @PostMapping("/{blogId}/remove")
    public String removeFromFavorite(@PathVariable UUID blogId, @ModelAttribute("favoriteSession") List<FavoriteDto> favorite) {
        BlogDto blogDto = blogService.getBlogByUUID(blogId);
        FavoriteDto favoriteDto = favoriteService.getFavoriteDto(blogDto);
        favorite.remove(favoriteDto);
        return "redirect:/favorite";
    }

}
