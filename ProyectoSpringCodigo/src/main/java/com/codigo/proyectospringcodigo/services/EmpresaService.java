package com.codigo.proyectospringcodigo.services;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Distrito;
import com.codigo.proyectospringcodigo.model.Empresa;
import com.codigo.proyectospringcodigo.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.codigo.proyectospringcodigo.Utils.Constantes.ESTADO_ACTIVO;
import static com.codigo.proyectospringcodigo.Utils.Constantes.EX_NOT_FOUND_RECURSO;
import static com.codigo.proyectospringcodigo.Utils.Utilitarios.*;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> findAllEmpresa(){
        return empresaRepository.findAll();
    }
    public Empresa createEmpresa(Map<String, String>empresa) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre_empresa","ruc_empresa","logo_empresa","latitud","longitud","descripcion","celular",
        "direccion","referencia_direccion"};
        IsValidateParametros(empresa, parametrosRequeridos);
        IsNotEmptyValores(empresa, parametrosRequeridos);
        IsValidateId(empresa, "distrito_id");


        Empresa emp = new Empresa();
        emp.setNombre_empresa(empresa.get("nombre_empresa"));
        emp.setRuc_empresa(empresa.get("ruc_empresa"));
        emp.setLogo_empresa(empresa.get("logo_empresa"));
        emp.setLatitud(empresa.get("latitud"));
        emp.setLongitud(empresa.get("longitud"));
        emp.setDescripcion(empresa.get("descripcion"));
        emp.setCelular(empresa.get("celular"));
        emp.setDireccion(empresa.get("direccion"));
        emp.setReferencia_direccion(empresa.get("referencia_direccion"));
        emp.setCreated_at(new Date());
        emp.setEstado(ESTADO_ACTIVO);
        Distrito dis = new Distrito();
        dis.setDistrito_id(Integer.parseInt(empresa.get("distrito_id")));
        emp.setDistrito(dis);
        empresaRepository.save(emp);
        return emp;
    }

    public Empresa findByIdEmpresa(Integer empresa_id) throws ErrorResponseException {
        Optional<Empresa> empresaById = empresaRepository.findById(empresa_id);
        if(empresaById.isPresent()){
            return empresaById.get();
        }
        throw new ErrorResponseException(EX_NOT_FOUND_RECURSO, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }

    public Empresa updateEmpresa(Map<String, String> empresa) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre_empresa","ruc_empresa","logo_empresa","latitud","longitud","descripcion","celular", "direccion","referencia_direccion", "empresa_id"};
        IsValidateParametros(empresa, parametrosRequeridos);
        IsNotEmptyValores(empresa, parametrosRequeridos);
        IsValidateId(empresa, "empresa_id");
        IsValidateId(empresa, "distrito_id");

        Empresa empresaBuscado = findByIdEmpresa(Integer.parseInt(empresa.get("empresa_id")));
        empresaBuscado.setNombre_empresa(empresa.get("nombre_empresa"));
        empresaBuscado.setRuc_empresa(empresa.get("ruc_empresa"));
        empresaBuscado.setLogo_empresa(empresa.get("logo_empresa"));
        empresaBuscado.setLatitud(empresa.get("latitud"));
        empresaBuscado.setLongitud(empresa.get("longitud"));
        empresaBuscado.setDescripcion(empresa.get("descripcion"));
        empresaBuscado.setCelular(empresa.get("celular"));
        empresaBuscado.setDireccion(empresa.get("direccion"));
        empresaBuscado.setReferencia_direccion(empresa.get("referencia_direccion"));
        empresaBuscado.setEstado(Integer.parseInt(empresa.get("estado")));
        Distrito dis = new Distrito();
        dis.setDistrito_id(Integer.parseInt(empresa.get("distrito_id")));
        empresaBuscado.setDistrito(dis);
        empresaRepository.save(empresaBuscado);
        return empresaBuscado;
    }

    public Empresa eliminarEmpresa(Map<String, String> empresa) throws ErrorResponseException {
        String parametrosRequeridos [] = {"empresa_id"};
        Empresa empresaBuscado = findByIdEmpresa(Integer.parseInt(empresa.get("empresa_id")));
        empresaRepository.delete(empresaBuscado);
        return empresaBuscado;
    }
}
