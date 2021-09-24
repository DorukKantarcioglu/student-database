create table student (
    id UUID not null,
    name varchar(255) not null,
    primary key(id)
);

create table course (
    code varchar(255) not null,
    title varchar(255) not null,
    primary key(code)
);

create table enrollment (
    id UUID not null,
    code varchar(255) not null,
    primary key(id, code)
);
