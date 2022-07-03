package eu.codeacademy.blog.jpa.blog.entity;

import eu.codeacademy.blog.jpa.comment.entity.Comment;
import eu.codeacademy.blog.security.jpa.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID blogId;
    private String subject;
    private String description;
    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String author;
    private String status;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
