package functional.exercise_advance.service;

import functional.exercise_advance.dto.ProductCreate;
import functional.exercise_advance.dto.ProductResponse;
import functional.exercise_advance.dto.ProductUpdate;
import functional.exercise_advance.exception.NotFoundException;
import functional.exercise_advance.mapper.Mapper;
import functional.exercise_advance.model.Product;
import functional.exercise_advance.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllRecords() {
        return repository.findAll().stream()
            .map(Mapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getRecordById(Long id) {
        return repository.findById(id).map(Mapper::toDTO)
            .orElseThrow(() -> new NotFoundException("Product with this id: " + id + " not found."));
    }

    @Override
    @Transactional
    public ProductResponse saveRecord(ProductCreate dto) {
        Product product = Mapper.toEntity(dto);


        return Mapper.toDTO(repository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse updateRecord(Long id, ProductUpdate dto) {
        Product existingProduct = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Product with this id" + id + " not found."));

        Mapper.update(dto, existingProduct);

        return Mapper.toDTO(repository.save(existingProduct));
    }

    @Override
    @Transactional
    public boolean deleteRecord(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundException("Product with this id" + id + " not found.");

        repository.deleteById(id);
        return true;
    }
}
