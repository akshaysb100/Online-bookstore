package com.onlinebookstore.service;

import com.onlinebookstore.model.Book;
import com.onlinebookstore.model.Customer;
import com.onlinebookstore.repository.OnlineBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class BookPriceCalculatorService {

    private final HashMap<String, ShippingChargeProvider> enumMapper;
    @Autowired
    OnlineBookRepository onlineBookRepository;

    public BookPriceCalculatorService() {
        this.enumMapper = new HashMap<String,ShippingChargeProvider>();
        this.enumMapper.put("INDIA",CountryWiseShippingCharges.INDIA);
        this.enumMapper.put("OTHER",CountryWiseShippingCharges.OTHER_COUNTRY);
    }

    public Double calculatePriceOfBookAsPerCountry(Long id, String countryOfCustomer){
        Optional<Book> book = onlineBookRepository.findById(id);
        String countryName = countryOfCustomer.toUpperCase();
        Double totalBookPrice = enumMapper.get(countryName).getShippingCharge() + book.get().getPrice();
        return totalBookPrice;
    }
}
