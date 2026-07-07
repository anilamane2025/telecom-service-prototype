package com.telecom.tsms.repository;

import com.telecom.tsms.entity.Complaint;
import com.telecom.tsms.entity.Notification;
import com.telecom.tsms.entity.RechargeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
