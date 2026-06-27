package functional.exercise_advance.service;

import functional.exercise_advance.dto.ProductResponse;
import functional.exercise_advance.mapper.Mapper;
import functional.exercise_advance.model.Category;
import functional.exercise_advance.model.Product;
import functional.exercise_advance.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

// Descending: highest to lowest -> sorted
// Ascending: lowest to highest -> reverse

@Service
public class FilterService implements IFilterService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<String> stockAndSalesGreaterUnitesTo(Integer stockGreaterTo,
                                                     Integer salesGreaterTo) {

        return productRepository.findAll().stream()
            .filter(product ->
                product.getStock() > stockGreaterTo
                    && product.getSalesLastThirtyDays() > salesGreaterTo)
            .sorted(Comparator.comparing(Product::getSalesLastThirtyDays).reversed())
            .map(Product::getName)
            .toList();

    }


    @Override
    @Transactional(readOnly = true)
    public String averagePriceByCategory(Category categoryToFilter) {

        Category category = Optional.ofNullable(categoryToFilter).orElse(Category.EDUCATIONAL);

        double averageByCategory = productRepository.findAll().stream()
            .filter(product -> product.getCategory() == category)
            .mapToDouble(Product::getPrice)
            .average()
            .orElse(0.0);

        return "Average of '" + category + "' = " + averageByCategory;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> quantityProductByCategory() {
        return productRepository.findAll().stream()
            .collect(Collectors.groupingBy(
                product -> product.getCategory().name(),
                Collectors.counting()
            ));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse mostExpensiveByCategory(Category categoryToFilter) {

        Category category = Optional.ofNullable(categoryToFilter).orElse(Category.BOARD_GAME);

        Product product = productRepository.findAll().stream()
            .filter(p -> p.getCategory() == category)
            // .reduce((a, b) -> a.getPrice() > b.getPrice() ? a : b)
            .max(Comparator.comparing(Product::getPrice))

            .orElse(null);

        return Mapper.toDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> topBestAverageRating(Integer limitToFind) {

        Integer limit = Optional.ofNullable(limitToFind).orElse(5);

        return productRepository.findAll().stream()
            .filter(product -> product.getAverageRating() != null)
            .sorted(Comparator.comparing(Product::getAverageRating).reversed())
            .map(Mapper::toDTO)
            .limit(limit)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> listTagsWithoutRepeat() {

        return productRepository.findAll().stream()
            .map(Product::getTags)
            .flatMap(Collection::stream)
            .sorted()
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Double> totalPotentialValue() {

        return productRepository.findAll().stream()
            .collect(Collectors.groupingBy(
                product -> product.getCategory().name(),
                Collectors.summingDouble(p -> p.getPrice() * p.getStock())
            ));
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Integer> restockProduct(Integer limitToFilter) {
        int limit = Optional.ofNullable(limitToFilter).orElse(5);

        return productRepository.findAll().stream()
            .filter(product -> product.getStock() < limit)
            .collect(Collectors.toMap(
                Product::getName,
                Product::getStock
            ));
    }
}

