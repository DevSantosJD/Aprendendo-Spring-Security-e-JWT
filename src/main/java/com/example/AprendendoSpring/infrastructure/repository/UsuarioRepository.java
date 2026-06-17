package com.example.AprendendoSpring.infrastructure.repository;

import com.example.AprendendoSpring.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    /*
    "exists" faz parte de uma lista de metodos do JPA e que podemos
    utilizar para criarmos mais metodos.
    */
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
