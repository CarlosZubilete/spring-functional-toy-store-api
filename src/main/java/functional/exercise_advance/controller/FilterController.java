package functional.exercise_advance.controller;

import functional.exercise_advance.dto.ProductResponse;
import functional.exercise_advance.model.Category;
import functional.exercise_advance.service.IFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController()
@RequestMapping("/api/products/filters")
public class FilterController {

    @Autowired
    private IFilterService service;

    // Point-One
    @GetMapping("/one")
    public ResponseEntity<List<String>> getStockAndSales(@RequestParam(defaultValue = "0")
                                                         Integer stockGreaterTo,
                                                         @RequestParam(defaultValue = "50")
                                                         Integer salesGreaterTo) {
        return ResponseEntity.ok(service.stockAndSalesGreaterUnitesTo(stockGreaterTo, salesGreaterTo));
    }

    // Point-Two
    @GetMapping("/two")
    public ResponseEntity<String> getAverageByCategory(@RequestParam(required = false)
                                                       Category category) {
        // if there is null , it is going to go to filter by EDUCATIONAL:
        // return ResponseEntity.ok(service.averagePriceByCategory(category));
        return ResponseEntity.ok(service.averagePriceByCategory(category));
    }

    // Point-Three
    @GetMapping("/three")
    public ResponseEntity<Map<String, Long>> getProductsByCategory() {
        return ResponseEntity.ok(service.quantityProductByCategory());
    }

    // Point-Four
    @GetMapping("/four")
    public ResponseEntity<ProductResponse> getMostExpensiveByCategory(@RequestParam(required = false)
                                                                      Category category) {
        return ResponseEntity.ok(service.mostExpensiveByCategory(category));
    }

    // Point-Five
    @GetMapping("/five")
    public ResponseEntity<List<ProductResponse>> getTopFiveBestAverageRating(@RequestParam(required = false)
                                                                             Integer limit) {
        // Default limit = 5.
        return ResponseEntity.ok(service.topBestAverageRating(limit));
    }

    // Point-Six
    @GetMapping("/six")
    public ResponseEntity<Set<String>> getListTagsWithoutRepeat() {
        return ResponseEntity.ok(service.listTagsWithoutRepeat());
    }

    // Point-Seven
    @GetMapping("/seven")
    public ResponseEntity<Map<String, Double>> getTotalPotentialValue() {
        return ResponseEntity.ok(service.totalPotentialValue());
    }

    // Point-Eight
    @GetMapping("/eight")
    public ResponseEntity<Map<String, Integer>> getRestockProduct(@RequestParam(required = false) Integer limit) {
        // Default limit < 5.
        return ResponseEntity.ok(service.restockProduct(limit));
    }

}
