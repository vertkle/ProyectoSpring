package com.codigo.proyectospringcodigo.service;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Categoria;
import com.codigo.proyectospringcodigo.repository.CategoriaRepository;
import com.codigo.proyectospringcodigo.services.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTest {
    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllCategoria() {
        // Arrange
        List<Categoria> mockCategorias = new ArrayList<>();
        mockCategorias.add(new Categoria());
        Mockito.when(categoriaRepository.findAll()).thenReturn(mockCategorias);

        // Act
        List<Categoria> categorias = categoriaService.findAllCategoria();

        // Assert
        assertNotNull(categorias);
        assertEquals(1, categorias.size());
    }

    @Test
    public void testCreateCategoria() {
        // Arrange
        Map<String, String> categoriaData = new HashMap<>();
        categoriaData.put("nombre_categoria", "Categoria de Prueba");

        // Act
        try {
            Categoria createdCategoria = categoriaService.createCategoria(categoriaData);

            // Assert
            assertNotNull(createdCategoria);
            assertEquals("Categoria de Prueba", createdCategoria.getNombre_categoria());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testFindByIdCategoria() {
        // Arrange
        int categoriaId = 1;
        Categoria mockCategoria = new Categoria();
        mockCategoria.setCategoria_id(categoriaId);
        Mockito.when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(mockCategoria));

        // Act
        try {
            Categoria foundCategoria = categoriaService.findByIdCategoria(categoriaId);

            // Assert
            assertNotNull(foundCategoria);
            assertEquals(categoriaId, foundCategoria.getCategoria_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testUpdateCategoria() {
        // Arrange
        int categoriaId = 1;
        Map<String, String> categoriaData = new HashMap<>();
        categoriaData.put("nombre_categoria", "Categoria Actualizada");
        categoriaData.put("categoria_id", String.valueOf(categoriaId));

        Categoria mockCategoria = new Categoria();
        mockCategoria.setCategoria_id(categoriaId);
        Mockito.when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(mockCategoria));

        // Act
        try {
            Categoria updatedCategoria = categoriaService.updateCategoria(categoriaData);

            // Assert
            assertNotNull(updatedCategoria);
            assertEquals("Categoria Actualizada", updatedCategoria.getNombre_categoria());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }

    @Test
    public void testEliminarCategoria() {
        // Arrange
        int categoriaId = 1;
        Categoria mockCategoria = new Categoria();
        mockCategoria.setCategoria_id(categoriaId);
        Mockito.when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(mockCategoria));

        // Act
        try {
            Categoria deletedCategoria = categoriaService.eliminarCategoria(Map.of("categoria_id", String.valueOf(categoriaId)));

            // Assert
            assertNotNull(deletedCategoria);
            assertEquals(categoriaId, deletedCategoria.getCategoria_id());
        } catch (ErrorResponseException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }
}
