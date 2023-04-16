package com.example.umag;

import com.example.umag.api.model.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends MongoRepository<Sale, String> {
    // Optional<Sale> findAllBy_id(String id);
    Optional<Sale> findById(String id);
    List<Sale> findAllById(String id);
    void deleteById(String id);
    // void updateById(String id, String barcode, int price, int quantity, String saleTime);



}
