package functional.exercise_advance.service;

import functional.exercise_advance.dto.ProductCreate;
import functional.exercise_advance.dto.ProductResponse;
import functional.exercise_advance.dto.ProductUpdate;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getAllRecords();

    ProductResponse getRecordById(Long id);

    ProductResponse saveRecord(ProductCreate dto);

    ProductResponse updateRecord(Long id, ProductUpdate dto);

    boolean deleteRecord(Long id);
}
