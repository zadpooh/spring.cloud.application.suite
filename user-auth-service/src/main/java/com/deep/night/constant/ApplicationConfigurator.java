package com.deep.night.constant;

import com.deep.night.config.ApplicationConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

@PropertySources({
        @PropertySource(name = "_application.config", value = "classpath:config/application.properties"),
        @PropertySource(name = "_application.profiles.config", value = "classpath:config/application-${spring.profiles.active:user-local}.properties")
})
@Slf4j
public class ApplicationConfigurator {

    //@Value("${application.config.property}")
    private String applicationConfigProperty = "META-INF/config/application.properties";

    @PostConstruct
    public void initialize() throws Exception {
        ClassLoader classloader = this.getClass().getClassLoader();

        log.info("application setup...");

        //application.config.property initialize
        log.debug("applicationConfigProperty = {}", applicationConfigProperty);
        ApplicationConfiguration.application.property = new Properties(System.getProperties());
        ApplicationConfiguration.application.property.load(classloader.getResourceAsStream(applicationConfigProperty));
        log.debug("ConfigurationConstant.application.property = {}", ApplicationConfiguration.application.property);

        log.info("application setup completed");

        setConfigurableEnvironment();
    }

    @Autowired //@Resource
    private ConfigurableEnvironment env;
    private String applicationEnvironmentProperty = "config/application.properties";
    private ResourceLoader loader = new DefaultResourceLoader();

    private void setConfigurableEnvironment() {
        if (Boolean.valueOf(env.getProperty("application.properties.loaded"))) return;

        log.info("set configurable environment...");

        MutablePropertySources mutablePropertySources = env.getPropertySources();
        try {
            Resource resource = loader.getResource(applicationEnvironmentProperty);
            if (resource.exists())
                mutablePropertySources.addLast(getPropertySource(resource));
        } catch (IOException e) {
            log.error("setConfigurableEnvironment > mutablePropertySources > IOException ", e);
        }

        log.info("activeProfiles : {}", env.getActiveProfiles());

        if (env.getActiveProfiles().length == 0) env.setActiveProfiles("user-local");
        for (String profile : env.getActiveProfiles()) {
            String applicationProfileConfigProperty = applicationEnvironmentProperty.replace(".properties", "-".concat(profile).concat(".properties"));
            log.info("applicationProfileConfigProperty : {} ", applicationProfileConfigProperty);
            try {
                Resource resource = loader.getResource(applicationProfileConfigProperty);
                if (resource.exists())
                    mutablePropertySources.addLast(getPropertySource(resource));
            } catch (IOException e) {
                log.error("setConfigurableEnvironment > getResource > IOException ", e);
            }
        }
    }

    private PropertiesPropertySource getPropertySource(Resource resource) throws IOException {
        Properties properties = new Properties();
        properties.load(resource.getInputStream());

        return new PropertiesPropertySource(resource.getFilename(), properties);
    }

}

