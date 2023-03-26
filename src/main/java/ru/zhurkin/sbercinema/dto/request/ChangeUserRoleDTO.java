package ru.zhurkin.sbercinema.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeUserRoleDTO {

    private Long userId;
    private Long roleId;
}
