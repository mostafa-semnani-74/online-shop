package ir.mostafa.semnani.product;

import ir.mostafa.semnani.product.config.properties.ProductConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ProductConfigurationProperties.class)
public class ProductApplication implements CommandLineRunner {

	private ProductConfigurationProperties productConfigurationProperties;

	@Autowired
	public ProductApplication(ProductConfigurationProperties productConfigurationProperties) {
		this.productConfigurationProperties = productConfigurationProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("example : " + productConfigurationProperties.getUsername());
	}
}
