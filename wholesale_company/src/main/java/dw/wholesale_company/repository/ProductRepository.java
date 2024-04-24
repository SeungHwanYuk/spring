package dw.wholesale_company.repository;

import dw.wholesale_company.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    List<Product> findByProductId(List<Integer> productIdList);
}
