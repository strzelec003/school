create schema if not exists school;

set global time_zone = '+1:00';

create table if not exists student(
    id int auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    primary key (id)
) engine=InnoDB;

create table if not exists exam(
    id int auto_increment,
    subject_name varchar(50) not null,
    subject_description varchar(50) not null,
    primary key (id)
) engine=InnoDB;

create table if not exists marks(
    id int auto_increment,
    grade int not null,
    student_fk int not null,
    exam_fk int not null,
    primary key (id),
    foreign key (student_fk)
		references student(id)
        on update cascade on delete restrict,
	foreign key (exam_fk)
		references exam(id)
        on update cascade on delete restrict,
	constraint check_grade CHECK (grade BETWEEN 2 and 5)
) engine=InnoDB;


