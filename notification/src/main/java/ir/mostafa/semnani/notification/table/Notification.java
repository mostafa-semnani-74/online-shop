package ir.mostafa.semnani.notification.table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("notification")
@Getter
@Setter
public class Notification {

    @PrimaryKey
    private NotificationPrimaryKey primaryKey;

}
