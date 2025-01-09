package com.bridge.admin.service.auth;

import com.bridge.admin.repository.rdb.auth.Password;
import lombok.*;
import org.bridge.base.config.DateUtil;
import org.bridge.base.service.CrudDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PasswordDto extends CrudDto {

    private Long id;
    private String password;
    private String lastChangedTime;
    private String expTime;

    public PasswordDto(Password entity) {
        super(entity);
        this.id = entity.getId();
        this.password = entity.getPassword();
        this.lastChangedTime = DateUtil.toFormat_yyyyMMddHHmmss(entity.getLastChangedTime());
        this.expTime = DateUtil.toFormat_yyyyMMddHHmmss(entity.getExpTime());
    }

    @Override
    public Password toEntity() {
        return Password.builder()
                .id(id)
                .password(password)
                .createdWho(super.getCreatedWho())
                .build();
    }
}
