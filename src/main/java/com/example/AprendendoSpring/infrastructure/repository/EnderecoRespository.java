package com.example.AprendendoSpring.infrastructure.repository;

import com.example.AprendendoSpring.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRespository extends JpaRepository<Endereco, Long> {
}
