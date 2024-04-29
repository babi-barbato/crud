package org.example.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exemplojwt.domain.usuario.Login;
import school.sptech.exemplojwt.domain.usuario.Usuario;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByEmail(String email);
}
