package com.example.umag.service;

import com.example.umag.SupplyRepository;
import com.example.umag.api.model.Sale;
import com.example.umag.api.model.Supply;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;
    public List<Supply> getSupplyById(String id) {
        return supplyRepository.findAllById(id);
    }

    public void deleteById(String id) {
        Optional<Supply> s = supplyRepository.findById(id);
        if(s.isPresent()) {
            supplyRepository.deleteById(id);
        }
    }

    public void updateById(String id, Supply supply) {
//        Sale sale(barcode, price, quantity, saleTime);
//        saleRepository.insert(sale);
        Supply s = supplyRepository.findById(id).orElse(null);
        if(s != null){
            s.setBarcode(supply.getBarcode());
            s.setPrice(supply.getPrice());
            s.setQuantity(supply.getQuantity());
            s.setTime(supply.getTime());
            supplyRepository.save(s);
        }
    }

    public String createSupply(Supply s) {
        supplyRepository.insert(s);
        return s.getId();
    }

    long convertDateToInt(String date) {
        long res = 0;
        long y = (date.indexOf(0) - '0') * 1000 +
                (date.indexOf(1) - '0') * 100 +
                (date.indexOf(2) - '0') * 10 +
                (date.indexOf(3) - '0');
        long m = (date.indexOf(5) - '0') * 10 +
                (date.indexOf(6) - '0');
        long d = (date.indexOf(8) - '0') * 10 +
                (date.indexOf(9) - '0');
        long h = (date.indexOf(10) - '0') * 10 +
                (date.indexOf(11) - '0');
        long mm = (date.indexOf(13) - '0') * 10 +
                (date.indexOf(14) - '0');
        long s = (date.indexOf(16) - '0') * 10 +
                (date.indexOf(17) - '0');

        return s + mm * 60 + h * 60 * 60 + d * 24 * 60 * 60 +
                m * 32 * 24 * 60 * 60 + y * 12 * 32 * 24 * 60 * 60;
    }
    public List<Supply> getSuppliesByTime(String fromTime, String toTime, String barcode) {
        List<Supply> listSupplies = supplyRepository.findAll();
        long ft = convertDateToInt(fromTime);
        long tt = convertDateToInt(toTime);
        List<Supply> supplies = new ArrayList<>();
        for (Supply s: listSupplies) {
            long st = convertDateToInt(s.getTime());
            if (s.getBarcode() == barcode && ft <= st && st <= tt) {
                supplies.add(s);
            }
        }
        return supplies;
    }

    private List<Supply> supplyList;

    public Optional<Supply> getSupply(Integer id) {
        Optional optional = Optional.empty();
        for (Supply supply: supplyList) {
            if(id.equals(supply.getId())) {
                optional = Optional.of(supply);
                return optional;
            }
        }
        return optional;
    }

}
