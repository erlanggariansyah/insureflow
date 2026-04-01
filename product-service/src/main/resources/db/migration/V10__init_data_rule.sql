INSERT INTO rule_types (id, name) VALUES
('770e8400-e29b-41d4-a716-446655440400', 'AGE_VALIDATION'),
('770e8400-e29b-41d4-a716-446655440401', 'SUM_ASSURED_VALIDATION'),
('770e8400-e29b-41d4-a716-446655440402', 'GENDER_RESTRICTION'),
('770e8400-e29b-41d4-a716-446655440403', 'MATURITY_LIMIT');

INSERT INTO rules (sub_id, type_id, name, expression, message) VALUES
('550e8400-e29b-41d4-a716-446655440001', '770e8400-e29b-41d4-a716-446655440400',
 'TL1_AGE_LIMIT', '#input.age >= 18 and #input.age <= 60',
 'Usia masuk untuk Term Life Protection First harus antara 18 hingga 60 tahun'),
('550e8400-e29b-41d4-a716-446655440001', '770e8400-e29b-41d4-a716-446655440401',
 'TL1_SA_LIMIT', '#input.sumAssured >= 50000000 and #input.sumAssured <= 5000000000',
 'Uang Pertanggungan harus di antara 50 Juta hingga 5 Miliar IDR'),
('550e8400-e29b-41d4-a716-446655440001', '770e8400-e29b-41d4-a716-446655440401',
 'TL1_SA_MULTIPLIER', '#input.sumAssured % 1000000 == 0',
 'Uang Pertanggungan harus dalam kelipatan 1.000.000');

INSERT INTO rules (sub_id, type_id, name, expression, message) VALUES
('660e8400-e29b-41d4-a716-446655440302', '770e8400-e29b-41d4-a716-446655440400',
 'HOSP_MIN_AGE', '#input.age >= 1',
 'Rider Hospitalization hanya tersedia untuk nasabah berusia minimal 1 tahun');

INSERT INTO rules (sub_id, type_id, name, expression, message) VALUES
('550e8400-e29b-41d4-a716-446655440006', '770e8400-e29b-41d4-a716-446655440403',
 'EN1_MATURITY_LIMIT', '#input.age + #input.tenure <= 25',
 'Masa asuransi pendidikan berakhir di usia 25 tahun. Silakan sesuaikan Masa Pertanggungan Anda.');