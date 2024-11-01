package ir.mostafa.semnani.notification.repository;

import ir.mostafa.semnani.notification.table.Notification;
import ir.mostafa.semnani.notification.table.NotificationPrimaryKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CassandraRepository<Notification, NotificationPrimaryKey> {

}