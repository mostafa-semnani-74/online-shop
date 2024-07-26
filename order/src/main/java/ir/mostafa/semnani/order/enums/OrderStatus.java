package ir.mostafa.semnani.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    IN_PROGRESS(1),
    CANCELLED(2),
    DONE(3);

    private final int id;
}
