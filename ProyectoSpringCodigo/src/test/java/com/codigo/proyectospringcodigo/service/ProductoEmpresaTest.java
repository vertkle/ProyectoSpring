package com.codigo.proyectospringcodigo.service;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.ProductoEmpresa;
import com.codigo.proyectospringcodigo.repository.ProductoEmpresaRepository;
import com.codigo.proyectospringcodigo.services.ProductoEmpresaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.codigo.proyectospringcodigo.Utils.Constantes.ESTADO_ACTIVO;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoEmpresaTest {
    @InjectMocks
    private ProductoEmpresaService prodEmpService;
    @Mock
    private ProductoEmpresaRepository prodEmpRepository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllProductoEmpresa() {
        // Arrange
        List<ProductoEmpresa> mockProdEmpresas = new ArrayList<>();
        mockProdEmpresas.add(new ProductoEmpresa());
        Mockito.when(prodEmpRepository.findAll()).thenReturn(mockProdEmpresas);

        // Act
        List<ProductoEmpresa> prodEmpresas = prodEmpService.findAllProductoEmpresa();

        // Assert
        assertNotNull(prodEmpresas);
        assertEquals(1, prodEmpresas.size());
    }

    @Test
    public void testCreateProductoEmpresa() {
        // Arrange
        Map<String, String> request = new HashMap<>();
        request.put("precio", "10.0");
        request.put("cantidad", "100");
        request.put("producto_id", "1");
        request.put("empresa_id", "2");

        // Act
        try {
            ProductoEmpresa createdProdEmp = prodEmpService.createProductoEmpresa(request);

            // Assert
            assertNotNull(createdProdEmp);
            assertEquals(10.0, createdProdEmp.getPrecio(), 0.001);
            assertEquals(100, createdProdEmp.getCantidad());
            assertEquals(ESTADO_ACTIVO, createdProdEmp.getEstado());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testFindByIdProdEmp() {
        // Arrange
        int prodEmpId = 1;
        ProductoEmpresa mockProdEmp = new ProductoEmpresa();
        mockProdEmp.setProdemp_id(prodEmpId);
        Mockito.when(prodEmpRepository.findById(prodEmpId)).thenReturn(Optional.of(mockProdEmp));

        // Act
        try {
            ProductoEmpresa foundProdEmp = prodEmpService.findByIdProdEmp(prodEmpId);

            // Assert
            assertNotNull(foundProdEmp);
            assertEquals(prodEmpId, foundProdEmp.getProdemp_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testUpdateProductoEmpresa() {
        // Arrange
        int prodEmpId = 1;
        Map<String, String> request = new HashMap<>();
        request.put("precio", "15.0");
        request.put("cantidad", "50");
        request.put("prodemp_id", "1");
        request.put("estado", "0");

        ProductoEmpresa mockProdEmp = new ProductoEmpresa();
        mockProdEmp.setProdemp_id(prodEmpId);
        Mockito.when(prodEmpRepository.findById(prodEmpId)).thenReturn(Optional.of(mockProdEmp));

        // Act
        try {
            ProductoEmpresa updatedProdEmp = prodEmpService.updateProductoEmpresa(request);

            // Assert
            assertNotNull(updatedProdEmp);
            assertEquals(15.0, updatedProdEmp.getPrecio(), 0.001);
            assertEquals(50, updatedProdEmp.getCantidad());
            assertEquals(0, updatedProdEmp.getEstado());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testEliminarProductoEmpresa() {
        // Arrange
        int prodEmpId = 1;
        ProductoEmpresa mockProdEmp = new ProductoEmpresa();
        mockProdEmp.setProdemp_id(prodEmpId);
        Mockito.when(prodEmpRepository.findById(prodEmpId)).thenReturn(Optional.of(mockProdEmp));

        // Act
        try {
            ProductoEmpresa deletedProdEmp = prodEmpService.eliminarProductoEmpresa(Map.of("prodemp_id", String.valueOf(prodEmpId)));

            // Assert
            assertNotNull(deletedProdEmp);
            assertEquals(prodEmpId, deletedProdEmp.getProdemp_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }
}
