package ir.mostafa.semnani.notification.table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

@Getter
@Setter
@PrimaryKeyClass
public class NotificationPrimaryKey {

    @PrimaryKeyColumn(name = "type", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String type;

    @PrimaryKeyColumn(name = "timeUUID", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID timeUUID;

}
