package ir.mostafa.semnani.customer.repository;

import ir.mostafa.semnani.customer.document.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {
}
