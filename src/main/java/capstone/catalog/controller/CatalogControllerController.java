package capstone.catalog.controller;

import capstone.catalog.dto.Product;
import capstone.catalog.dto.ProductNotFoundException;
import capstone.catalog.manager.ProductManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class CatalogControllerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ProductManager manger;

    @Autowired
    public CatalogControllerController(ProductManager manger) {
        this.manger = manger;
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public Product getById(
            @PathVariable(value = "productId", required = true) String productId
    ) throws Exception {
        //Thread.sleep(4000L);
        return manger.getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id [" + productId + "] doesn't exist"));
    }

    @RequestMapping(value = "/product/sku/{sku}", method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getBySKU(
            @PathVariable(value = "sku", required = true) String sku
    ) throws Exception {
        //Thread.sleep(4000L);
        return manger.getBySKU(sku);
    }

}