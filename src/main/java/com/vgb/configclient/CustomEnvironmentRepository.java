package com.vgb.configclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
@ConfigurationProperties("spring.cloud.config.server.custom")
public class CustomEnvironmentRepository implements EnvironmentRepository, Ordered{

    final Logger log = Logger.getLogger(this.getClass().getName());

    private String userId;
    private String password;
    private String url;
    private int order = Ordered.LOWEST_PRECEDENCE;

    public CustomEnvironmentRepository() {
    }

    public CustomEnvironmentRepository(String userId, String password, String url, int order) {
        this.userId = userId;
        this.password = password;
        this.url = url;
        this.order = order;
    }

    @Override
    public Environment findOne(String application, String profile, String label) {
        log.info("\n###########\nAttempting to connect to custom url at " + connectionString() + "\n###########\n");
        final Environment environment = new Environment(application, profile);
        environment.add(new PropertySource("customProperty", properties()));
        return environment;
    }

    private String connectionString() {
        return getUrl() + "/" + getUserId();
    }
    private Map<String,String> properties() {
        return new HashMap() {
            {
                put("custom.prop.1", "custom.value.1");
                put("custom.prop.2", "custom.value.2");
                put("custom.prop.3", "custom.value.3");
                put("custom.prop.4", "custom.value.4");
                put("props.name", "custom.value");

            }
        };
    }

    @Override
    public int getOrder() {
        return order;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CustomEnvironmentRepository{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", order=" + order +
                '}';
    }
}
