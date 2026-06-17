package com.example.AprendendoSpring.controller;

import com.example.AprendendoSpring.buisness.UsuarioService;
import com.example.AprendendoSpring.controller.dtos.UsuarioDTO;
import com.example.AprendendoSpring.infrastructure.entity.Usuario;
import com.example.AprendendoSpring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//A anotação abaixo indica que está é a classe controladora
@RestController
//A anotação abaixo é responsavel por apontar a URI dessa controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping()
    //A classe ResponseEntity é utilizada para retornarmos
    //de forma correta as nossas respostas HTTP
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping()
    public ResponseEntity buscaUsuarioPorEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUsuarioPorEmial(@PathVariable String email){
        usuarioService.deleteUsuarioPorEmial(email);;
        return ResponseEntity.ok().build();
    }
}
