package codegym.vn.lavashop.service;

import codegym.vn.lavashop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    List<Product> search(String searchName);
    Page<Product> search(String searchName,Pageable pageable);
    Product findById(int id);
    void save(Product product);
    void delete(Product product);
};
