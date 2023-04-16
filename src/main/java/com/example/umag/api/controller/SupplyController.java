package com.example.umag.api.controller;

import com.example.umag.api.model.Sale;
import com.example.umag.api.model.Supply;
import com.example.umag.service.SupplyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/supplies")
@AllArgsConstructor
@ResponseBody
public class SupplyController {
    private SupplyService supplyService;

    @GetMapping()
    public List<Supply> fetchSuppliesByTime(@RequestParam String barcode, String fromTime, String toTime){
        return supplyService.getSuppliesByTime(fromTime, toTime, barcode);
    }

    @GetMapping("/{id}")
    public List<Supply> fetchSuppliesById(@PathVariable String id){
        return supplyService.getSupplyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplyById(@PathVariable String id){
        supplyService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateSupplyById(@PathVariable String id, @RequestBody Supply s){
        supplyService.updateById(id, s);
    }

    @PostMapping
    public String createSupply(@RequestBody Supply s){
        return supplyService.createSupply(s);
    }

//    @GetMapping("/supply")
//    public Supply getSupply(@RequestParam Integer id){
//        Optional supply = supplyService.getSupply(id);
//        if(supply.isPresent()){
//            return (Supply) supply.get();
//        }
//        return null;
//    }
}
