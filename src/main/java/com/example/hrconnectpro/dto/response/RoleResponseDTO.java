package com.example.hrconnectpro.dto.response;


import com.example.hrconnectpro.entities.Role;



public record RoleResponseDTO(
        String name,

        boolean isDefault
) {
    public static RoleResponseDTO fromRole(Role role){
        return new RoleResponseDTO(
                role.getName(),
                role.isDefault()
        );
    }
}

