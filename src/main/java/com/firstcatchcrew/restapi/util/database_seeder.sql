
-- Insert roles
INSERT INTO user_role (id, type, label, description) VALUES (1, 'FISHER', 'Fisher', 'Can manage catches');
INSERT INTO user_role (id, type, label, description) VALUES (2, 'CUSTOMER', 'Customer', 'Can place orders');
INSERT INTO user_role (id, type, label, description) VALUES (3, 'ADMIN', 'Admin', 'Can place orders');

-- Insert people (fishers and customers)
INSERT INTO person (id, username, email, password, role_id) VALUES (1, 'fisher1', 'fisher1@example.com', 'fisherpass1', 1);
INSERT INTO person (id, username, email, password, role_id) VALUES (2, 'fisher2', 'fisher2@example.com', 'fisherpass2', 1);
INSERT INTO person (id, username, email, password, role_id) VALUES (3, 'fisher3', 'fisher3@example.com', 'fisherpass3', 1);
INSERT INTO person (id, username, email, password, role_id) VALUES (4, 'customer0', 'customer0@example.com', 'custpass0', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (5, 'customer1', 'customer1@example.com', 'custpass1', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (6, 'customer2', 'customer2@example.com', 'custpass2', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (7, 'customer3', 'customer3@example.com', 'custpass3', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (8, 'customer4', 'customer4@example.com', 'custpass4', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (9, 'customer5', 'customer5@example.com', 'custpass5', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (10, 'customer6', 'customer6@example.com', 'custpass6', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (11, 'customer7', 'customer7@example.com', 'custpass7', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (12, 'customer8', 'customer8@example.com', 'custpass8', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (13, 'customer9', 'customer9@example.com', 'custpass9', 2);
INSERT INTO person (id, username, email, password, role_id) VALUES (14, 'admin', 'admin@example.com', 'adminpass', 3);

-- Insert landings
INSERT INTO landing (id, name) VALUES (1, 'North Dock');
INSERT INTO landing (id, name) VALUES (2, 'South Dock');
INSERT INTO landing (id, name) VALUES (3, 'West Bay');

-- Insert species
INSERT INTO species (id, species_name) VALUES (1, 'Cod');
INSERT INTO species (id, species_name) VALUES (2, 'Halibut');
INSERT INTO species (id, species_name) VALUES (3, 'Mackerel');
INSERT INTO species (id, species_name) VALUES (4, 'Tuna');
INSERT INTO species (id, species_name) VALUES (5, 'Salmon');

INSERT INTO fisher_profile (person_id, fishing_license_number, default_landing_id)VALUES    (1, 'LIC-001', 1),    (2, 'LIC-002', 2),    (3, 'LIC-003', 3);


INSERT INTO fisher_profile (person_id, fishing_license_number, default_landing_id)
VALUES
    (1, 'LIC-001', 1),
    (2, 'LIC-002', 2),
    (3, 'LIC-003', 3);


-- Insert catches
-- (Catch insert SQL lines are added here from the previous completion)
-- Insert catches
INSERT INTO catch (
    id, fisher_profile_id, species_id, available, sold,
    time_stamp, quantity_in_kg, price,
    landing_id, pickup_instructions, pickup_time,
    latitude, longitude
) VALUES
      (1, 2, 1, true, false, '2025-05-24 09:00:00', 15.62, 31.04, 1, 'Call ahead', '2025-05-24 14:00:00', 47.56061, -52.694983),
      (2, 3, 1, false, true, '2025-05-26 10:00:00', 12.99, 52.11, 2, 'Paid already', '2025-05-26 14:00:00', 47.574873, -52.697402),
      (3, 2, 1, true, false, '2025-05-30 10:00:00', 4.53, 30.44, 1, 'Call ahead', '2025-05-30 12:00:00', 47.575784, -52.709358),
      (4, 1, 5, true, false, '2025-05-31 09:00:00', 3.92, 38.35, 3, 'Ring bell', '2025-05-31 12:00:00', 47.57112, -52.692956),
      (5, 2, 2, true, false, '2025-06-09 10:00:00', 14.89, 31.15, 3, 'Bring ID', '2025-06-09 14:00:00', 47.563612, -52.702622),
      (6, 3, 1, false, true, '2025-06-01 12:00:00', 17.96, 21.64, 1, 'Paid already', '2025-06-01 14:00:00', 47.576021, -52.698333),
      (7, 3, 3, false, true, '2025-05-29 12:00:00', 7.89, 31.41, 1, 'Bring ID', '2025-05-29 14:00:00', 47.560912, -52.706935),
      (8, 3, 3, false, true, '2025-05-24 11:00:00', 19.58, 49.70, 3, 'Call ahead', '2025-05-24 16:00:00', 47.563961, -52.698046),
      (9, 3, 1, false, true, '2025-06-01 09:00:00', 2.45, 51.96, 2, 'Call ahead', '2025-06-01 12:00:00', 47.570176, -52.690719),
      (10, 2, 1, false, true, '2025-06-19 08:00:00', 2.62, 57.22, 2, 'Ask for Joe', '2025-06-19 13:00:00', 47.564273, -52.704363),
      (11, 3, 3, false, true, '2025-05-26 13:00:00', 8.12, 38.17, 2, 'Bring ID', '2025-05-26 18:00:00', 47.571106, -52.708053),
      (12, 3, 3, true, false, '2025-06-11 08:00:00', 12.31, 20.79, 1, 'Bring ID', '2025-06-11 11:00:00', 47.564854, -52.707962),
      (13, 2, 4, true, false, '2025-05-28 10:00:00', 6.05, 28.85, 1, 'Bring ID', '2025-05-28 12:00:00', 47.575213, -52.692381),
      (14, 2, 4, true, false, '2025-06-12 08:00:00', 10.52, 50.18, 2, 'Bring ID', '2025-06-12 10:00:00', 47.569158, -52.694964),
      (15, 1, 2, true, false, '2025-05-22 08:00:00', 13.38, 27.87, 1, 'Call ahead', '2025-05-22 12:00:00', 47.572379, -52.695826);

INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (1, '2025-06-19 10:00:00', 'PAID', 4);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (2, '2025-06-22 12:00:00', 'FULFILLED', 5);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (3, '2025-06-20 09:00:00', 'PENDING', 6);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (4, '2025-06-25 11:00:00', 'CANCELLED', 7);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (5, '2025-06-26 14:00:00', 'PAID', 8);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (6, '2025-06-18 08:00:00', 'FULFILLED', 9);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (7, '2025-06-23 15:00:00', 'PAID', 10);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (8, '2025-06-21 10:30:00', 'PENDING', 11);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (9, '2025-06-28 13:45:00', 'FULFILLED', 12);
INSERT INTO orders (id, order_date_time, order_status, person_id) VALUES (10, '2025-06-30 16:00:00', 'CANCELLED', 13);

INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (1, 2.50, 1, 1);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (2, 1.00, 3, 1);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (3, 3.75, 4, 2);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (4, 1.25, 2, 2);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (5, 4.00, 5, 3);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (6, 2.25, 7, 3);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (7, 1.10, 6, 4);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (8, 2.80, 8, 5);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (9, 3.33, 10, 6);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (10, 2.00, 9, 6);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (11, 1.90, 11, 7);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (12, 2.60, 13, 8);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (13, 3.00, 12, 9);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (14, 1.50, 15, 9);
INSERT INTO order_items (id, quantity, catch_id, order_id) VALUES (15, 2.75, 14, 10);

