package eu.codeacademy.blog.controller;

import eu.codeacademy.blog.common.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/public/blog-finder")
@RequiredArgsConstructor
public class BlogFinderController {
    private final BlogService blogService;

    @GetMapping
    public String getBlogsBySubject(Model model, @RequestParam String subject) {
        return "forward:/public/blog-finder/searchResult/" + subject;
    }

    @GetMapping("/searchResult/{subject}")
    public String getBlogsBySubjectSearchResult(Model model, @PathVariable String subject, Pageable pageable) {
        model.addAttribute("blogPage", blogService.getBlogBySubjectPageable(subject, pageable));
        return "blog/blogs";
    }
}
