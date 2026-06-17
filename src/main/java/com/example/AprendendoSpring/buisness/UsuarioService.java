package com.example.AprendendoSpring.buisness;

import com.example.AprendendoSpring.infrastructure.entity.Usuario;
import com.example.AprendendoSpring.infrastructure.exceptions.ConflictException;
import com.example.AprendendoSpring.infrastructure.exceptions.ResourceNotFoundException;
import com.example.AprendendoSpring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
/*
@RequiredArgsConstructor - gera construtor que inicializa so os campos que contem
"private final"
*/
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepositoey;
    private final PasswordEncoder passwordEncoder;


    // Responsavel apenas pro salvar usuario
    public Usuario salvarUsuario(Usuario usuario) {
        //Regra de negocio
        try {
            /*
            chamada de metodo responsavel por
            verificar se o email passado existe
            */
            emailExiste(usuario.getEmail());

            /*Chama o metodo setSenha, dentro dele utilizamos a injeção de
               para acessar o meodo encode, que criptografa a senha, e dentro desse metodo,
               utilizamos o getSenha, para pegar a senha passada pelo usuario no corpo do
               objeto Usuario.
            */

            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

            return usuarioRepositoey.save(usuario);
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }

    }

    /*
        Verifica se o e-mail passado pelo usuário existe
        caso exista, trata e lança uma excessão perosnalizada
     */
    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e){
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    /*
    chama metodo criado no repository.
    exclusivo apenas por verificar se um email já existe
    pode ser utilizado/chamado em outro metodo, sendo assim
    não é interessante que o mesmo tenha uma regra de negocio dentro dele
    interessante que apenas retorne o valor, e que o metodo que o chamos
    se responsabilize por realizar a regra de negocio
     */
    public boolean verificaEmailExistente(String email){
        return usuarioRepositoey.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepositoey.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Email não encontrado " + email));
    }

    public void deleteUsuarioPorEmial(String email){
        usuarioRepositoey.deleteByEmail(email);
    }

}
