package com.telecom.tsms.service;

import com.telecom.tsms.dto.MobileNumberRequest;
import com.telecom.tsms.entity.*;
import com.telecom.tsms.enums.NotificationStatus;
import com.telecom.tsms.enums.NotificationType;
import com.telecom.tsms.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendRechargeSuccessNotification(RechargeTransaction transaction) {

        MobileNumber mobileNumber = transaction.getSubscription().getMobileNumber();
        BigDecimal amount  = transaction.getAmount();
        TelecomPlan plan = transaction.getSubscription().getTelecomPlan();
        String planName = plan.getPlanName();
        String title = "RECHARGE SUCCESSFUL";
        String message = "Your recharge of ₹ "+amount+" for "+planName+" was successful.";
        NotificationType notificationType = NotificationType.RECHARGE_SUCCESS;
        createNotification(mobileNumber,title,message,notificationType);

    }

    @Override
    public void sendRechargeFailedNotification(RechargeTransaction transaction) {

        MobileNumber mobileNumber = transaction.getSubscription().getMobileNumber();
        BigDecimal amount  = transaction.getAmount();
        TelecomPlan plan = transaction.getSubscription().getTelecomPlan();
        String planName = plan.getPlanName();
        String title = "RECHARGE FAILED";
        String message = "Your recharge of ₹ "+amount+" for "+planName+" has failed. Please try again.";
        NotificationType notificationType = NotificationType.RECHARGE_FAILED;
        createNotification(mobileNumber,title,message,notificationType);

    }

    @Override
    public void sendComplaintCreatedNotification(Complaint complaint) {

        MobileNumber mobileNumber = complaint.getMobileNumber();
        String title = "COMPLAINT CREATED";
        String message = "Your complaint has been registered and is under review.";
        NotificationType notificationType = NotificationType.COMPLAINT_CREATED;
        createNotification(mobileNumber,title,message,notificationType);

    }

    @Override
    public void sendComplaintResolvedNotification(Complaint complaint) {

        MobileNumber mobileNumber = complaint.getMobileNumber();
        String title = "COMPLAINT RESOLVED";
        String message = "Your complaint has been resolved.";
        NotificationType notificationType = NotificationType.COMPLAINT_RESOLVED;
        createNotification(mobileNumber,title,message,notificationType);

    }

    private void createNotification(
        MobileNumber mobileNumber,
        String title,
        String message,
        NotificationType notificationType
    )
    {
        Notification notification = Notification.builder()
                .mobileNumber(mobileNumber)
                .title(title)
                .message(message)
                .notificationType(notificationType)
                .notificationStatus(NotificationStatus.SENT)
                .createdAt(LocalDateTime.now())
                .sentAt(LocalDateTime.now())
                .read(false)
                .build();

        Notification savedNotification = notificationRepository.save(notification);
    }

}
