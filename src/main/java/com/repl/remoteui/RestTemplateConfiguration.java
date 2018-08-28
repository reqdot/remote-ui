package com.repl.remoteui;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class RestTemplateConfiguration {

    private String tokenUrl = "http://localhost:8080/oauth/token";

    @Bean
    public EmbeddedServletContainerCustomizer customizer() {
        return container -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
                tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
            }
        };
    }

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId("clientapp");
        resource.setClientSecret("123456");
        resource.setGrantType("password");
        resource.setUsername("adolfo");
        resource.setPassword("123");

        return resource;
    }

    @Bean
    public OAuth2RestOperations restTemplate() {

        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
    }

}
