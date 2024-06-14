package ir.mostafa.semnani.customer.service;

import ir.mostafa.semnani.customer.document.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);

    List<Customer> findAll();
}
