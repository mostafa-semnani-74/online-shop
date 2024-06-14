package ir.mostafa.semnani.customer.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("customers")
public class Customer {
    @Id
    private String id;

    private String name;

}
