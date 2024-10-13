package com.gloit.tukohama.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MailConfirmationTokenRepository extends JpaRepository<MailConfirmationToken, Long> {

    @Query("select obj from MailConfirmationToken as obj where obj.confirmationToken=:confirmationToken")
    public MailConfirmationToken findByConfirmationToken(@Param("confirmationToken") String confirmationToken);

    @Query("select obj from MailConfirmationToken as obj where obj.borrado is null ")
    public List<MailConfirmationToken> findAll();

    @Query("select obj from MailConfirmationToken as obj")
    public List<MailConfirmationToken> findAllWithDeleted();

    @Query("select obj from MailConfirmationToken as obj where obj.borrado is null and obj.id=:id")
    public Optional<MailConfirmationToken> findById(@Param("id") Long id);

    @Query("select obj from MailConfirmationToken as obj where obj.id=:id")
    public Optional<MailConfirmationToken> findByIdWithDeleted(@Param("id") Long id);

}

