package ru.zhurkin.sbercinema.model.enums;

import ru.zhurkin.sbercinema.model.Role;

public enum RoleEnum {

    ADMINISTRATOR(new Role(1L, "Administrator role", "Administrator")),
    MODERATOR(new Role(2L, "Moderator role", "Administrator")),
    FILM_MAKER(new Role(3L, "Film maker role", "Film maker")),
    VIEWER(new Role(4L, "Viewer role", "Viewer"));

    private final Role role;

    RoleEnum(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
