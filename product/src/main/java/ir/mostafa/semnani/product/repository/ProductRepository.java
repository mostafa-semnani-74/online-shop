package ir.mostafa.semnani.product.repository;

import ir.mostafa.semnani.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
