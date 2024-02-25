package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.config.handlers.exception.CustomException;
import com.example.hrconnectpro.entities.Role;
import com.example.hrconnectpro.repository.RoleRepository;
import com.example.hrconnectpro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll(){
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        if (authorities.contains("VIEW_ROLES"))
            return roleRepository.findAll();
        else {
            //Returning empty list to fix bug where user with no authority cannot access the application
            return List.of();
        }
    }

    @Override
    public Role save(Role role, boolean isSeed) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isSeed && authentication != null) {
            List<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            if (!authorities.contains("CREATE_ROLE")) {
                throw new CustomException("Insufficient authorities", HttpStatus.UNAUTHORIZED);
            }
        }

        if (findDefaultRole().isPresent() && role.isDefault()) {
            throw new CustomException("There is already a default role", HttpStatus.UNAUTHORIZED);
        }
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findDefaultRole(){
        return roleRepository.findByIsDefaultTrue();
    }





//    @Override
//    public Role update(Role role, Long id){
//        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
//        if (authorities.contains("UPDATE_ROLE")){
//            Role existingRole = getById(id).orElse(null);
//            if (existingRole != null){
//                existingRole.setName(role.getName());
//                existingRole.setAuthorities(role.getAuthorities());
//                if (role.isDefault() && findDefaultRole().isPresent()) throw new CustomException("There is already a default role", HttpStatus.UNAUTHORIZED);
//                existingRole.setDefault(role.isDefault());
//                return roleRepository.save(existingRole);
//            }
//            return null;
//        }return null;
//    }

    @Override
    public Optional<Role> getById(Long id){
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getByName(String name){
        return roleRepository.findByName(name);
    }

    @Override
    public void delete(Long id){
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("DELETE_ROLE"))getById(id).ifPresent(roleRepository::delete);
    }

}

