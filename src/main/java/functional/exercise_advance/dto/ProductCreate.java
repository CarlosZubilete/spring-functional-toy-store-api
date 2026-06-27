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
public class ProductCreate {

    @NotBlank(message = "name cannot be blank")
    @Size(min = 3, max = 30, message = "name must be between 3 and 30 characters")
    private String name;

    @NotNull(message = "category is required")
    private Category category;

    @NotNull(message = "minimum age is required")
    @Positive(message = "minimum age must be positive")
    private Integer minimumAge;

    @NotNull(message = "price is required")
    @Positive(message = "price must be positive")
    private Double price;

    @NotNull(message = "stock is required")
    @PositiveOrZero(message = "stock must be positive or zero")
    private Integer stock;

    @Size(max = 30, message = "brand accept till 30 characters")
    private String brand;

    @Positive(message = "average rating must be positive")
    private Double averageRating;

    @NotNull(message = "sales last thirty days rating age is required")
    @PositiveOrZero(message = "sales last thirty days must be positive or zero")
    private Integer salesLastThirtyDays;

    @NotEmpty(message = "tags list cannot be blank")
    private Set<
        @NotBlank(message = "tag cannot be blank")
        @Size(min = 2, max = 20, message = "each tag must be between 2 and 20 characters")
            String> tags;
}
