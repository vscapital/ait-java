package ait.co49.shop.service;

import ait.co49.shop.model.entity.Customer;
import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id, Customer customer);
    Customer deleteCustomer(Long id);
    Customer findByEmail(String email);
}