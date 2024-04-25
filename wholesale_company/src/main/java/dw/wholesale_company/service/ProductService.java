package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
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
            throw new ResourceNotFoundException("Order", "Date", " ");
        } else {
            return productList1;
        }
    }


    // 제품 중에서 제품명에 '주스'가 들어간 제품에 대한 모든 정보를 검색하시오

    public List<Product> getProductListByName(String name) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getProductName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Product> getProductListByBetweenPrice(int lowLimit, int highLimit) {
        List<Product> productList = productRepository.findAll();
        List<Product> productListHighLimit = new ArrayList<>();
        List<Product> productListLowLimit = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getUnitPrice() < highLimit) {
                productListHighLimit.add(productList.get(i));
            }
        }
        for (int i = 0; i < productListHighLimit.size(); i++) {
            if (productListHighLimit.get(i).getUnitPrice() > lowLimit) {
                productListLowLimit.add(productListHighLimit.get(i));
            }
        }
        return productListLowLimit;
    }

    //public List<Product> getProductListByBetweenPrice(int lowLimit, int highLimit) {
//    List<Product> productList = productRepository.findAll();
//    List<Product> productListHighLimit = new ArrayList<>();
//    for (int i = 0; i < productList.size(); i++) {
//        if (productList.get(i).getUnitPrice() < highLimit && productList.get(i).getUnitPrice() > lowLimit) {
//            productListHighLimit.add(productList.get(i));
//        }
//    }
//    return productListHighLimit;
//}
//}
    public List<Product> getProductByProductList(List<Long> productIdList) {
        List<Product> productList = productRepository.findAll();
        List<Product> productList1 = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            for (int j = 0; j < productIdList.size(); j++) {
                if (productList.get(i).getProductId() == productIdList.get(j))
                    productList1.add(productList.get(i));
            }
        }
        return productList1;
//    return productList
//            .stream()
//            .filter(product -> productIdList.contains(product.getProductId()))
//            .collect(Collectors.toList());
//
    }

    // 제품 재고금액이 높은 상위 10개 제품
//    public List<Product> sum() {
//        long num = 0;
//
//        List<Product> productList = productRepository.findAll();
//        List<Product> productList1 = new ArrayList<>();
//
//        for (int i = 0; i < productList.size(); i++) {
//            num = productList.get(i).getInventory()*productList.get(i).getUnitPrice();
//            productList1.get(i).getUnitPrice()
//        }
//              productList
//                      .stream()
//                      .sorted(Comparator.comparingLong())
//                      .collect(Collectors.toList());
//
//
//        }
//    }

    public List<Product> getLimit(int limit) {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .sorted(Comparator.comparingLong((Product p) -> p.getUnitPrice()*p.getInventory())
                        .reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}

