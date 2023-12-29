package com.codigo.proyectospringcodigo.controller;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Distrito;
import com.codigo.proyectospringcodigo.services.DistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/distrito")
//@RefreshScope
public class DistritoController {
    @Autowired
    private DistritoService distritoService;

//    @Value("${app.testProp}")
//    private String testProp;
//    @GetMapping("/test-prod")
//    public String getTestProp(){
//        return this.testProp;
//    }

    @GetMapping
    public ResponseEntity<List<Distrito>> findAllDistritosController(){
        List<Distrito> listaDistrito = distritoService.findAllDistrito();
        return new ResponseEntity<>(listaDistrito, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Distrito>  createDistritoController(@RequestBody Map<String, String> request) throws ErrorResponseException {
        Distrito dis = distritoService.createDistrito(request);
        return new ResponseEntity<>(dis,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Distrito> updateDistrito(@RequestBody Map<String, String> request) throws ErrorResponseException {
        Distrito dis = distritoService.updateDistrito(request);
        return new ResponseEntity<>(dis,HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Distrito> deleteDistrito(@RequestBody Map<String,String> distrito) throws ErrorResponseException {
        Distrito dis = distritoService.eliminarDistrito(distrito);
        return new ResponseEntity<>(dis, HttpStatus.OK);
    }
}
