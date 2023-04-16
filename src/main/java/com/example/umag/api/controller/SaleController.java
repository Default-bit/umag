package com.example.umag.api.controller;

import com.example.umag.api.model.Sale;
import com.example.umag.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/sales")
@AllArgsConstructor
@ResponseBody

public class SaleController {
    private SaleService saleService;

//    @GetMapping
//    public List<Sale> fetchAllSales(){
//        return saleService.getAllSales();
//    }
    @GetMapping()
    public List<Sale> fetchSalesByTime(@PathVariable String barcode, @PathVariable String fromTime, @PathVariable String toTime){
        return saleService.getSalesByTime(fromTime, toTime, barcode);
    }

    @GetMapping("/{id}")
    public List<Sale> fetchSalesById(@PathVariable String id){
        return saleService.getSalesById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSaleById(@PathVariable String id){
        saleService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateSaleById(@PathVariable String id, @RequestBody Sale s){
        saleService.updateById(id, s);
    }

    @PostMapping
    public String createSale(@RequestBody Sale s){
        return saleService.createSale(s);
    }

//    @GetMapping("/sale")
//    public Sale getSale(@RequestParam Integer id){
//        Optional sale = saleService.getSale(id);
//        if(sale.isPresent()){
//            return (Sale) sale.get();
//        }
//        return null;
//    }
}
