package com.codigo.proyectospringcodigo.Utils.security;

import com.codigo.proyectospringcodigo.model.Usuario;
import com.codigo.proyectospringcodigo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        usuario = usuarioRepository.findByEmail(username).get();
        if(!Objects.isNull(usuario)){
            return new User(usuario.getEmail(), usuario.getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("Usuario no Encontrado");
        }
    }

    public Usuario getUserDetail(){
        return usuario;
    }
}
