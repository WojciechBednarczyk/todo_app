create table project_steps
(
    id  int primary key auto_increment,
    description varchar(100) not null,
    project_id int references projects(id),
    days_to_deadline int
);

