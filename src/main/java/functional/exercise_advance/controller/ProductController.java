package functional.exercise_advance.controller;

import functional.exercise_advance.dto.ProductCreate;
import functional.exercise_advance.dto.ProductResponse;
import functional.exercise_advance.dto.ProductUpdate;
import functional.exercise_advance.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getProductList() {
        List<ProductResponse> productList = service.getAllRecords();
        return productList != null
            ? ResponseEntity.ok(productList)
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse product = service.getRecordById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductCreate dto) {
        ProductResponse created = service.saveRecord(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable Long id,
                                                         @Valid @RequestBody ProductUpdate dto) {
        ProductResponse updated = service.updateRecord(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        boolean result = service.deleteRecord(id);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
