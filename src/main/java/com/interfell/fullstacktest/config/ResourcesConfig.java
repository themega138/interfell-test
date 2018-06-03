package com.interfell.fullstacktest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interfell.fullstacktest.resources.AuthResources;
import com.interfell.fullstacktest.resources.UsersResources;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Component
public class ResourcesConfig extends ResourceConfig{

    @Value("${spring.jersey.application-path:/api}")
    private String apiPath;

    @Autowired
    public ResourcesConfig(ObjectMapper objectMapper) {
        register(UsersResources.class);
        register(AuthResources.class);

        register(WadlResource.class);

        register(new ObjectMapperContextResolver(objectMapper));
    }

    @PostConstruct
    public void init(){
        this.configureSwagger();
    }

    @Provider
    public static class ObjectMapperContextResolver implements
            ContextResolver<ObjectMapper> {
        private final ObjectMapper mapper;
        public ObjectMapperContextResolver(ObjectMapper mapper) {
            this.mapper = mapper;
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return mapper;
        }
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-example");
        config.setTitle("Interfell test");
        config.setVersion("v1");
        config.setContact("Moises Graterol");
        config.setSchemes(new String[] { "http" });
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.interfell.fullstacktest.resources");
        config.setPrettyPrint(true);
        config.setScan(true);
    }

}
