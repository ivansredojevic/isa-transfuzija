
    create table application_user (
       dtype varchar(31) not null,
        id  serial not null,
        activated boolean not null,
        address varchar(255),
        email varchar(255),
        jmbg varchar(255),
        jobinformation varchar(255),
        last_donation_date date,
        name varchar(255),
        occupation varchar(255),
        password varchar(255),
        penalty int4,
        phone varchar(255),
        role varchar(255),
        sex varchar(255),
        surname varchar(255),
        token varchar(255),
        username varchar(255),
        center_id int4,
        primary key (id)
    );

    create table appointment (
       id  serial not null,
        approved boolean,
        complain_center boolean,
        complain_pers boolean,
        duration int4,
        end_time timestamp,
        price_euro int4,
        start_time timestamp,
        taken boolean,
        application_user_id int4,
        center_id int4,
        primary key (id)
    );

    create table appointment_personnel (
       id  serial not null,
        complaint boolean,
        appointment_id int4,
        personnel_id int4,
        primary key (id)
    );

    create table center (
       id  serial not null,
        address varchar(255) not null,
        center_name varchar(255) not null,
        closed_time time,
        open_time time,
        rating float4,
        primary key (id)
    );

    create table complaint (
       id  serial not null,
        complaint_text varchar(255),
        reply_text varchar(255),
        admin_id int4,
        application_user_id int4,
        appointment_id int4,
        center_id int4,
        personnel_user_id int4,
        primary key (id)
    );

    create table questionnaire (
       id  serial not null,
        aspirin boolean not null,
        blood_pressure_issues boolean not null,
        dangerous_occupation boolean not null,
        date date not null,
        healthy boolean not null,
        infectious boolean not null,
        on_therapy boolean not null,
        rejected boolean not null,
        tatooed boolean not null,
        application_user_id int4,
        primary key (id)
    );

    alter table application_user 
       add constraint UK_cb61p28hanadv7k0nx1ec0n5l unique (email);

    alter table application_user 
       add constraint UK_6c0v0rco93sykgyetukfmkkod unique (username);

    alter table application_user 
       add constraint FK5jpqqchoqamrvp2qdjmhkqni5 
       foreign key (center_id) 
       references center;

    alter table appointment 
       add constraint FK812acci29kncu8pjhcw1a41cj 
       foreign key (application_user_id) 
       references application_user;

    alter table appointment 
       add constraint FK4r8xy6t1045a4a7s3jfesr0nw 
       foreign key (center_id) 
       references center;

    alter table appointment_personnel 
       add constraint FK4cwosnecpk45fm9s1cqdkxjmm 
       foreign key (appointment_id) 
       references appointment;

    alter table appointment_personnel 
       add constraint FKi4iq3omoc8vt8a32cwe0hpqlj 
       foreign key (personnel_id) 
       references application_user;

    alter table complaint 
       add constraint FKptp7myb6pg2oq2agu9rbswmpx 
       foreign key (admin_id) 
       references application_user;

    alter table complaint 
       add constraint FKqeywmmdpfpe01ljbpygcdr0o0 
       foreign key (application_user_id) 
       references application_user;

    alter table complaint 
       add constraint FKqtxgrqkjx08fmv45mt3tmqe86 
       foreign key (appointment_id) 
       references appointment;

    alter table complaint 
       add constraint FK3afmsx2lqykmapxv0q1s8ib2t 
       foreign key (center_id) 
       references center;

    alter table complaint 
       add constraint FKm21y1i7fm8911rc87t4wa950u 
       foreign key (personnel_user_id) 
       references application_user;

    alter table questionnaire 
       add constraint FKhwon7fh8d76hacxn80pmcjklh 
       foreign key (application_user_id) 
       references application_user;

    create table application_user (
       dtype varchar(31) not null,
        id  serial not null,
        activated boolean not null,
        address varchar(255),
        email varchar(255),
        jmbg varchar(255),
        jobinformation varchar(255),
        last_donation_date date,
        name varchar(255),
        occupation varchar(255),
        password varchar(255),
        penalty int4,
        phone varchar(255),
        role varchar(255),
        sex varchar(255),
        surname varchar(255),
        token varchar(255),
        username varchar(255),
        center_id int4,
        primary key (id)
    );

    create table appointment (
       id  serial not null,
        approved boolean,
        complain_center boolean,
        complain_pers boolean,
        duration int4,
        end_time timestamp,
        price_euro int4,
        start_time timestamp,
        taken boolean,
        application_user_id int4,
        center_id int4,
        primary key (id)
    );

    create table appointment_personnel (
       personnel_id int4 not null,
        appointment_id int4 not null,
        primary key (appointment_id, personnel_id)
    );

    create table center (
       id  serial not null,
        address varchar(255) not null,
        center_name varchar(255) not null,
        closed_time time,
        open_time time,
        rating float4,
        primary key (id)
    );

    create table complaint (
       id  serial not null,
        complaint_text varchar(255),
        reply_text varchar(255),
        admin_id int4,
        application_user_id int4,
        appointment_id int4,
        center_id int4,
        personnel_user_id int4,
        primary key (id)
    );

    create table questionnaire (
       id  serial not null,
        aspirin boolean not null,
        blood_pressure_issues boolean not null,
        dangerous_occupation boolean not null,
        date date not null,
        healthy boolean not null,
        infectious boolean not null,
        on_therapy boolean not null,
        rejected boolean not null,
        tatooed boolean not null,
        application_user_id int4,
        primary key (id)
    );

    alter table application_user 
       add constraint UK_cb61p28hanadv7k0nx1ec0n5l unique (email);

    alter table application_user 
       add constraint UK_6c0v0rco93sykgyetukfmkkod unique (username);

    alter table application_user 
       add constraint FK5jpqqchoqamrvp2qdjmhkqni5 
       foreign key (center_id) 
       references center;

    alter table appointment 
       add constraint FK812acci29kncu8pjhcw1a41cj 
       foreign key (application_user_id) 
       references application_user;

    alter table appointment 
       add constraint FK4r8xy6t1045a4a7s3jfesr0nw 
       foreign key (center_id) 
       references center;

    alter table appointment_personnel 
       add constraint FK4cwosnecpk45fm9s1cqdkxjmm 
       foreign key (appointment_id) 
       references appointment;

    alter table appointment_personnel 
       add constraint FKi4iq3omoc8vt8a32cwe0hpqlj 
       foreign key (personnel_id) 
       references application_user;

    alter table complaint 
       add constraint FKptp7myb6pg2oq2agu9rbswmpx 
       foreign key (admin_id) 
       references application_user;

    alter table complaint 
       add constraint FKqeywmmdpfpe01ljbpygcdr0o0 
       foreign key (application_user_id) 
       references application_user;

    alter table complaint 
       add constraint FKqtxgrqkjx08fmv45mt3tmqe86 
       foreign key (appointment_id) 
       references appointment;

    alter table complaint 
       add constraint FK3afmsx2lqykmapxv0q1s8ib2t 
       foreign key (center_id) 
       references center;

    alter table complaint 
       add constraint FKm21y1i7fm8911rc87t4wa950u 
       foreign key (personnel_user_id) 
       references application_user;

    alter table questionnaire 
       add constraint FKhwon7fh8d76hacxn80pmcjklh 
       foreign key (application_user_id) 
       references application_user;
