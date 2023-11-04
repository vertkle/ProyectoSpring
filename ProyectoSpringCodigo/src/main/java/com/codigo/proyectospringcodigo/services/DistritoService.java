package com.codigo.proyectospringcodigo.services;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Distrito;
import com.codigo.proyectospringcodigo.repository.DistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.codigo.proyectospringcodigo.Utils.Constantes.*;
import static com.codigo.proyectospringcodigo.Utils.Utilitarios.*;

@Service
public class DistritoService {

    @Autowired
    private DistritoRepository distritoRepository;

    // ====================================CRUD
    public List<Distrito> findAllDistrito(){
        return distritoRepository.findAll();
    }
    public Distrito createDistrito(Map<String, String> distrito) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre","ubigeo"};
        IsValidateParametros(distrito, parametrosRequeridos);
        IsNotEmptyValores(distrito, parametrosRequeridos);

        Distrito dis = new Distrito();
        dis.setNombre(distrito.get("nombre"));
        dis.setUbigeo(distrito.get("ubigeo"));
        distritoRepository.save(dis);
        return dis;
    }

    public Distrito findByIdDistrito(Integer distrito_id) throws ErrorResponseException {
        Optional<Distrito> distritoById = distritoRepository.findById(distrito_id);
        if(distritoById.isPresent()){
            return distritoById.get();
        }
        throw new ErrorResponseException(EX_NOT_FOUND_RECURSO,HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }

    public Distrito updateDistrito(Map<String, String> distrito) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre","ubigeo","distrito_id"};
        IsValidateParametros(distrito, parametrosRequeridos);
        IsNotEmptyValores(distrito, parametrosRequeridos);
        IsValidateId(distrito, "distrito_id");

        Distrito distritoBuscado = findByIdDistrito(Integer.parseInt(distrito.get("distrito_id")));
        distritoBuscado.setNombre(distrito.get("nombre"));
        distritoBuscado.setUbigeo(distrito.get("ubigeo"));
        distritoRepository.save(distritoBuscado);
        return distritoBuscado;
    }

    public Distrito eliminarDistrito(Map<String, String> distrito) throws ErrorResponseException {
        String parametrosRequeridos [] = {"distrito_id"};
        IsValidateParametros(distrito, parametrosRequeridos);
        IsNotEmptyValores(distrito, parametrosRequeridos);
        IsValidateId(distrito, "distrito_id");

        Distrito distritoBuscado = findByIdDistrito(Integer.parseInt(distrito.get("distrito_id")));
        distritoRepository.delete(distritoBuscado);
        return distritoBuscado;
    }

}
