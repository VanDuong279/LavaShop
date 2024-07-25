package codegym.vn.lavashop.controller;

import codegym.vn.lavashop.dto.ProductDto;
import codegym.vn.lavashop.model.Product;
import codegym.vn.lavashop.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class RestProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    private ResponseEntity<Page<Product>> getList(Pageable pageable) {
        Page<Product> productPage = productService.findAll(pageable);
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(productPage, HttpStatus.NO_CONTENT); // 204
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK); // 200
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> findById(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<?> save(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // In ra chi tiết lỗi
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable int id, @RequestBody ProductDto productDto) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(productDto,product);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
