package capstone.catalog.repository;

import capstone.catalog.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final Map<String, Product> byProductId;
    private final Map<String, List<Product>> bySKU;


    @Autowired
    public ProductRepository(ProductLoader loader) {
        final List<Product> products = loader.loadProducts();
        Map<String, Product> byProductIdMap = products.stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));
        this.byProductId = Collections.unmodifiableMap(byProductIdMap);
        Map<String, List<Product>> bySKUMap = products.stream()
                .collect(Collectors.groupingBy(Product::getSku));
        this.bySKU = Collections.unmodifiableMap(bySKUMap);
    }

    public Optional<Product> getById(String productId) {
        return Optional.ofNullable(byProductId.get(productId));
    }

    public List<Product> getBySKU(String sku) {
        return Optional.ofNullable(bySKU.get(sku))
                .orElse(Collections.emptyList());
    }
}
