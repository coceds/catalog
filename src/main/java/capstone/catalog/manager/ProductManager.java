package capstone.catalog.manager;

import capstone.catalog.dto.Product;
import capstone.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManager {

    private final ProductRepository repository;

    @Autowired
    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<Product> getProductById(String productId) {
        return repository.getById(productId);
    }

    public List<Product> getBySKU(String sku) {
        return repository.getBySKU(sku);
    }
}
