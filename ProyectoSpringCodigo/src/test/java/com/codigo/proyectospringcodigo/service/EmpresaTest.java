package com.codigo.proyectospringcodigo.service;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Empresa;
import com.codigo.proyectospringcodigo.repository.EmpresaRepository;
import com.codigo.proyectospringcodigo.services.EmpresaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.codigo.proyectospringcodigo.Utils.Constantes.ESTADO_ACTIVO;
import static org.junit.jupiter.api.Assertions.*;

public class EmpresaTest {
    @InjectMocks
    private EmpresaService empresaService;

    @Mock
    private EmpresaRepository empresaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testFindAllEmpresa() {
        // Arrange
        List<Empresa> mockEmpresas = new ArrayList<>();
        mockEmpresas.add(new Empresa());
        Mockito.when(empresaRepository.findAll()).thenReturn(mockEmpresas);

        // Act
        List<Empresa> empresas = empresaService.findAllEmpresa();

        // Assert
        assertNotNull(empresas);
        assertEquals(1, empresas.size());
    }
    @Test
    public void testCreateEmpresa() {
        // Arrange
        Map<String, String> empresaData = new HashMap<>();
        empresaData.put("nombre_empresa", "Empresa de Prueba");
        empresaData.put("ruc_empresa", "12345678901");
        empresaData.put("logo_empresa", "logo.png");
        empresaData.put("latitud", "123.456");
        empresaData.put("longitud", "456.789");
        empresaData.put("descripcion", "Esta es una empresa de prueba");
        empresaData.put("celular", "987654321");
        empresaData.put("direccion", "Calle de Prueba 123");
        empresaData.put("referencia_direccion", "Frente a la plaza de prueba");
        empresaData.put("distrito_id", "1");

        // Act
        try {
            Empresa createdEmpresa = empresaService.createEmpresa(empresaData);

            // Assert
            assertNotNull(createdEmpresa);
            assertEquals("Empresa de Prueba", createdEmpresa.getNombre_empresa());
            assertEquals("12345678901", createdEmpresa.getRuc_empresa());
            assertEquals("logo.png", createdEmpresa.getLogo_empresa());
            assertEquals("123.456", createdEmpresa.getLatitud());
            assertEquals("456.789", createdEmpresa.getLongitud());
            assertEquals("Esta es una empresa de prueba", createdEmpresa.getDescripcion());
            assertEquals("987654321", createdEmpresa.getCelular());
            assertEquals("Calle de Prueba 123", createdEmpresa.getDireccion());
            assertEquals("Frente a la plaza de prueba", createdEmpresa.getReferencia_direccion());
            assertEquals(ESTADO_ACTIVO, createdEmpresa.getEstado());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testFindByIdEmpresa() {
        // Arrange
        int empresaId = 1;
        Empresa mockEmpresa = new Empresa();
        mockEmpresa.setEmpresa_id(empresaId);
        Mockito.when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(mockEmpresa));

        // Act
        try {
            Empresa foundEmpresa = empresaService.findByIdEmpresa(empresaId);

            // Assert
            assertNotNull(foundEmpresa);
            assertEquals(empresaId, foundEmpresa.getEmpresa_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testUpdateEmpresa() {
        // Arrange
        int empresaId = 1;
        Map<String, String> empresaData = new HashMap<>();
        empresaData.put("nombre_empresa", "Empresa Actualizada");
        empresaData.put("ruc_empresa", "98765432109");
        empresaData.put("logo_empresa", "new_logo.png");
        empresaData.put("latitud", "987.654");
        empresaData.put("longitud", "654.321");
        empresaData.put("descripcion", "Esta es una empresa actualizada");
        empresaData.put("celular", "123456789");
        empresaData.put("direccion", "Nueva Calle 456");
        empresaData.put("referencia_direccion", "Frente al nuevo parque");
        empresaData.put("empresa_id", String.valueOf(empresaId));
        empresaData.put("distrito_id", "2");
        empresaData.put("estado", "0");

        Empresa mockEmpresa = new Empresa();
        mockEmpresa.setEmpresa_id(empresaId);
        Mockito.when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(mockEmpresa));

        // Act
        try {
            Empresa updatedEmpresa = empresaService.updateEmpresa(empresaData);

            // Assert
            assertNotNull(updatedEmpresa);
            assertEquals("Empresa Actualizada", updatedEmpresa.getNombre_empresa());
            assertEquals("98765432109", updatedEmpresa.getRuc_empresa());
            assertEquals("new_logo.png", updatedEmpresa.getLogo_empresa());
            assertEquals("987.654", updatedEmpresa.getLatitud());
            assertEquals("654.321", updatedEmpresa.getLongitud());
            assertEquals("Esta es una empresa actualizada", updatedEmpresa.getDescripcion());
            assertEquals("123456789", updatedEmpresa.getCelular());
            assertEquals("Nueva Calle 456", updatedEmpresa.getDireccion());
            assertEquals("Frente al nuevo parque", updatedEmpresa.getReferencia_direccion());
            assertEquals(0, updatedEmpresa.getEstado());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testEliminarEmpresa() {
        // Arrange
        int empresaId = 1;
        Empresa mockEmpresa = new Empresa();
        mockEmpresa.setEmpresa_id(empresaId);
        Mockito.when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(mockEmpresa));

        // Act
        try {
            Empresa deletedEmpresa = empresaService.eliminarEmpresa(Map.of("empresa_id", String.valueOf(empresaId)));

            // Assert
            assertNotNull(deletedEmpresa);
            assertEquals(empresaId, deletedEmpresa.getEmpresa_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }
}
