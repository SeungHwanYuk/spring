package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {
    ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }


    public List<Product> getProductListByLessInventory(int inventory) {
        List<Product> productList = productRepository.findAll();
        List<Product> productList1 = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getInventory() < inventory) {
                productList1.add(productList.get(i));
            }
        }
        if (productList1.isEmpty()) {
            throw new ResourceNotFoundException("Order","Date"," ");
        } else {
            return productList1;
        }
    }
}

