package com.telecom.tsms.service;

import com.telecom.tsms.entity.Complaint;
import com.telecom.tsms.entity.RechargeTransaction;

public interface NotificationService {
    void sendRechargeSuccessNotification(RechargeTransaction transaction);
    void sendRechargeFailedNotification(RechargeTransaction transaction);
    void sendComplaintCreatedNotification(Complaint complaint);
    void sendComplaintResolvedNotification(Complaint complaint);
}