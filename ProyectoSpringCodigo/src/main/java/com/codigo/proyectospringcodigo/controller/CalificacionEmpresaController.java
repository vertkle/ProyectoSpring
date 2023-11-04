package com.codigo.proyectospringcodigo.controller;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.CalificacionEmpresa;
import com.codigo.proyectospringcodigo.services.CalificacionEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/calificacion")
public class CalificacionEmpresaController {
    @Autowired
    private CalificacionEmpresaService calificacionEmpresaService;

    @GetMapping
    public ResponseEntity<List<CalificacionEmpresa>>findAllCalificacionController(){
        List<CalificacionEmpresa>listaCalificacion = calificacionEmpresaService.findAllCalificacionEmpresa();
        return new ResponseEntity<>(listaCalificacion, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CalificacionEmpresa>createCalificacionController(@RequestBody Map<String, String>request) throws ErrorResponseException {
        CalificacionEmpresa cal = calificacionEmpresaService.createCalificacionEmpresa(request);
        return new ResponseEntity<>(cal, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<CalificacionEmpresa>updateCalificacionController(@RequestBody Map<String,String>request) throws ErrorResponseException {
        CalificacionEmpresa cal = calificacionEmpresaService.updateCalificacion(request);
        return new ResponseEntity<>(cal, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<CalificacionEmpresa>eliminarCalificacionController(@RequestBody Map<String, String>request) throws ErrorResponseException {
        CalificacionEmpresa cal = calificacionEmpresaService.eliminarCalificacion(request);
        return new ResponseEntity<>(cal, HttpStatus.OK);
    }

}
