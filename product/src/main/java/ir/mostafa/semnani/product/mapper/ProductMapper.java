package ir.mostafa.semnani.product.mapper;

import ir.mostafa.semnani.product.dto.ProductDTO;
import ir.mostafa.semnani.product.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getCode(),
                product.getName()
        );
    }

    public static List<ProductDTO> toDTOs(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.id())
                .code(productDTO.code())
                .name(productDTO.name())
                .build();
    }

    public static List<Product> toEntities(List<ProductDTO> productDTOs) {
        return productDTOs.stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList());
    }

}
