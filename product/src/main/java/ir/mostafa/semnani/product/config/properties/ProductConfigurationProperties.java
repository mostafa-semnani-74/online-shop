package ir.mostafa.semnani.product.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("product")
@Data
public class ProductConfigurationProperties {
    private String username;
}
