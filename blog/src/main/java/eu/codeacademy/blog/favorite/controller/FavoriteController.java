package eu.codeacademy.blog.favorite.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/favorite")
@SessionAttributes("favoriteSession")
public class FavoriteController {

    @ModelAttribute("favoriteSession")
    public List<BlogDto> createCart() {
        return List.of(BlogDto.builder()
                .subject("Test")
                .description("tekstas tekstas tekstas tekstas ")
                .build());
    }

    @GetMapping
    public String openFavorite(@ModelAttribute("favoriteSession") List<BlogDto> favorite) {
        return "favorite/favorite";
    }

//    @GetMapping("/add")
//    public String addToFavorite(Model model) {
//        model.addAttribute("favoriteSession","labas");
//        return "redirect:/favorite";
//    }

}
