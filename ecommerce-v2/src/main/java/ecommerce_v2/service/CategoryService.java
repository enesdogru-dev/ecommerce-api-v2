package ecommerce_v2.service;


import ecommerce_v2.dto.CategoryResponse;
import ecommerce_v2.entity.Category;
import ecommerce_v2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> new CategoryResponse(category.getId(),category.getName()))
                .collect(Collectors.toList());
    }

}
