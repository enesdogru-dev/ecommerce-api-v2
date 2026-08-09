package ecommerce_v2.service;


import ecommerce_v2.dto.ProductRequest;
import ecommerce_v2.dto.ProductResponse;
import ecommerce_v2.entity.Category;
import ecommerce_v2.entity.Product;
import ecommerce_v2.repository.CategoryRepository;
import ecommerce_v2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(()-> new RuntimeException("HATA: Firdiğiniz ID'ye ait bir kategori bulunamadı!"));
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStock(),
                category.getName()
        );
    }
}
