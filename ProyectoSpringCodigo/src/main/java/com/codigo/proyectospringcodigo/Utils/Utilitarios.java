package com.codigo.proyectospringcodigo.Utils;

import com.codigo.proyectospringcodigo.Exception.Errors.ErrorResponseException;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.codigo.proyectospringcodigo.Utils.Constantes.*;

public class Utilitarios {
    public static final boolean IsValidateParametros(Map<String, String> dict, String parametrosRequeridos[]) throws ErrorResponseException {
        for (String parametro : parametrosRequeridos) {
            if (!dict.containsKey(parametro)) {
                throw new ErrorResponseException(EX_ERROR_PARAMETROS, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
            }
        }
        return true;
    }
    public static final boolean IsNotEmptyValores(Map<String, String> dict, String parametrosRequeridos[]) throws ErrorResponseException {
        for (String parametro: parametrosRequeridos){
            if(dict.get(parametro).isEmpty() || dict.get(parametro) == null){
                throw new ErrorResponseException(EX_VALORES_VACIOS,HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
            }
        }
        return true;
    }
    public static final boolean IsValidateId(Map<String, String> dict, String nombre_parametro_id) throws ErrorResponseException{
        try {
            Integer.parseInt(dict.get(nombre_parametro_id));
            return true;
        } catch (NumberFormatException e) {
            throw new ErrorResponseException(EX_ERROR_FORMAT_NUMBER_ID,HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        }
    }
}
