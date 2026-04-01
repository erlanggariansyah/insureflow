INSERT INTO methods (id, name) VALUES
(1, 'Flat'),
(2, 'Per 1000 Sum Assured'),
(3, 'Percentage by Premium'),
(4, 'Percentage by Sum Assured');

INSERT INTO rider_types (id, code, name) VALUES
('660e8400-e29b-41d4-a716-446655440200', 'WAIVER', 'Waiver of Premium'),
('660e8400-e29b-41d4-a716-446655440201', 'ACCIDENTAL', 'Accidental Benefits'),
('660e8400-e29b-41d4-a716-446655440202', 'HEALTH', 'Health & Hospitalization'),
('660e8400-e29b-41d4-a716-446655440203', 'CRITICAL', 'Critical Illness'),
('660e8400-e29b-41d4-a716-446655440204', 'DISMEMBERMENT', 'Dismemberment Benefit');

INSERT INTO riders (id, code, name, rider_type_id, methods) VALUES
('660e8400-e29b-41d4-a716-446655440300', 'WPB', 'Waiver of Premium Benefit',
 '660e8400-e29b-41d4-a716-446655440200', 3),
('660e8400-e29b-41d4-a716-446655440301', 'ADB', 'Accidental Death Benefit',
 '660e8400-e29b-41d4-a716-446655440201', 2),
('660e8400-e29b-41d4-a716-446655440302', 'HOSP', 'Hospitalization Cash Benefit',
 '660e8400-e29b-41d4-a716-446655440202', 1),
('660e8400-e29b-41d4-a716-446655440303', 'CI37', 'Critical Illness 37 Diseases',
 '660e8400-e29b-41d4-a716-446655440203', 4),
('660e8400-e29b-41d4-a716-446655440304', 'PAD', 'Personal Accident Dismemberment',
 '660e8400-e29b-41d4-a716-446655440204', 2);

INSERT INTO rider_rates (id, rider_id, age, gender, rate) VALUES
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 18, NULL, 0.250000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 25, NULL, 0.300000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 30, NULL, 0.400000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 35, NULL, 0.550000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 40, NULL, 0.750000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 45, NULL, 1.000000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 50, NULL, 1.350000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 55, NULL, 1.800000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 60, NULL, 2.400000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440301', 65, NULL, 3.200000);

INSERT INTO rider_rates (id, rider_id, age, gender, rate) VALUES
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 18, NULL, 2.500000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 25, NULL, 2.800000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 30, NULL, 3.200000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 35, NULL, 4.100000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 40, NULL, 5.500000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 45, NULL, 7.200000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 50, NULL, 9.500000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 55, NULL, 12.000000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440300', 60, NULL, 15.500000);

INSERT INTO rider_rates (id, rider_id, age, gender, rate) VALUES
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440302', 0, NULL, 500000.000000);

INSERT INTO rider_rates (id, rider_id, age, gender, rate) VALUES
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 18, 1, 0.180000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 25, 1, 0.220000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 30, 1, 0.280000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 40, 1, 0.450000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 50, 1, 0.700000);

INSERT INTO rider_rates (id, rider_id, age, gender, rate) VALUES
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 18, 0, 0.140000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 25, 0, 0.170000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 30, 0, 0.220000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 40, 0, 0.350000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440304', 50, 0, 0.550000);

INSERT INTO rider_rates (id, rider_id, age, gender, rate) VALUES
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440303', 18, NULL, 100.000000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440303', 30, NULL, 100.000000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440303', 45, NULL, 100.000000),
(gen_random_uuid(), '660e8400-e29b-41d4-a716-446655440303', 60, NULL, 100.000000);
