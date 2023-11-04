package com.codigo.proyectospringcodigo.service;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Producto;
import com.codigo.proyectospringcodigo.repository.ProductoRepository;
import com.codigo.proyectospringcodigo.services.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.codigo.proyectospringcodigo.Utils.Constantes.ESTADO_ACTIVO;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {
    @InjectMocks
    private ProductoService productoService;
    @Mock
    private ProductoRepository productoRepository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllProductos() {
        // Arrange
        List<Producto> mockProductos = new ArrayList<>();
        mockProductos.add(new Producto());
        Mockito.when(productoRepository.findAll()).thenReturn(mockProductos);

        // Act
        List<Producto> productos = productoService.findAllProductos();

        // Assert
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    public void testCreateProducto() {
        // Arrange
        Map<String, String> request = new HashMap<>();
        request.put("nombre_producto", "Producto de prueba");
        request.put("descripcion", "Descripción de prueba");
        request.put("categoria_id", "1");

        // Act
        try {
            Producto createdProducto = productoService.createProducto(request);

            // Assert
            assertNotNull(createdProducto);
            assertEquals("Producto de prueba", createdProducto.getNombre_producto());
            assertEquals("Descripción de prueba", createdProducto.getDescripcion());
            assertEquals(ESTADO_ACTIVO, createdProducto.getEstado());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testFindByIdProducto() {
        // Arrange
        int productoId = 1;
        Producto mockProducto = new Producto();
        mockProducto.setProducto_id(productoId);
        Mockito.when(productoRepository.findById(productoId)).thenReturn(Optional.of(mockProducto));

        // Act
        try {
            Producto foundProducto = productoService.findByIdProducto(productoId);

            // Assert
            assertNotNull(foundProducto);
            assertEquals(productoId, foundProducto.getProducto_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testUpdateProducto() {
        // Arrange
        int productoId = 1;
        Map<String, String> request = new HashMap<>();
        request.put("nombre_producto", "Producto actualizado");
        request.put("descripcion", "Descripción actualizada");
        request.put("categoria_id", "2");
        request.put("producto_id", "1");
        request.put("estado", "0");

        Producto mockProducto = new Producto();
        mockProducto.setProducto_id(productoId);
        Mockito.when(productoRepository.findById(productoId)).thenReturn(Optional.of(mockProducto));

        // Act
        try {
            Producto updatedProducto = productoService.updateProducto(request);

            // Assert
            assertNotNull(updatedProducto);
            assertEquals("Producto actualizado", updatedProducto.getNombre_producto());
            assertEquals("Descripción actualizada", updatedProducto.getDescripcion());
            assertEquals(0, updatedProducto.getEstado());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testEliminarProducto() {
        // Arrange
        int productoId = 1;
        Producto mockProducto = new Producto();
        mockProducto.setProducto_id(productoId);
        Mockito.when(productoRepository.findById(productoId)).thenReturn(Optional.of(mockProducto));

        // Act
        try {
            Producto deletedProducto = productoService.eliminarProducto(Map.of("producto_id", String.valueOf(productoId)));

            // Assert
            assertNotNull(deletedProducto);
            assertEquals(productoId, deletedProducto.getProducto_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }
}
