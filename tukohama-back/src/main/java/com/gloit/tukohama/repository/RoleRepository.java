package com.gloit.tukohama.repository;

import com.gloit.tukohama.entity.Role;
import com.gloit.tukohama.entity.enumerator.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select obj from Role as obj where obj.borrado is null and obj.id=:id")
    public Optional<Role> findById(@Param("id") Long id);
    @Query("select obj from Role as obj where obj.borrado is null ")
    public List<Role> findAll();


    @Query("select obj from Role as obj where obj.id=:id")
    public Optional<Role> findByIdWithDeleted(@Param("id") Long id);
    @Query("select obj from Role as obj")
    public List<Role> findAllWithDeleted();


    @Query("select obj from Role obj where obj.role=:role")
    public Optional<Role> buscarPorRole(@Param("role") EnumRole role);
}
