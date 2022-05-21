package eu.codeacademy.blog.blog.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageSource messageSource;

    public String getMessage(String key) {
        try {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            if (key != null && !key.equals("")) {
                log.error("Key " + key + " does not exits");
                e.printStackTrace();
                return String.format("?%s?", key);
            }
        }
        return "";
    }
}
