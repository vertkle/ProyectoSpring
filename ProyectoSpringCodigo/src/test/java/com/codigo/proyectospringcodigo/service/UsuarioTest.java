package com.codigo.proyectospringcodigo.service;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Usuario;
import com.codigo.proyectospringcodigo.repository.UsuarioRepository;
import com.codigo.proyectospringcodigo.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class UsuarioTest {
    @Mock
    UsuarioRepository usuarioRepository;
    @InjectMocks
    UsuarioService usuarioService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindAllUsuario() {
        Mockito.when(usuarioRepository.findAll()).thenReturn(new ArrayList<Usuario>());
        List<Usuario> usuarios = usuarioService.findAllUsuario();
        assertNotNull(usuarios);
        assertTrue(usuarios.isEmpty());
    }
    @Test
    public void testFindByIdUsuario() {
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setUsuario_id(usuarioId);
        Mockito.when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        try {
            Usuario foundUsuario = usuarioService.findByIdUsuario(usuarioId);
            assertNotNull(foundUsuario);
            Assertions.assertEquals(usuarioId,foundUsuario.getUsuario_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }
    @Test
    public void testFindByEmailUsuario() {
        // Configura el mock para findByEmail
        String email = "test@example.com";
        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(new Usuario()));

        try {
            boolean result = usuarioService.findByEmailUsuario(email);
            assertFalse(result); // Debe arrojar una excepción, por lo que result debe ser falso.
        } catch (ErrorResponseException e) {
            // Manejar la excepción esperada
        }
    }
    @Test
    public void testUpdateUsuario() {
        // Configura el mock para findById
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setUsuario_id(usuarioId);
        Mockito.when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        Map<String, String> usuarioData = new HashMap<>();
        usuarioData.put("nombre_usuario", "Updated User");
        usuarioData.put("estado", "1");
        usuarioData.put("usuario_id", "1");

        try {
            Usuario updatedUsuario = usuarioService.updateUsuario(usuarioData);
            assertNotNull(updatedUsuario);
            Assertions.assertEquals("Updated User", updatedUsuario.getNombre_usuario());
            Assertions.assertEquals(1, updatedUsuario.getEstado());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testEliminarUsuario() {
        // Configura el mock para findById
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setUsuario_id(usuarioId);
        Mockito.when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        Map<String, String> usuarioData = new HashMap<>();
        usuarioData.put("usuario_id", "1");

        try {
            Usuario deletedUsuario = usuarioService.eliminarUsuario(usuarioData);
            assertNotNull(deletedUsuario);
            Assertions.assertEquals(usuarioId, deletedUsuario.getUsuario_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

}
