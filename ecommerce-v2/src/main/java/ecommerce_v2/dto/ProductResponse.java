package ecommerce_v2.dto;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        Integer stock,
        String categoryName
) {
}
