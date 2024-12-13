package ait.co49.shop.service;

import ait.co49.shop.model.dto.CustomerDto;
import ait.co49.shop.model.entity.Customer;
import ait.co49.shop.repository.CustomerRepository;
import ait.co49.shop.service.mapping.CustomerMappingService;
import ait.co49.shop.service.mapping.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMappingService customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMappingService customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapDtoToEntity(customerDto);
        Customer savedProduct = customerRepository.save(customer);
        return this.customerMapper.mapEntityToDto(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long id) {
        return this.customerMapper.mapEntityToDto(customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id)));
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = this.customerMapper.mapDtoToEntity(getCustomerById(id));
        existingCustomer.setFirstName(customerDto.getFirstName());
        existingCustomer.setLastName(customerDto.getLastName());
        existingCustomer.setEmail(customerDto.getEmail());
        return this.customerMapper.mapEntityToDto(customerRepository.save(existingCustomer));
    }

    @Override
    @Transactional
    public CustomerDto deleteCustomer(Long id) {
        CustomerDto customer = getCustomerById(id);
        customerRepository.deleteById(id);
        return customer;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findByEmail(String email) {
        return this.customerMapper.mapEntityToDto(customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with email: " + email)));
    }
}