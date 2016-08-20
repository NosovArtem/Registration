package com.registration.repository;

import com.registration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    Optional<User> findOneById(Long userId);

    @Query("SELECT u FROM User AS u JOIN u.authorities AS a WHERE a.name = 'ROLE_DOCTOR'")
    List<User> findUsersLoginDoctor();

    @Query("SELECT u FROM User AS u JOIN u.authorities AS a WHERE a.name = 'ROLE_DOCTOR' AND u.id = ?1")
    User findOneUserDoctorById(Long doctorId);

    @Override
    void delete(User t);

}
