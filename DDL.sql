create table admin.article(
    article_id BIGSERIAL primary key ,
    article_title varchar(255),
    article_content TEXT,
    article_active int,
    created_who varchar(255),
    created_at TIMESTAMP,
    updated_who varchar(255),
    updated_at TIMESTAMP
);

create table admin.role(
   role_id BIGSERIAL primary key ,
   role_name varchar(255) unique,
   rmk varchar(2048),
   created_who varchar(255),
   created_at TIMESTAMP,
   updated_who varchar(255),
   updated_at TIMESTAMP
);

create table admin.password(
    password_id BIGSERIAL primary key ,
    password varchar(2048),
    last_changed_time TIMESTAMP,
    password_expired_time TIMESTAMP,
    created_who varchar(255),
    created_at TIMESTAMP,
    updated_who varchar(255),
    updated_at TIMESTAMP
);

create table admin.manager(
  user_id varchar(255) primary key ,
  username varchar(255) unique ,
  user_email varchar(255) not null,
  password_id bigint not null,
  role_id bigint not null,
  enabled int not null,
  created_who varchar(255),
  created_at TIMESTAMP,
  updated_who varchar(255),
  updated_at TIMESTAMP,
  constraint manager_fk_password FOREIGN KEY  (password_id) REFERENCES  admin.password(password_id),
  constraint manager_fk_role FOREIGN KEY  (role_id) REFERENCES  admin.role(role_id)
);