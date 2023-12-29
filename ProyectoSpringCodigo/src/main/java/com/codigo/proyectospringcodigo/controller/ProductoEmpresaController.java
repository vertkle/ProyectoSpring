package com.codigo.proyectospringcodigo.controller;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.ProductoEmpresa;
import com.codigo.proyectospringcodigo.services.ProductoEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/producto_empresa")
public class ProductoEmpresaController {
    @Autowired
    private ProductoEmpresaService productoEmpresaService;

    @GetMapping
    public ResponseEntity<List<ProductoEmpresa>>findAllProductoEmpresaController(){
        List<ProductoEmpresa> listaProdEmp = productoEmpresaService.findAllProductoEmpresa();
        return new ResponseEntity<>(listaProdEmp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductoEmpresa>createProdEmpController(@RequestBody Map<String, String>request) throws ErrorResponseException {
        ProductoEmpresa prodEmp = productoEmpresaService.createProductoEmpresa(request);
        return new ResponseEntity<>(prodEmp, HttpStatus.OK);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<ProductoEmpresa>updateProdEmpController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        ProductoEmpresa prodEmp = productoEmpresaService.updateProductoEmpresa(request);
        return new ResponseEntity<>(prodEmp, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ProductoEmpresa>eliminarCalificacionController(@RequestBody Map<String, String>request) throws ErrorResponseException {
        ProductoEmpresa prodEmp = productoEmpresaService.eliminarProductoEmpresa(request);
        return new ResponseEntity<>(prodEmp, HttpStatus.OK);
    }

    @PostMapping("/get_by_empresa")
    public ResponseEntity<List<ProductoEmpresa>>findByEmpresa(@RequestBody Map<String, String>request) throws ErrorResponseException {
        List<ProductoEmpresa> listaProdEmp = productoEmpresaService.findByEmpresa(request);
        return new ResponseEntity<>(listaProdEmp, HttpStatus.OK);
    }
}
