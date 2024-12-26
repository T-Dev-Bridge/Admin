package com.bridge.admin.service.auth;

import com.bridge.admin.repository.rdb.auth.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bridge.base.service.CrudDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ManagerDto extends CrudDto {

    private String id;
    private String username;
    private String password;
    private String email;
    private Long pwdId;
    private Long roleId;
    private boolean enabled;


    public ManagerDto(Manager entity) {
        super(entity);
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.pwdId = entity.getPwdId();
        this.password = entity.getPassword().getPassword();
        this.roleId = entity.getRoleId();
        this.enabled = entity.getEnabled() == 1;
    }


    @Override
    public Manager toEntity() {
        return Manager.builder()
                .id(id)
                .username(username)
                .email(email)
                .pwdId(pwdId)
                .roleId(roleId)
                .enabled(enabled ? 1 : 0)
                .build();
    }
}
