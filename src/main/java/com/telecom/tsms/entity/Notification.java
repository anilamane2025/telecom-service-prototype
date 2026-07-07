package com.telecom.tsms.entity;

import com.telecom.tsms.enums.NotificationStatus;
import com.telecom.tsms.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mobile_number_id",nullable = false)
    private MobileNumber mobileNumber;

    @Column(name="title",nullable = false,length = 30)
    private String title;

    @Column(name = "message",nullable = false,length = 500)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name="notification_type",nullable = false)
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    @Column(name="notification_status",nullable = false)
    private NotificationStatus notificationStatus;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name="sent_at")
    private LocalDateTime sentAt;

    @Column(name="is_read",nullable = false)
    private boolean read;
}
