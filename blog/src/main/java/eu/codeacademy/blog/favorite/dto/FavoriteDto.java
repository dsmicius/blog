package eu.codeacademy.blog.favorite.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class FavoriteDto {
    private UUID blogId;
    private String subject;
    private String description;
    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String author;
    private String status;
}
