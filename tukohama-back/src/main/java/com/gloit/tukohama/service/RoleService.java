package com.gloit.tukohama.service;

import com.gloit.tukohama.entity.Role;
import com.gloit.tukohama.entity.enumerator.EnumRole;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    public Optional<Role> findById(Long id);
    public List<Role> findAll();

    public Optional<Role> findByIdWithDeleted(Long id);
    public List<Role> findAllWithDeleted();

    public Role insert(Role obj) throws Exception;
    public Role update(Role obj);
    public void delete(Long id);
    public Role recycle(Role obj);
    public void destroy(Long id);
}
