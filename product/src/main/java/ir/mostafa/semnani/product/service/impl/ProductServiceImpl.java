package ir.mostafa.semnani.product.service.impl;

import ir.mostafa.semnani.product.dto.ProductDTO;
import ir.mostafa.semnani.product.mapper.ProductMapper;
import ir.mostafa.semnani.product.repository.ProductRepository;
import ir.mostafa.semnani.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(productDTO)));
    }

    @Override
    public List<ProductDTO> findAll() {
        return ProductMapper.toDTOs(productRepository.findAll());
    }
}
