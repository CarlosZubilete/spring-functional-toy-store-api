package functional.exercise_advance.service;

import functional.exercise_advance.dto.ProductResponse;
import functional.exercise_advance.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IFilterService {
    // Point one
    List<String> stockAndSalesGreaterUnitesTo(Integer stockGreaterTo,
                                              Integer salesGreaterTo);

    // Point Two
    String averagePriceByCategory(Category categoryToFilter);

    // Point three
    Map<String, Long> quantityProductByCategory();

    // Point four
    ProductResponse mostExpensiveByCategory(Category categoryToFilter);

    // Point five
    List<ProductResponse> topBestAverageRating(Integer limitToFilter);

    // Point six
    Set<String> listTagsWithoutRepeat();

    // Point seven
    Map<String, Double> totalPotentialValue();

    // Point eight
    Map<String, Integer> restockProduct(Integer limitToFilter);

}

