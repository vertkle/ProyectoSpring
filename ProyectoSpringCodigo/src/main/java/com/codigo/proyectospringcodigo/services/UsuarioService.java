package com.codigo.proyectospringcodigo.services;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.Utils.security.CustomerDetailsService;
import com.codigo.proyectospringcodigo.Utils.security.SecurityConfig;
import com.codigo.proyectospringcodigo.Utils.security.jwt.JwtUtil;
import com.codigo.proyectospringcodigo.model.Empresa;
import com.codigo.proyectospringcodigo.model.Usuario;
import com.codigo.proyectospringcodigo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import static com.codigo.proyectospringcodigo.Utils.Constantes.*;
import static com.codigo.proyectospringcodigo.Utils.Utilitarios.*;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CustomerDetailsService customerDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityConfig securityConfig;

    public List<Usuario>findAllUsuario(){
        return usuarioRepository.findAll();
    }

    public Usuario findByIdUsuario(Integer usuario_id) throws ErrorResponseException {
        Optional<Usuario> usuarioById = usuarioRepository.findById(usuario_id);
        if(usuarioById.isPresent()){
            return usuarioById.get();
        }
        throw new ErrorResponseException(EX_NOT_FOUND_RECURSO, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }

    public Boolean findByEmailUsuario(String email_usuario) throws ErrorResponseException {
        Optional<Usuario> usuarioByEmail = usuarioRepository.findByEmail(email_usuario);
        if(usuarioByEmail.isPresent()){
            throw new ErrorResponseException(USUARIO_ENCONTRADO, HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE);
        }
        return true;

    }
    public Usuario createUsuario(Map<String, String> usuario) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre_usuario", "email", "password", "empresa_id"};
        IsValidateParametros(usuario, parametrosRequeridos);
        IsNotEmptyValores(usuario, parametrosRequeridos);
        IsValidateId(usuario, "empresa_id");

        Usuario usu = new Usuario();
        usu.setNombre_usuario(usuario.get("nombre_usuario"));
        usu.setEmail(usuario.get("email"));
        String password = usuario.get("password");
        String encodePassword = securityConfig.passwordEncoder().encode(password);
        usu.setPassword(encodePassword);
        usu.setCreated_at(new Date());
        usu.setEstado(ESTADO_ACTIVO);
        Empresa emp = new Empresa();
        emp.setEmpresa_id(Integer.parseInt(usuario.get("empresa_id")));
        usu.setEmpresa(emp);
        findByEmailUsuario(usu.getEmail());
        usuarioRepository.save(usu);
        return usu;
    }

    public Usuario updateUsuario(Map<String, String> usuario) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre_usuario", "estado", "usuario_id"};
        IsValidateParametros(usuario, parametrosRequeridos);
        IsNotEmptyValores(usuario, parametrosRequeridos);
        IsValidateId(usuario, "usuario_id");

        Usuario usuarioBuscado = findByIdUsuario(Integer.parseInt(usuario.get("usuario_id")));
        usuarioBuscado.setNombre_usuario(usuario.get("nombre_usuario"));
        usuarioBuscado.setEstado(Integer.parseInt(usuario.get("estado")));
        usuarioRepository.save(usuarioBuscado);
        return usuarioBuscado;
    }

    public Usuario eliminarUsuario(Map<String, String> usuario) throws ErrorResponseException {
        String parametrosRequeridos [] = {"usuario_id"};
        IsValidateParametros(usuario, parametrosRequeridos);
        IsNotEmptyValores(usuario, parametrosRequeridos);
        IsValidateId(usuario, "usuario_id");
        Usuario usuarioBuscado = findByIdUsuario(Integer.parseInt(usuario.get("usuario_id")));
        usuarioRepository.delete(usuarioBuscado);
        return usuarioBuscado;
    }

    public ResponseEntity<String> login(Map<String, String>request){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.get("email"),request.get("password")));

            if(authentication.isAuthenticated()){
                if(customerDetailsService.getUserDetail().getEstado() == 1 ){
                    return new ResponseEntity<String>(
                            "{\"token \":\"" + jwtUtil.generateToken(customerDetailsService.getUserDetail().getEmail(),
                                    "-") +"\"}",
                            HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<String>("{\"mensaje\": " + "Espera la Aprobación del administrador"+"\"}",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("{\"mensaje\": " + "Credenciales Incorrectas"+"\"}",HttpStatus.BAD_REQUEST);
    }
}
