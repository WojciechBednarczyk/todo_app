package io.github.WojciechBednarczyk.todo_app;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {
    @Getter
    @Setter
    private Template template;

    public static class Template{

        @Getter
        @Setter
        private boolean allowMultipleTasks;

    }
}
