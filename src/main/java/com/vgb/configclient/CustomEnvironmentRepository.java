package com.vgb.configclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Profile("custom")
@ConfigurationProperties("spring.cloud.config.server.custom")
public class CustomEnvironmentRepository implements EnvironmentRepository, Ordered {

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
        //connect to the desired repository
        log.info("\n###########\nTODO: connect to custom repository for " + application + ":" + profile + " at " + connectionString() + "\n###########\n");
        final String[] profiles = {profile};
        final Environment environment = new Environment(application, profiles, label, null, null);

        //add matching repository values into the environment
        environment.add(new PropertySource("customProperty", properties(application, profiles)));
        return environment;
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


    /**
     * form the connection string
     *
     * @return
     */
    private String connectionString() {
        return getUrl() + "/" + getUserId();
    }

    /**
     * retrieve properties
     *
     * @param application
     * @param profiles
     * @return
     */
    private Map<String, String> properties(String application, String[] profiles) {
        //potentially use the application and profiles to obtain desired information from the store
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
}
