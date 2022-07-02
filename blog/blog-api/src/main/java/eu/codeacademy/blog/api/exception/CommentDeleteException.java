package eu.codeacademy.blog.api.exception;

public class CommentDeleteException extends RuntimeException{

    public CommentDeleteException(String message) {
        super(message);
    }
}
