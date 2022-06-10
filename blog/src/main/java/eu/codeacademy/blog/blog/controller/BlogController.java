package eu.codeacademy.blog.blog.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.blog.service.BlogService;
import eu.codeacademy.blog.comment.service.CommentService;
import eu.codeacademy.blog.helper.MessageService;
import eu.codeacademy.blog.user.dto.UserRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BlogController {

    //TODO: perkelti i viena interface visus root pathus
    private static final String BLOG_ROOT_PATH = "/blogs";
    private static final String BLOG_LIST_PATH = "/public" + BLOG_ROOT_PATH + "/list";
    private static final String BLOG_UPDATE_PATH = BLOG_ROOT_PATH + "/update";
    private static final String BLOG_DELETE_PATH = BLOG_ROOT_PATH + "/delete";
    private static final String BLOG_VIEW_PATH = "/public" + BLOG_ROOT_PATH + "/{blogId}/view";
    private final BlogService blogService;
    private final MessageService messageService;
    private final CommentService commentService;

    @GetMapping(BLOG_ROOT_PATH)
    public String openCreateBlogForm(Model model, String message) {
        model.addAttribute("blog", BlogDto.builder().build());
        model.addAttribute("message", messageService.getMessage(message));
        return "blog/blog";
    }

    @PostMapping(BLOG_ROOT_PATH)
    public String createBlog(Model model, @Valid BlogDto blog,
                             BindingResult errors,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserRoleDto userDto) {
        if (errors.hasErrors()) {
            model.addAttribute("blog", BlogDto.builder().subject("Error").build());
            return "blog/blog";
        }
        blogService.addBlog(blog, userDto.getUser());
        redirectAttributes.addFlashAttribute("messageSuccess", "create.blog.message.success");
        return "redirect:/public/blogs/list";
    }

    @GetMapping(BLOG_LIST_PATH)
    public String getBlogs(Model model, @PageableDefault(size = 8, sort = {"createDate"}, direction = Sort.Direction.DESC) Pageable pageable, String message) {
        model.addAttribute("blogPage", blogService.getBlogPaginated(pageable));
        model.addAttribute("message", messageService.getMessage(message));
        return "blog/blogs";
    }

    @GetMapping(BLOG_UPDATE_PATH)
    public String getUpdateBlog(Model model, @RequestParam UUID blogId) {
        model.addAttribute("blog", blogService.getBlogByUUID(blogId));
        return "blog/blog_update";
    }

    @GetMapping(BLOG_VIEW_PATH)
    public String getViewBlog(Model model, @PathVariable("blogId") UUID id) {
        Blog blog = blogService.getBlogByBlogId(id);
        model.addAttribute("blog", blogService.getBlogByUUID(id));
        model.addAttribute("comments", commentService.getBlogComments(blog));
        return "blog/blog_view";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(BLOG_UPDATE_PATH)
    public String updateBlog(BlogDto blog, RedirectAttributes redirectAttributes) {
        blogService.updateBlog(blog);
        redirectAttributes.addFlashAttribute("messageSuccess", "update.blog.message.success");
        return "redirect:/public/blogs/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(BLOG_DELETE_PATH)
    public String deleteBlog(@RequestParam UUID blogId, RedirectAttributes redirectAttributes) {
        blogService.deleteBlog(blogId);
        redirectAttributes.addFlashAttribute("messageSuccess", "delete.blog.message.success");
        return "redirect:/public/blogs/list";
    }
}
