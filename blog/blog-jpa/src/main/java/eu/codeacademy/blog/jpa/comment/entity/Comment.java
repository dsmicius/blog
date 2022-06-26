package eu.codeacademy.blog.jpa.comment.entity;

import eu.codeacademy.blog.jpa.blog.entity.Blog;
import eu.codeacademy.blog.jpa.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID commentId;
    private String text;
    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String author;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
