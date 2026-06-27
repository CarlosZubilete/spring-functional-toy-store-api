package functional.exercise_advance.dto;

import functional.exercise_advance.model.Category;
import lombok.*;


import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Category category;
    private Integer minimumAge;
    private Double price;
    private Integer stock;
    private String brand;
    private Double averageRating;
    private Integer salesLastThirtyDays;
    private Set<String> tags;
}
