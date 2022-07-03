package eu.codeacademy.blog.security.basic.config;

import eu.codeacademy.blog.security.basic.model.GlobalUser;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ConfigurationProperties("global")
public class ApplicationUsersPropertyConfig {
    private final List<GlobalUser> users = new ArrayList<>();

    public Collection<GlobalUser> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        Object[] usersName = users.stream().map(GlobalUser::getUsername).toArray();

        return "ApplicationUsersPropertyConfig{" +
                "users=" + Arrays.toString(usersName) +
                '}';
    }
}
