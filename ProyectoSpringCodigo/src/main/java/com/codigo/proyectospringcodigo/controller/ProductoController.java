package com.codigo.proyectospringcodigo.controller;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Producto;
import com.codigo.proyectospringcodigo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducto(){
        List<Producto> listaProducto = productoService.findAllProductos();
        return new ResponseEntity<>(listaProducto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Producto>createProductoController(@RequestBody Map<String,String> request) throws ErrorResponseException {
        Producto prod = productoService.createProducto(request);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Producto>updateProductoController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        Producto prod = productoService.updateProducto(request);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Producto>deleteEmpresaController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        Producto prod = productoService.eliminarProducto(request);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

}
