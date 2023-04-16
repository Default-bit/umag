package com.example.umag.service;

import com.example.umag.SaleRepository;
import com.example.umag.api.model.Sale;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SaleService {

    private final SaleRepository saleRepository;

//    public List<Sale> getAllSales() {
//        return saleRepository.findAll();
//    }

    public List<Sale> getSalesById(String id) {
        return saleRepository.findAllById(id);
    }

    public void deleteById(String id) {
        Optional<Sale> s = saleRepository.findById(id);
        if(s.isPresent()) {
            saleRepository.deleteById(id);
        }
    }

    public void updateById(String id, Sale s) {
//        Sale sale(barcode, price, quantity, saleTime);
//        saleRepository.insert(sale);
        Sale sale = saleRepository.findById(id).orElse(null);
        if(s != null){
            sale.setBarcode(s.getBarcode());
            sale.setPrice(s.getPrice());
            sale.setQuantity(s.getQuantity());
            sale.setTime(s.getTime());
            saleRepository.save(sale);
        }
    }

    public String createSale(Sale s) {
        saleRepository.insert(s);
        return s.getId();
    }

    long convertDateToInt(String date) {
        long y = (date.indexOf(0) - '0') * 1000L +
                (date.indexOf(1) - '0') * 100 +
                (date.indexOf(2) - '0') * 10 +
                (date.indexOf(3) - '0');
        long m = (date.indexOf(5) - '0') * 10L +
                (date.indexOf(6) - '0');
        long d = (date.indexOf(8) - '0') * 10L +
                (date.indexOf(9) - '0');
        long h = (date.indexOf(10) - '0') * 10L +
                (date.indexOf(11) - '0');
        long mm = (date.indexOf(13) - '0') * 10L +
                (date.indexOf(14) - '0');
        long s = (date.indexOf(16) - '0') * 10L +
                (date.indexOf(17) - '0');

        return s + mm * 60 + h * 60 * 60 + d * 24 * 60 * 60 +
                m * 32 * 24 * 60 * 60 + y * 12 * 32 * 24 * 60 * 60;
    }
    public List<Sale> getSalesByTime(String fromTime, String toTime, String barcode) {
        List<Sale> listSales = saleRepository.findAll();
        long ft = convertDateToInt(fromTime);
        long tt = convertDateToInt(toTime);
        List<Sale> sales = new ArrayList<>();
        for (Sale s: listSales) {
            long st = convertDateToInt(s.getTime());
            if (s.getBarcode() == barcode && ft <= st && st <= tt) {
                sales.add(s);
            }
        }
        return sales;
    }

    private List<Sale> saleList;
    public Optional<Sale> getSale(Integer id) {
        Optional optional = Optional.empty();
        for (Sale sale: saleList) {
            if(id.equals(sale.getId())) {
                optional = Optional.of(sale);
                return optional;
            }
        }
        return optional;
    }

}
