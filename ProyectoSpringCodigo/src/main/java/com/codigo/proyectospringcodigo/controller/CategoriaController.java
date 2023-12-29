package com.codigo.proyectospringcodigo.controller;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Categoria;
import com.codigo.proyectospringcodigo.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>>findAllCategoriaController(){
        List<Categoria> listaCategoria = categoriaService.findAllCategoria();
        return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoriaController(@RequestBody Map<String, String>request) throws ErrorResponseException {
        Categoria cat = categoriaService.createCategoria(request);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Categoria> updateCategoriaController(@RequestBody Map<String, String> request) throws ErrorResponseException {
        Categoria cat = categoriaService.updateCategoria(request);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @PostMapping("/eliminar_categoria")
    public ResponseEntity<Categoria> eliminarCategoriaController(@RequestBody Map<String, String> request) throws ErrorResponseException {
        Categoria cat = categoriaService.eliminarCategoria(request);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }
}
