package com.codigo.proyectospringcodigo.services;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Empresa;
import com.codigo.proyectospringcodigo.model.Producto;
import com.codigo.proyectospringcodigo.model.ProductoEmpresa;
import com.codigo.proyectospringcodigo.repository.ProductoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.codigo.proyectospringcodigo.Utils.Constantes.ESTADO_ACTIVO;
import static com.codigo.proyectospringcodigo.Utils.Constantes.EX_NOT_FOUND_RECURSO;
import static com.codigo.proyectospringcodigo.Utils.Utilitarios.*;

@Service
public class ProductoEmpresaService {
    @Autowired
    private ProductoEmpresaRepository prodEmpRepository;

    public List<ProductoEmpresa> findAllProductoEmpresa(){
        return prodEmpRepository.findAll();
    }
    public ProductoEmpresa createProductoEmpresa(Map<String, String> request) throws ErrorResponseException {
        String parametrosRequeridos [] = {"precio", "cantidad", "producto_id","empresa_id"};
        IsValidateParametros(request, parametrosRequeridos);
        IsNotEmptyValores(request, parametrosRequeridos);
        IsValidateId(request, "producto_id");
        IsValidateId(request, "empresa_id");

        ProductoEmpresa prodEmp = new ProductoEmpresa();
        prodEmp.setPrecio(Double.parseDouble(request.get("precio")));
        prodEmp.setCantidad(Integer.parseInt(request.get("cantidad")));
        Producto prod = new Producto();
        prod.setProducto_id(Integer.parseInt(request.get("producto_id")));
        prodEmp.setProducto(prod);
        prodEmp.setEstado(ESTADO_ACTIVO);
        Empresa emp = new Empresa();
        emp.setEmpresa_id(Integer.parseInt(request.get("empresa_id")));
        prodEmp.setEmpresa(emp);
        prodEmpRepository.save(prodEmp);
        return prodEmp;
    }

    public ProductoEmpresa findByIdProdEmp(Integer prodEmpId) throws ErrorResponseException {
        Optional<ProductoEmpresa> prodEmpById = prodEmpRepository.findById(prodEmpId);
        if(prodEmpById.isPresent()){
            return prodEmpById.get();
        }
        throw new ErrorResponseException(EX_NOT_FOUND_RECURSO, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }

    public ProductoEmpresa updateProductoEmpresa(Map<String, String> request) throws ErrorResponseException {
        String parametrosRequeridos [] = {"precio", "cantidad","prodemp_id", "estado"};
        IsValidateParametros(request, parametrosRequeridos);
        IsNotEmptyValores(request, parametrosRequeridos);
        IsValidateId(request, "prodemp_id");

        ProductoEmpresa prodEmp = findByIdProdEmp(Integer.parseInt(request.get("prodemp_id")));
        prodEmp.setPrecio(Double.parseDouble(request.get("precio")));
        prodEmp.setCantidad(Integer.parseInt(request.get("cantidad")));
        prodEmp.setEstado(Integer.parseInt(request.get("estado")));

        prodEmpRepository.save(prodEmp);
        return prodEmp;
    }

    public ProductoEmpresa eliminarProductoEmpresa(Map<String, String>request) throws ErrorResponseException {
        String parametrosRequeridos [] = {"prodemp_id"};
        IsValidateParametros(request, parametrosRequeridos);
        IsNotEmptyValores(request, parametrosRequeridos);
        IsValidateId(request, "prodemp_id");

        ProductoEmpresa prodEmp = findByIdProdEmp(Integer.parseInt(request.get("prodemp_id")));
        prodEmpRepository.delete(prodEmp);
        return prodEmp;
    }
}
