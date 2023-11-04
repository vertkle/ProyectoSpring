package com.codigo.proyectospringcodigo.services;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.CalificacionEmpresa;
import com.codigo.proyectospringcodigo.model.Empresa;
import com.codigo.proyectospringcodigo.repository.CalificacionEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.codigo.proyectospringcodigo.Utils.Constantes.EX_NOT_FOUND_RECURSO;
import static com.codigo.proyectospringcodigo.Utils.Utilitarios.*;

@Service
public class CalificacionEmpresaService {
    @Autowired
    private CalificacionEmpresaRepository calificacionEmpresaRepository;

    public List<CalificacionEmpresa>findAllCalificacionEmpresa(){
        return calificacionEmpresaRepository.findAll();
    }
    public CalificacionEmpresa createCalificacionEmpresa(Map<String, String> calificacion) throws ErrorResponseException {
        String parametrosRequeridos [] = {"ip_registro", "puntuacion"};
        IsValidateParametros(calificacion, parametrosRequeridos);
        IsNotEmptyValores(calificacion, parametrosRequeridos);
        IsValidateId(calificacion, "puntuacion");

        CalificacionEmpresa cal = new CalificacionEmpresa();
        cal.setPuntuacion(Integer.parseInt(calificacion.get("puntuacion")));
        cal.setIp_registro(calificacion.get("ip_registro"));
        Empresa emp = new Empresa();
        emp.setEmpresa_id(Integer.parseInt(calificacion.get("empresa_id")));
        cal.setEmpresa(emp);
        calificacionEmpresaRepository.save(cal);
        return cal;
    }
    public CalificacionEmpresa findByIdCalificacion(Integer id) throws ErrorResponseException {
        Optional<CalificacionEmpresa> calificacionById = calificacionEmpresaRepository.findById(id);
        if(calificacionById.isPresent()){
            return calificacionById.get();
        }
        throw new ErrorResponseException(EX_NOT_FOUND_RECURSO, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }
    public CalificacionEmpresa updateCalificacion(Map<String,String>calificacion) throws ErrorResponseException {
        String parametrosRequeridos [] = {"ip_registro", "puntuacion", "calificacion_id"};
        IsValidateParametros(calificacion, parametrosRequeridos);
        IsNotEmptyValores(calificacion, parametrosRequeridos);
        IsValidateId(calificacion, "calificacion_id");

        CalificacionEmpresa calificacionBuscado = findByIdCalificacion(Integer.parseInt(calificacion.get("calificacion_id")));
        calificacionBuscado.setIp_registro(calificacion.get("ip_registro"));
        calificacionBuscado.setPuntuacion(Integer.parseInt(calificacion.get("puntuacion")));
        Empresa emp = new Empresa();
        emp.setEmpresa_id(Integer.parseInt(calificacion.get("empresa_id")));
        calificacionBuscado.setEmpresa(emp);
        calificacionEmpresaRepository.save(calificacionBuscado);
        return calificacionBuscado;
    }
    public CalificacionEmpresa eliminarCalificacion(Map<String, String>calificacion) throws ErrorResponseException {
        String parametrosRequeridos [] = {"calificacion_id"};
        IsValidateParametros(calificacion, parametrosRequeridos);
        IsNotEmptyValores(calificacion, parametrosRequeridos);
        IsValidateId(calificacion, "calificacion_id");

        CalificacionEmpresa calificacionBuscado = findByIdCalificacion(Integer.parseInt(calificacion.get("calificacion_id")));
        calificacionEmpresaRepository.delete(calificacionBuscado);
        return calificacionBuscado;
    }
}
