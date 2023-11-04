package com.codigo.proyectospringcodigo.services;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import com.codigo.proyectospringcodigo.model.Categoria;
import com.codigo.proyectospringcodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static com.codigo.proyectospringcodigo.Utils.Constantes.EX_NOT_FOUND_RECURSO;
import static com.codigo.proyectospringcodigo.Utils.Utilitarios.*;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria>findAllCategoria(){
        return categoriaRepository.findAll();
    }

    public Categoria createCategoria(Map<String, String> categoria) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre_categoria"};
        IsValidateParametros(categoria, parametrosRequeridos);
        IsNotEmptyValores(categoria, parametrosRequeridos);

        Categoria cat = new Categoria();
        cat.setNombre_categoria(categoria.get("nombre_categoria"));
        categoriaRepository.save(cat);
        return cat;
    }
    public Categoria findByIdCategoria(Integer categoria_id) throws ErrorResponseException {
        Optional<Categoria>categoriaById = categoriaRepository.findById(categoria_id);
        if(categoriaById.isPresent()){
            return categoriaById.get();
        }
        throw new ErrorResponseException(EX_NOT_FOUND_RECURSO, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }
    public Categoria updateCategoria(Map<String,String>categoria) throws ErrorResponseException {
        String parametrosRequeridos [] = {"nombre_categoria","categoria_id"};
        IsValidateParametros(categoria, parametrosRequeridos);
        IsNotEmptyValores(categoria, parametrosRequeridos);
        IsValidateId(categoria, "categoria_id");

        Categoria categoriaBuscado = findByIdCategoria(Integer.parseInt(categoria.get("categoria_id")));
        categoriaBuscado.setNombre_categoria(categoria.get("nombre_categoria"));
        categoriaRepository.save(categoriaBuscado);
        return categoriaBuscado;
    }
    public Categoria eliminarCategoria(Map<String,String>categoria) throws ErrorResponseException {
        String parametrosRequeridos [] = {"categoria_id"};
        IsValidateParametros(categoria, parametrosRequeridos);
        IsNotEmptyValores(categoria, parametrosRequeridos);
        IsValidateId(categoria, "categoria_id");

        Categoria categoriaBuscado = findByIdCategoria(Integer.parseInt(categoria.get("categoria_id")));
        categoriaRepository.delete(categoriaBuscado);
        return categoriaBuscado;
    }
}
