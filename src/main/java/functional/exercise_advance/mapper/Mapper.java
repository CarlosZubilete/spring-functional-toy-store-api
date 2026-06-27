package functional.exercise_advance.mapper;

import functional.exercise_advance.dto.ProductCreate;
import functional.exercise_advance.dto.ProductResponse;
import functional.exercise_advance.dto.ProductUpdate;
import functional.exercise_advance.model.Product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper {

    public static ProductResponse toDTO(Product product) {
        if (product == null)
            return null;

        Set<String> tags = formatTags(product.getTags());

        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .category(product.getCategory())
            .minimumAge(product.getMinimumAge())
            .price(product.getPrice())
            .stock(product.getStock())
            .brand(product.getBrand())
            .averageRating(product.getAverageRating())
            .salesLastThirtyDays(product.getSalesLastThirtyDays())
            .tags(tags)
            .build();
    }


    public static Product toEntity(ProductCreate dto) {
        if (dto == null)
            return null;

        Set<String> tags = formatTags(dto.getTags());

        return Product.builder()
            .name(dto.getName())
            .category(dto.getCategory())
            .minimumAge(dto.getMinimumAge())
            .price(dto.getPrice())
            .brand(dto.getBrand())
            .stock(dto.getStock())
            .salesLastThirtyDays(dto.getSalesLastThirtyDays())
            .averageRating(dto.getAverageRating())
            .tags(tags)
            .build();
    }

    public static void update(ProductUpdate dto, Product existingProduct) {
        if (dto == null) return;

        if (dto.getName() != null)
            existingProduct.setName(dto.getName());

        if (dto.getCategory() != null)
            existingProduct.setCategory(dto.getCategory());

        if (dto.getMinimumAge() != null)
            existingProduct.setMinimumAge(dto.getMinimumAge());

        if (dto.getPrice() != null)
            existingProduct.setPrice(dto.getPrice());

        if (dto.getBrand() != null)
            existingProduct.setBrand(dto.getBrand());

        if (dto.getStock() != null)
            existingProduct.setStock(dto.getStock());

        if (dto.getSalesLastThirtyDays() != null)
            existingProduct.setSalesLastThirtyDays(dto.getSalesLastThirtyDays());

        if (dto.getAverageRating() != null)
            existingProduct.setAverageRating(dto.getAverageRating());

        if (dto.getTags() != null) {
            Set<String> tags = formatTags(dto.getTags());
            existingProduct.setTags(tags);
        }
    }

    private static Set<String> formatTags(Set<String> tagList) {
        return tagList.stream()
            .map(String::toLowerCase)
            .collect(Collectors.toSet());
    }
}
