package ecommerce_v2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest (
        @NotBlank(message = "Ürün adı boş bırakılamaz!")
        String name,

        @NotNull(message = "Fiyat boş olamaz")
        @Min(value = 0,message = "Fiyat 0'dan küçük olamaz!")
        Double price,

        @NotNull(message = "Stok boş olamaz!")
        @Min(value = 0,message = "Stok 0'dan küçük olamaz!")
        Integer stock,

        @NotNull(message = "Kategori ID boş olamaz!")
        Long categoryId
){
}
