package regular.model.com.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @apiNote The service for working with clients
 * @since 06.01.2023
 */
@Component
@RequiredArgsConstructor
public class MessageService {
    private static MessageSource messageSource;

    @Autowired
    public void setSomeThing(MessageSource messageSource) {
        MessageService.messageSource = messageSource;
    }

    /**
     * When key comes, it will return the message which belongs to that <br/>
     * This is the place where taking keys from  <pre>/src/java/resources/messages</pre>
     */
    public static String getMessage(String key) {
        try {
            return messageSource.getMessage(key, null,
                    LocaleContextHolder.getLocale());
        }catch (Exception e) {
            return key;
        }
    }

}
