package com.bridge.admin.service.auth;

import com.bridge.admin.repository.rdb.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bridge.base.service.CrudDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) // equal 수행 시 상위 클래스의 필드도 비교
public class RoleDto extends CrudDto {

    private Long id;
    private String name;
    private String rmk;

    public RoleDto(Role entity) {
        super(entity);
        this.id = entity.getId();
        this.name = entity.getName();
        this.rmk = entity.getRmk();
    }

    @Override
    public Role toEntity() {
        return Role.builder()
                .id(id)
                .name(name)
                .rmk(rmk)
                .build();
    }
}
