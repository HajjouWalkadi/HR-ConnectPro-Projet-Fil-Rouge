package com.example.hrconnectpro.dto.request;


import com.example.hrconnectpro.entities.Role;

import java.util.List;

public record RoleRequestDTO(
        String name,
        boolean isDefault
){
    public Role toRole(){
        return Role.builder()
                .name(name)
                .isDefault(isDefault)
                .build();
    }
}