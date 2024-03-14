package ir.mostafa.semnani.product.service;

import ir.mostafa.semnani.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO productDTO);

    List<ProductDTO> findAll();

}
