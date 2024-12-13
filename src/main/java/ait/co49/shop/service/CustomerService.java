package ait.co49.shop.service;

import ait.co49.shop.model.dto.CustomerDto;
import java.util.List;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Long id);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    CustomerDto deleteCustomer(Long id);
    CustomerDto findByEmail(String email);
}