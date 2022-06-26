package eu.codeacademy.blog.controller;

import eu.codeacademy.blog.common.favorite.dto.FavoriteDto;
import eu.codeacademy.blog.common.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/public/favorite")
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
    public String clearFavorite(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("messageSuccess","message.clear.favorite");
        return "redirect:/public/blogs/list";
    }

    @PostMapping("/{blogId}")
    public String addToFavorite(@PathVariable UUID blogId, @ModelAttribute("favoriteSession") FavoriteDto favorite) {
        favoriteService.addToFavoriteByBlogId(blogId, favorite);

        return "redirect:/public/blogs/list";
    }


    @PostMapping("/{blogId}/remove")
    public String removeFromFavorite(@PathVariable UUID blogId, @ModelAttribute("favoriteSession") FavoriteDto favorite) {
        favoriteService.removeFromFavoriteByBlogId(blogId, favorite);
        return "redirect:/public/favorite";
    }
}
