package dw.wholesale_company.controller;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class
ProductController {
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/products/search/inventory/less/{inventory}")
    public ResponseEntity<List<Product>> getProductListByLessInventory(@PathVariable int inventory) {
        return new ResponseEntity<>(productService.getProductListByLessInventory(inventory),HttpStatus.OK);
    }

    @GetMapping("/products/search/name/{name}")
    public ResponseEntity<List<Product>> getProductListByName(@PathVariable String name) {
        return new ResponseEntity<>(productService.getProductListByName(name), HttpStatus.OK);
    }

    @GetMapping("/products/search/price")
    public ResponseEntity<List<Product>> getProductListByBetweenPrice(@RequestParam int lowLimit,
                                                                      @RequestParam int highLimit) {
        return new ResponseEntity<>(productService.getProductListByBetweenPrice(lowLimit,highLimit),HttpStatus.OK);
    }

    @GetMapping("/products/search_list")
    public ResponseEntity<List<Product>> getProductByProductList(@RequestBody List<Long> productIdList) {
        return new ResponseEntity<>(productService.getProductByProductList(productIdList),HttpStatus.OK);
    }
}
