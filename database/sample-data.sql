-- =========================================
-- CUSTOMER SAMPLE DATA
-- =========================================

INSERT INTO customer
(customer_code, full_name, mobile_number, email, state, kyc_status)
VALUES
('CUST005', 'Arya Chanakya', '9000000001', 'arya@telecom.com', 'Takshashila', 'VERIFIED'),

('CUST006', 'Chandragupta Maurya', '9000000002', 'chandra@telecom.com', 'Magadha', 'VERIFIED'),

('CUST007', 'Ashoka Maurya', '9000000003', 'ashoka@telecom.com', 'Pataliputra', 'PENDING');




-- =========================================
-- TELECOM PLAN SAMPLE DATA
-- =========================================

INSERT INTO telecom_plan
(plan_code, plan_name, plan_type, validity_days,
price, data_limit_gb, sms_limit, voice_limit_minutes, active)
VALUES

('PLAN499', 'Unlimited Combo Plan', 'COMBO',
84, 499.00, 2.0, 100, 9999, true),

('PLAN299', 'Daily Data Saver', 'DATA',
28, 299.00, 1.5, 100, 300, true),

('PLAN199', 'Talktime Basic', 'VOICE',
28, 199.00, 0.5, 50, 1000, true);


-- =========================================
-- SUBSCRIPTION SAMPLE DATA
-- =========================================

INSERT INTO subscription
(customer_id, plan_id, activation_date, expiry_date, status)
VALUES

(2, 3, '2026-05-01', '2026-07-24', 'ACTIVE'),

(3, 2, '2026-05-03', '2026-06-28', 'ACTIVE'),

(4, 5, '2026-04-01', '2026-04-29', 'EXPIRED'),

(6, 4, '2026-05-05', '2026-06-02', 'ACTIVE'),

(7, 3, '2026-05-07', '2026-07-30', 'ACTIVE');