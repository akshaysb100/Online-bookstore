package com.onlinebookstore.service;

import com.onlinebookstore.exception.CountryNotFoundException;
import com.onlinebookstore.model.Book;
import com.onlinebookstore.repository.OnlineBookRepository;
import com.onlinebookstore.utility.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
@PropertySource(value = {"classpath:message.properties"})
@Service
public class BookPriceCalculatorService {

    private final HashMap<String, ShippingChargeProvider> enumMapper;

    @Autowired
    OnlineBookRepository onlineBookRepository;

    @Autowired
    private Environment environment;

    public BookPriceCalculatorService() {
        this.enumMapper = new HashMap<>();
        this.enumMapper.put("INDIA",CountryWiseShippingCharges.INDIA);
        this.enumMapper.put("OTHER",CountryWiseShippingCharges.OTHER_COUNTRY);
    }

    public Double calculatePriceOfBookAsPerCountry(Long id, String countryOfCustomer) {
        Book book = onlineBookRepository.findBookById(id);
        ShippingChargeProvider provider = enumMapper.get(countryOfCustomer.toUpperCase());
        Double totalBookPrice = provider.getShippingCharge() + book.getPrice();
        return totalBookPrice;
    }
}
