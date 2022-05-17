package com.mymicroservice.notification.dataAccess;

import com.mymicroservice.notification.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {


}
