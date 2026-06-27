package functional.exercise_advance.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "minimum_age")
    private Integer minimumAge;

    private Double price;

    private Integer stock;

    private String brand;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "sales_last_thirty_days")
    private Integer salesLastThirtyDays;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_tag", joinColumns = @JoinColumn(name = "id_product"))
    @Column(name = "tag")
    private Set<String> tags;
}
