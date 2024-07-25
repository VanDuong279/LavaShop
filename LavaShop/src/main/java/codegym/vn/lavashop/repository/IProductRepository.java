package codegym.vn.lavashop.repository;

import codegym.vn.lavashop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findStudentByNameContaining(String searchName);
    Page<Product> findStudentByNameContaining(String searchName, Pageable pageable);

    @Query(value = "select * from product where name like concat('%',:searchName,'%')",nativeQuery = true)
    List<Product> searchByName(@Param("searchName") String searchName);
}
