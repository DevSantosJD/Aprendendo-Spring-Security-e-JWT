package com.example.AprendendoSpring.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "senha")
    private String senha;

    // Um usuario para muitos endereços
    // Muitos endereços podem pertencer a um usuario
    @OneToMany(cascade = CascadeType.ALL)

    // name = nome da nossa coluna na tabela de endereços
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "telefone_id", referencedColumnName = "id")
    private List<Telefone> telefones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    //Normalmente veio com retorno string, mas alterei para variavel senha
    // Para locarmos com senha e email
    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    //Normalmente veio com retorno string, mas alterei para variavel email
    @Override
    public String getUsername() {
        return email;
    }

}
