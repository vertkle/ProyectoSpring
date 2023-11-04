package com.codigo.proyectospringcodigo.controller;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Empresa;
import com.codigo.proyectospringcodigo.model.Usuario;
import com.codigo.proyectospringcodigo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.codigo.proyectospringcodigo.Utils.Constantes.ALGO_SALIO_MAL;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> listaUsuarios = usuarioService.findAllUsuario();
        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return usuarioService.login(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(ALGO_SALIO_MAL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/registrar_usuario")
    public ResponseEntity<Usuario>createUsuarioController(@RequestBody Map<String,String> request) throws ErrorResponseException {
        Usuario usu = usuarioService.createUsuario(request);
        return new ResponseEntity<>(usu, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Usuario>updateUsuarioController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        Usuario usu = usuarioService.updateUsuario(request);
        return new ResponseEntity<>(usu, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Usuario>deleteUsuarioController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        Usuario usu = usuarioService.eliminarUsuario(request);
        return new ResponseEntity<>(usu, HttpStatus.OK);
    }

}
