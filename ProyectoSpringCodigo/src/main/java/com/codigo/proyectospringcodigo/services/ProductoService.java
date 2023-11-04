package com.codigo.proyectospringcodigo.services;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Categoria;
import com.codigo.proyectospringcodigo.model.Producto;
import com.codigo.proyectospringcodigo.repository.ProductoRepository;
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
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAllProductos(){
        return productoRepository.findAll();
    }
    public Producto createProducto(Map<String, String> producto) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre_producto", "descripcion", "categoria_id"};
        IsValidateParametros(producto, parametrosRequeridos);
        IsNotEmptyValores(producto, parametrosRequeridos);
        IsValidateId(producto, "categoria_id");

        Producto prod = new Producto();
        prod.setNombre_producto(producto.get("nombre_producto"));
        prod.setDescripcion(producto.get("descripcion"));
        prod.setImagen_producto(producto.get("imagen_producto"));
        prod.setEstado(ESTADO_ACTIVO);
        Categoria cat = new Categoria();
        cat.setCategoria_id(Integer.parseInt(producto.get("categoria_id")));
        prod.setCategoria(cat);
        productoRepository.save(prod);
        return prod;
    }

    public Producto findByIdProducto(Integer producto_id) throws ErrorResponseException {
        Optional<Producto> prodBuscado = productoRepository.findById(producto_id);
        if(prodBuscado.isPresent()){
            return prodBuscado.get();
        }
        throw new ErrorResponseException(EX_NOT_FOUND_RECURSO, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }

    public Producto updateProducto(Map<String, String> producto) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre_producto", "descripcion", "categoria_id", "producto_id", "estado"};
        IsValidateParametros(producto, parametrosRequeridos);
        IsNotEmptyValores(producto, parametrosRequeridos);
        IsValidateId(producto, "categoria_id");
        IsValidateId(producto, "producto_id");

        Producto prod = findByIdProducto(Integer.parseInt(producto.get("producto_id")));
        prod.setNombre_producto(producto.get("nombre_producto"));
        prod.setDescripcion(producto.get("descripcion"));
        prod.setImagen_producto(producto.get("imagen_producto"));
        prod.setEstado(Integer.parseInt(producto.get("estado")));
        Categoria cat = new Categoria();
        cat.setCategoria_id(Integer.parseInt(producto.get("categoria_id")));
        prod.setCategoria(cat);
        productoRepository.save(prod);
        return prod;
    }

    public Producto eliminarProducto(Map<String, String> producto) throws ErrorResponseException {
        String parametrosRequeridos [] = {"producto_id"};
        Producto prod = findByIdProducto(Integer.parseInt(producto.get("producto_id")));
        productoRepository.delete(prod);
        return prod;
    }
}
