package com.example.AprendendoSpring.infrastructure.repository;

import com.example.AprendendoSpring.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRespository extends JpaRepository<Telefone, Long> {
}
