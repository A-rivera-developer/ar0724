INSERT INTO tools (code, type, brand) VALUES ('CHNS', 'Chainsaw', 'Stihl');
INSERT INTO tools (code, type, brand) VALUES ('LADW', 'Ladder', 'Werner');
INSERT INTO tools (code, type, brand) VALUES ('JAKD', 'Jackhammer', 'DeWalt');
INSERT INTO tools (code, type, brand) VALUES ('JAKR', 'Jackhammer', 'Ridgid');

INSERT INTO tool_charges (type, daily_charge, charge_weekdays, charge_weekends, charge_holidays) VALUES ('Ladder', 1.99, true, true, false);
INSERT INTO tool_charges (type, daily_charge, charge_weekdays, charge_weekends, charge_holidays) VALUES ('Chainsaw', 1.49, true, false, true);
INSERT INTO tool_charges (type, daily_charge, charge_weekdays, charge_weekends, charge_holidays) VALUES ('Jackhammer', 2.99, true, false, false);

