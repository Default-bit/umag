package com.example.umag;

import com.example.umag.api.model.Sale;
import com.example.umag.api.model.Supply;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SupplyRepository extends MongoRepository<Supply, String> {
    // Optional<Sale> findAllBy_id(String id);
    Optional<Supply> findById(String id);
    List<Supply> findAllById(String id);
    void deleteById(String id);
    // void updateById(String id, String barcode, int price, int quantity, String saleTime);
}
