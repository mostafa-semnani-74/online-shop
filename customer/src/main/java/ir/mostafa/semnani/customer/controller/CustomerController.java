package ir.mostafa.semnani.customer.controller;

import ir.mostafa.semnani.customer.document.Customer;
import ir.mostafa.semnani.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

}
