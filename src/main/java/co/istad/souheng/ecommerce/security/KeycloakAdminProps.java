package co.istad.souheng.ecommerce.security;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class KeycloakAdminProps {
    private String serverUrl;
    private String realm;
    private String targetRealm;
    private String clientId;
    private String clientSecret;
}