package eu.codeacademy.blog.comment.exception;

public class CommentDeleteException extends RuntimeException{

    public CommentDeleteException(String message) {
        super(message);
    }
}
