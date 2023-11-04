package com.codigo.proyectospringcodigo.controller;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Empresa;
import com.codigo.proyectospringcodigo.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas(){
        List<Empresa> listaEmpresa = empresaService.findAllEmpresa();
        return new ResponseEntity<>(listaEmpresa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Empresa>createEmpresaController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        Empresa emp = empresaService.createEmpresa(request);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Empresa>updateEmpresaController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        Empresa emp = empresaService.updateEmpresa(request);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Empresa>deleteEmpresaController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        Empresa emp = empresaService.eliminarEmpresa(request);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}
