package com.codigo.proyectospringcodigo.service;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.CalificacionEmpresa;
import com.codigo.proyectospringcodigo.repository.CalificacionEmpresaRepository;
import com.codigo.proyectospringcodigo.services.CalificacionEmpresaService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class CalificacionEmpresaTest {
    @InjectMocks
    private CalificacionEmpresaService calificacionEmpresaService;

    @Mock
    private CalificacionEmpresaRepository calificacionEmpresaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllCalificacionEmpresa() {
        // Arrange
        List<CalificacionEmpresa> mockCalificaciones = new ArrayList<>();
        mockCalificaciones.add(new CalificacionEmpresa());
        Mockito.when(calificacionEmpresaRepository.findAll()).thenReturn(mockCalificaciones);

        // Act
        List<CalificacionEmpresa> calificaciones = calificacionEmpresaService.findAllCalificacionEmpresa();

        // Assert
        assertNotNull(calificaciones);
        assertEquals(1, calificaciones.size());
    }

    @Test
    public void testCreateCalificacionEmpresa() {
        // Arrange
        Map<String, String> calificacionData = new HashMap<>();
        calificacionData.put("ip_registro", "127.0.0.1");
        calificacionData.put("puntuacion", "5");
        calificacionData.put("empresa_id", "1");

        // Act
        try {
            CalificacionEmpresa createdCalificacion = calificacionEmpresaService.createCalificacionEmpresa(calificacionData);

            // Assert
            assertNotNull(createdCalificacion);
            assertEquals(5, createdCalificacion.getPuntuacion());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testFindByIdCalificacion() {
        // Arrange
        int calificacionId = 1;
        CalificacionEmpresa mockCalificacion = new CalificacionEmpresa();
        mockCalificacion.setCalificacion_id(calificacionId);
        Mockito.when(calificacionEmpresaRepository.findById(calificacionId)).thenReturn(Optional.of(mockCalificacion));

        // Act
        try {
            CalificacionEmpresa foundCalificacion = calificacionEmpresaService.findByIdCalificacion(calificacionId);

            // Assert
            assertNotNull(foundCalificacion);
            assertEquals(calificacionId, foundCalificacion.getCalificacion_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testUpdateCalificacion() {
        // Arrange
        int calificacionId = 1;
        Map<String, String> calificacionData = new HashMap<>();
        calificacionData.put("ip_registro", "127.0.0.1");
        calificacionData.put("puntuacion", "4");
        calificacionData.put("calificacion_id", String.valueOf(calificacionId));
        calificacionData.put("empresa_id", "1");

        CalificacionEmpresa mockCalificacion = new CalificacionEmpresa();
        mockCalificacion.setCalificacion_id(calificacionId);
        Mockito.when(calificacionEmpresaRepository.findById(calificacionId)).thenReturn(Optional.of(mockCalificacion));

        // Act
        try {
            CalificacionEmpresa updatedCalificacion = calificacionEmpresaService.updateCalificacion(calificacionData);

            // Assert
            assertNotNull(updatedCalificacion);
            assertEquals("127.0.0.1", updatedCalificacion.getIp_registro());
            assertEquals(4, updatedCalificacion.getPuntuacion());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testEliminarCalificacion() {
        // Arrange
        int calificacionId = 1;
        CalificacionEmpresa mockCalificacion = new CalificacionEmpresa();
        mockCalificacion.setCalificacion_id(calificacionId);
        Mockito.when(calificacionEmpresaRepository.findById(calificacionId)).thenReturn(Optional.of(mockCalificacion));

        // Act
        try {
            CalificacionEmpresa deletedCalificacion = calificacionEmpresaService.eliminarCalificacion(Map.of("calificacion_id", String.valueOf(calificacionId)));

            // Assert
            assertNotNull(deletedCalificacion);
            assertEquals(calificacionId, deletedCalificacion.getCalificacion_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }
}
