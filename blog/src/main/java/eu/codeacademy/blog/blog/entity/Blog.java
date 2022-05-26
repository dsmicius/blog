package eu.codeacademy.blog.blog.entity;

import eu.codeacademy.blog.comment.entity.Comment;
import lombok.*;

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

}
