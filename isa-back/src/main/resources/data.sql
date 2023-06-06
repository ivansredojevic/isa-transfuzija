
 

-- centers
insert into center (center_name, address, rating, open_time, closed_time) values ('KCV', 'Ulica KCV 1, Novi Sad, Srbija', '4.0', '07:00', '23:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Kamenica', 'Kamenica, Novi Sad, Srbija', '4.2', '05:00', '22:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Urgentni', 'Cara Dusana 1, Novi Sad, Srbija', '3.5', '01:00', '23:00');


-- admin
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty) values ('application_user', 'ivan.m.sredojevic@gmail.com', 'admin', '$10$t8s15mWHi5wBxl6jQ7rd3O86SrpDWKCECZGBEDixeKX9vmfsOku0e', 'admin pass user', 'adminovic', 'bul c laz 22', '0640276707', '1012993773648', 'm', 'medical center admin', 'admin at medical centers', true, 'ROLE_ADMIN', '99a3a2fb-cc06-46e3-9086-2c76aa323804', 0);
-- user

insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty) values ('application_user', 'ivan.sredojevic@devoteam.com', 'user', '$10$t8s15mWHi5wBxl6jQ7rd3O86SrpDWKCECZGBEDixeKX9vmfsOku0e', 'Ivan', 'Sredojevic', 'bul c laz 21', '0640276707', '1012993773648', 'm', 'it consultant', 'senior consultant at dt srb', true, 'ROLE_USER', '99a3a2fb-cc06-46e3-9086-2c76aa323801', 0);
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty) values ('application_user', 'ivansoko93@gmail.com', 'user12', '$10$t8s15mWHi5wBxl6jQ7rd3O86SrpDWKCECZGBEDixeKX9vmfsOku0e', 'user12 pass user', 'userovic', 'bul c laz 22', '0640276707', '1012993773648', 'm', 'it consultant', 'junior consultant at dt srb', true, 'ROLE_USER', '99a3a2fb-cc06-46e3-9086-2c76aa323802', 2);
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty) values ('application_user', 'sokolicc.dji.acc@gmail.com', 'user11', '$10$t8s15mWHi5wBxl6jQ7rd3O86SrpDWKCECZGBEDixeKX9vmfsOku0e', 'user11 pass user', 'userovic', 'bul c laz 22', '0640276707', '1012993773648', 'm', 'it consultant', 'junior consultant at dt srb', true, 'ROLE_USER', '99a3a2fb-cc06-46e3-9086-2c76aa323803', 3);

-- questionnaire
insert into questionnaire (application_user_id, date, donation_number, rejected, healthy, dangerous_occupation, infectious, blood_pressure_issues, on_therapy, aspirin, tatooed) values (2, '2023-05-30', 1, false, true, false, false, false, false, false, false);
insert into questionnaire (application_user_id, date, donation_number, rejected, healthy, dangerous_occupation, infectious, blood_pressure_issues, on_therapy, aspirin, tatooed) values (3, '2023-05-30', 3, false, true, false, false, false, false, false, false);
--insert into questionnaire (application_user_id, date, donation_number, rejected, healthy, dangerous_occupation, infectious, blood_pressure_issues, on_therapy, aspirin, tatooed) values (4, '2023-05-30', 7, false, true, true, false, false, false, false, false);
 

-- personnel
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, center_id) values ('personnel', 'personnel_1@gmail.com', 'personnel_1', '$10$t8s15mWHi5wBxl6jQ7rd3O86SrpDWKCECZGBEDixeKX9vmfsOku0e', 'personnel_1 pass user', 'Personnelovic', 'serbia', '0640276707', '1012993773648', 'f', 'medical technician', 'medical technician at kcv', true, 'ROLE_PERSONNEL', '99a3a2fb-cc06-46e3-9086-2c76aa323805', 0, 1);
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, center_id) values ('personnel', 'personnel_2@gmail.com', 'personnel_2', '$10$t8s15mWHi5wBxl6jQ7rd3O86SrpDWKCECZGBEDixeKX9vmfsOku0e', 'personnel_2 pass user', 'Personnelovic', 'serbia', '0640276707', '1012993773648', 'f', 'medical technician', 'medical technician at kamenica', true, 'ROLE_PERSONNEL', '99a3a2fb-cc06-46e3-9086-2c76aa323806', 0, 2);


-- free appointments
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (1, null, '2023-06-06 09:00:00', '2023-06-06 09:00:00', 45, 60, false, false);
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (1, null, '2023-06-06 10:00:00', '2023-06-06 10:00:00', 55, 40, false, false);
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (2, null, '2023-06-06 12:00:00', '2023-06-06 12:00:00', 45, 38, false, false);
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (2, null, '2023-06-06 11:00:00', '2023-06-06 11:00:00', 45, 42, false, false);
-- reserved appointments
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (1, 2, '2023-06-08 11:00:00', '2023-06-08 11:00:00', 45, 49, true, true);
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (1, 3, '2023-06-08 12:00:00', '2023-06-08 12:00:00', 45, 16, true, true);
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (2, 3, '2023-06-08 11:00:00', '2023-06-08 11:00:00', 45, 82, true, true);
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (2, 2, '2023-06-08 12:00:00', '2023-06-08 12:00:00', 45, 53, true, true);
insert into appointment (center_id, application_user_id, modified_time, start_time, duration, price_euro, taken, approved) values (2, 4, '2023-06-08 16:00:00', '2023-06-08 16:00:00', 45, 19, true, true);


--personnel on appointments
insert into appointment_personnel (personnel_id, appointment_id) values (5, 5);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 6);

insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) values ('Bad attitude', null, null, 5, 2, 5, null);
insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) values ('Bad office', null, null, 6, 3, null, 1);
insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) values ('Bad attitude of medical staff', 'You are rude', 1, 9, 4, null, 2);




-- user 1 - admin
-- user 2 - user
-- user 3 - user
-- user 4 - user
-- user 5 - personnel
-- user 6 - personnel

