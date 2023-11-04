package com.codigo.proyectospringcodigo.service;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Distrito;
import com.codigo.proyectospringcodigo.repository.DistritoRepository;
import com.codigo.proyectospringcodigo.services.DistritoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DistritoTest {
    @InjectMocks
    private DistritoService distritoService;

    @Mock
    private DistritoRepository distritoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllDistrito() {
        // Arrange
        List<Distrito> mockDistritos = new ArrayList<>();
        mockDistritos.add(new Distrito());
        Mockito.when(distritoRepository.findAll()).thenReturn(mockDistritos);

        // Act
        List<Distrito> distritos = distritoService.findAllDistrito();

        // Assert
        assertNotNull(distritos);
        assertEquals(1, distritos.size());
    }

    @Test
    public void testCreateDistrito() {
        // Arrange
        Map<String, String> distritoData = new HashMap<>();
        distritoData.put("nombre", "Distrito de Prueba");
        distritoData.put("ubigeo", "123456");

        // Act
        try {
            Distrito createdDistrito = distritoService.createDistrito(distritoData);

            // Assert
            assertNotNull(createdDistrito);
            assertEquals("Distrito de Prueba", createdDistrito.getNombre());
            assertEquals("123456", createdDistrito.getUbigeo());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testFindByIdDistrito() {
        // Arrange
        int distritoId = 1;
        Distrito mockDistrito = new Distrito();
        mockDistrito.setDistrito_id(distritoId);
        Mockito.when(distritoRepository.findById(distritoId)).thenReturn(Optional.of(mockDistrito));

        // Act
        try {
            Distrito foundDistrito = distritoService.findByIdDistrito(distritoId);

            // Assert
            assertNotNull(foundDistrito);
            assertEquals(distritoId, foundDistrito.getDistrito_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testUpdateDistrito() {
        // Arrange
        int distritoId = 1;
        Map<String, String> distritoData = new HashMap<>();
        distritoData.put("nombre", "Distrito Actualizado");
        distritoData.put("ubigeo", "987654");
        distritoData.put("distrito_id", String.valueOf(distritoId));

        Distrito mockDistrito = new Distrito();
        mockDistrito.setDistrito_id(distritoId);
        Mockito.when(distritoRepository.findById(distritoId)).thenReturn(Optional.of(mockDistrito));

        // Act
        try {
            Distrito updatedDistrito = distritoService.updateDistrito(distritoData);

            // Assert
            assertNotNull(updatedDistrito);
            assertEquals("Distrito Actualizado", updatedDistrito.getNombre());
            assertEquals("987654", updatedDistrito.getUbigeo());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testEliminarDistrito() {
        // Arrange
        int distritoId = 1;
        Distrito mockDistrito = new Distrito();
        mockDistrito.setDistrito_id(distritoId);
        Mockito.when(distritoRepository.findById(distritoId)).thenReturn(Optional.of(mockDistrito));

        // Act
        try {
            Distrito deletedDistrito = distritoService.eliminarDistrito(Map.of("distrito_id", String.valueOf(distritoId)));

            // Assert
            assertNotNull(deletedDistrito);
            assertEquals(distritoId, deletedDistrito.getDistrito_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }
}
