create table cars (
                      id bigint auto_increment primary key,
                      license_plate_number varchar(20) not null comment '자동차 번호',
                      created_at timestamp(3) not null default current_timestamp,
                      updated_at timestamp(3) not null default current_timestamp on update current_timestamp,
                      unique key udx_cars_license_plate_number (license_plate_number)
) comment = '자동차 마스터 데이터'
;
