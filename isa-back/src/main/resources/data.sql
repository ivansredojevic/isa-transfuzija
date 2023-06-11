-- ISA 2022/2023 data.sql

-- centers
insert into center (center_name, address, rating, open_time, closed_time) values ('KCV', '45.253248, 19.822806', '4.0', '07:00', '23:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Kamenica', '45.226622, 19.874706', '4.2', '05:00', '22:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Urgentni', '45.250061, 19.820405', '3.5', '01:00', '23:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('KCV 2', '45.253248, 19.822806', '4.8', '07:00', '23:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Kamenica 2', '45.226622, 19.874706', '3.2', '05:00', '22:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Urgentni 2', '45.250061, 19.820405', '3.5', '01:00', '23:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Centar 6', '45.253248, 19.822806', '4.6', '07:00', '23:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Centar 7', '45.226622, 19.874706', '2.2', '05:00', '22:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Centar 8', '45.250061, 19.820405', '3.1', '01:00', '23:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('KCV 3', '45.253248, 19.822806', '4.0', '07:00', '23:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Kamenica 3', '45.226622, 19.874706', '9.2', '05:00', '22:00');
insert into center (center_name, address, rating, open_time, closed_time) values ('Urgentni 3', '45.250061, 19.820405', '1.5', '01:00', '23:00');

-- user
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, last_donation_date) 
values ('application_user', 'ivan.sredojevic.c@devoteam.com', 'user', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'Ivan', 'Sredojevic', 'Bulevar Cara Lazara 21', '0640123456', '1010999887766', 'm', 'Engineer', 'senior consultant at dt srb', true, 'USER', '99a3a2fb-cc06-46e3-9086-2c76aa323801', 0, '2022-05-30');
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, last_donation_date) 
values ('application_user', 'marko.markovic.19843e29876@gmail.com', 'user12', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'Marko', 'Markovic', 'Bulevar Cara Dusana 22', '0640123456', '1010999887766', 'm', 'Moler', 'Moler', true, 'USER', '99a3a2fb-cc06-46e3-9086-2c76aa323802', 2, '2023-05-30');
/* insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty) values ('application_user', 'sokolicc.dji.acc@gmail.com', 'user11', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'user1234 pass user123', 'userovic', 'bul c laz 22', '0640276707', '1010999887766', 'm', 'it consultant', 'junior consultant at dt srb', true, 'USER', '99a3a2fb-cc06-46e3-9086-2c76aa323803', 3); */

-- questionnaire
insert into questionnaire (application_user_id, date, rejected, healthy, dangerous_occupation, infectious, blood_pressure_issues, on_therapy, aspirin, tatooed) values (1, '2023-05-30', false, true, false, false, false, false, false, false);
insert into questionnaire (application_user_id, date, rejected, healthy, dangerous_occupation, infectious, blood_pressure_issues, on_therapy, aspirin, tatooed) values (2, '2023-05-30', false, true, false, false, false, false, false, false);
--insert into questionnaire (application_user_id, date, donation_number, rejected, healthy, dangerous_occupation, infectious, blood_pressure_issues, on_therapy, aspirin, tatooed) values (4, '2023-05-30', 7, false, true, true, false, false, false, false, false);

-- personnel
-- KCV
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, center_id) 
values ('personnel', 'personnel1_1@gmail.com', 'zivanaPersKCV', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'Zivana', 'Personnelovic', 'serbia', '0640123456', '1010999887766', 'f', 'doctor', 'cardiologist at KCV', true, 'PERSONNEL', '99a3a2fb-cc06-46e3-9086-2c76aa323805', 0, 1);

insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, center_id) 
values ('personnel', 'personnel1_2@gmail.com', 'zarkoPersKCV', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'Zarko', 'Doktor', 'serbia', '0640123456', '1010999887766', 'm', 'medical technician', 'medical technician at KCV', true, 'PERSONNEL', '99a3a2fb-cc06-46e3-9086-2c76aa323806', 0, 1);

-- Kamenica
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, center_id) 
values ('personnel', 'personnel2_1@gmail.com', 'marijaPersKam', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'Marija', 'Personnelovic', 'serbia', '0640123456', '1010999887766', 'f', 'doctor', 'cardiologist at Kamenica', true, 'PERSONNEL', '99a3a2fb-cc06-46e3-9086-2c76aa323805', 0, 2);

insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, center_id) 
values ('personnel', 'personnel2_2@gmail.com', 'markoPersKam', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'Marko', 'Doktorovic', 'serbia', '0640123456', '1010999887766', 'm', 'medical technician', 'medical technician at Kamenica', true, 'PERSONNEL', '99a3a2fb-cc06-46e3-9086-2c76aa323806', 0, 2);
-- admin
insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty) 
values ('application_user', 'ivan.m.sredojevicc@gmail.com', 'admin', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'admin pass user', 'adminovic', 'bul c laz 22', '0640123456', '1010999887766', 'm', 'medical center admin', 'admin at medical centers', true, 'ADMIN', '99a3a2fb-cc06-46e3-9086-2c76aa323804', 0);

--insert into application_user (dtype, email, username, password, name, surname, address, phone, jmbg, sex, occupation, jobinformation, activated, role, token, penalty, last_donation_date) 
--values ('application_user', 'ivansoko932@gmail.com', 'user123', '$2a$10$3TnwcRM8Es3lm3jQUClvK.wugatrVpe209UJ5xaKbec3EMAoaClve', 'Petar', 'Petrovic', 'Trg Dositeja Obradovica 6', '0640123456', '1010999887766', 'm', 'it consultant', 'senior consultant at dt srb', true, 'USER', '99a3a2fb-cc06-46e3-9086-2c76aa323803', 2, '2022-05-30');


-- free appointments
-- KCV.id=1 preklapajuci, 12 jun
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, null, '2023-06-12 09:51:00', '2023-06-12 09:16:00', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 1);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 1);

insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, null, '2023-06-12 10:45:00', '2023-06-12 10:00:00', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 2);

insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, null, '2023-06-12 11:30:01', '2023-06-12 10:45:01', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 3);

insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, null, '2023-06-12 12:45:00', '2023-06-12 11:30:00', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 4);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 4);

-- prosao, proveriti da li se pojavljuje u slobodnim terminima
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, null, '2023-05-11 12:15:00', '2023-05-11 11:30:00', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 5);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 5);

-- kam.id=2 preklapajuci, 12 jun

insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (2, null, '2023-06-12 10:01:00', '2023-06-12 09:16:00', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (5, 6);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 6);

insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (2, null, '2023-06-12 10:45:00', '2023-06-12 10:00:00', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (5, 7);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 7);

insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (2, null, '2023-06-12 11:30:01', '2023-06-12 10:45:01', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (5, 8);


insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (2, null, '2023-06-12 12:45:00', '2023-06-12 11:30:00', 45, 60, false, false, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 9);
insert into appointment_personnel (personnel_id, appointment_id) values (5, 9);

--kcv 12 user, id=1
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 1, '2023-06-12 09:15:00', '2023-06-12 08:30:00', 45, 60, false, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 10);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 10);
--kcv 11
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 1, '2023-06-11 08:01:00', '2023-06-11 07:16:00', 45, 60, false, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 11);
-- kam 12
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (2, 1, '2023-06-12 09:15:00', '2023-06-12 08:30:00', 45, 60, false, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 12);
insert into appointment_personnel (personnel_id, appointment_id) values (5, 12);


--kcv 12 user12, id=2
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 2, '2023-06-12 09:15:00', '2023-06-12 08:30:00', 45, 60, false, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 13);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 13);

--kcv 11
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 2, '2023-06-11 08:01:00', '2023-06-11 07:16:00', 45, 60, false, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 14);
-- kam 12
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (2, 2, '2023-06-12 09:15:00', '2023-06-12 08:30:00', 45, 60, false, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 15);





-- history
--kcv 12 user, id=1
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 1, '2023-06-09 09:15:00', '2023-06-09 08:30:00', 45, 60, true, true, true, true);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 16);
-- user complaints on app 16
insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) 
values ('Bad attitude', null, null, 16, 1, 4, null);
insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) 
values ('Bad office', null, null, 16, 1, null, 1);

--kcv 11
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 1, '2023-06-01 08:01:00', '2023-06-01 07:16:00', 45, 60, true, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 17);
-- kam 12
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (2, 1, '2023-06-02 09:15:00', '2023-06-02 08:30:00', 45, 60, true, true, false, true);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 18);
insert into appointment_personnel (personnel_id, appointment_id) values (5, 18);
-- user complaints on app 18
insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) 
values ('No elevator, had to climb for 10 minutes', 'You are spoiled', 7, 18, 1, null, 2);




-- history
--kcv 12 user12, id=2
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 2, '2023-06-10 09:15:00', '2023-06-10 08:30:00', 45, 60, true, true, true, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 19);
insert into appointment_personnel (personnel_id, appointment_id) values (4, 19);
-- user 12 complaints on app 19
insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) 
values ('Bad attitude', null, null, 19, 2, 5, null);
--kcv 11
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 2, '2023-05-19 08:01:00', '2023-05-19 07:16:00', 45, 60, true, true, false, true);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 20);
-- user 12 complaints on app 20
insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) 
values ('Bad office', null, null, 20, 2, null, 1);
-- kam 12
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (2, 2, '2023-06-03 09:15:00', '2023-06-03 08:30:00', 45, 60, true, true, false, true);
insert into appointment_personnel (personnel_id, appointment_id) values (5, 21);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 21);
-- user 12 complaints on app 21
insert into complaint (complaint_text, reply_text, admin_id, appointment_id, application_user_id, personnel_user_id, center_id) 
values ('Bad office, no equipment', 'You were not prepared for appointment', 7, 21, 2, null, 2);


-- history not taken

-- not taken user
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 1, '2023-06-09 19:15:00', '2023-06-09 18:30:00', 45, 60, false, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (3, 22);
--

-- not taken user12
insert into appointment (center_id, application_user_id, end_time, start_time, duration, price_euro, taken, approved, complain_pers, complain_center)
values (1, 2, '2023-06-10 09:15:00', '2023-06-10 08:30:00', 45, 60, false, true, false, false);
insert into appointment_personnel (personnel_id, appointment_id) values (6, 23);
--









-- user 1 - admin
-- user 2 - user
-- user 3 - user
-- user 4 - user
-- user 5 - personnel
-- user 6 - personnel

