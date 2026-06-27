package functional.exercise_advance.dto;

import functional.exercise_advance.model.Category;
import jakarta.validation.constraints.*;
import lombok.*;


import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdate {

    @Size(min = 3, max = 30, message = "name must be between 3 and 30 characters")
    private String name;

    private Category category;

    @Positive(message = "minimum age must be positive")
    private Integer minimumAge;

    @Positive(message = "price must be positive")
    private Double price;

    @PositiveOrZero(message = "stock must be positive or zero")
    private Integer stock;

    @Size(max = 30, message = "brand accept till 30 characters")
    private String brand;

    @Positive(message = "average rating must be positive")
    private Double averageRating;

    @PositiveOrZero(message = "sales last thirty days must be positive or zero")
    private Integer salesLastThirtyDays;

    private Set<
        @Size(min = 2, max = 20, message = "each tag must be between 2 and 20 characters")
            String> tags;

}
