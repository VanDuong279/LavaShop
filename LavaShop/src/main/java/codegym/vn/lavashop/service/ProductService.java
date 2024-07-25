package codegym.vn.lavashop.service;

import codegym.vn.lavashop.model.Product;
import codegym.vn.lavashop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository repository;
    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Product> search(String searchName) {
        return repository.searchByName("%" + searchName + "%");
    }

    @Override
    public Page<Product> search(String searchName, Pageable pageable) {
        return repository.findStudentByNameContaining(searchName,pageable);
    }

    @Override
    public Product findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }
}
