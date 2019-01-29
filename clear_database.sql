delete from marks where id>0;
delete from exam where id>0;
delete from student where id>0;
alter table marks auto_increment=1;
alter table exam auto_increment=1;
alter table student auto_increment=1;