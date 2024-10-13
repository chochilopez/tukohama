package com.gloit.tukohama.controller.form;

import com.gloit.tukohama.entity.Role;
import com.gloit.tukohama.entity.enumerator.EnumRole;

import javax.validation.constraints.Size;

public class RoleForm {

    @Size(min = 3, max = 50, message = "El rol debe tener entre 3 y 50 caracteres")
    private EnumRole role;

    public Role formRoleDTO(Role dto){

        if (this.role!=null)
            dto.setRole(this.role);

        return dto;
    }

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }
}
