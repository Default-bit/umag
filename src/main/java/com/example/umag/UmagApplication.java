package com.example.umag;

import com.example.umag.api.model.Sale;
import com.example.umag.api.model.Supply;
import com.jayway.jsonpath.Criteria;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


@SpringBootApplication(scanBasePackages = {"com.example"})
public class UmagApplication {

    public static void main(String[] args) { SpringApplication.run(UmagApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(SaleRepository saleRepository, MongoTemplate mongoTemplate){
//        return args -> {
//            Sale sale = new Sale("9", 10, 5, "2023-03-09 12:00:00");
//
////            Query query = new Query();
////            query.addCriteria((CriteriaDefinition) Criteria.where("time").is(sale.getId()));
////
////            mongoTemplate.find(query, Sale.class);
//
//
//            saleRepository.insert(sale);
//
//
//        };
//    }
    @Bean
    CommandLineRunner runner1(SupplyRepository supplyRepository, MongoTemplate mongoTemplate){
        return args -> {
            Supply supply = new Supply("9", 10, 5, "2023-03-09 12:00:00");

            supplyRepository.insert(supply);

        };
    }
}
