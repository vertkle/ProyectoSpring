package com.codigo.proyectospringcodigo.Utils;

import org.springframework.beans.factory.annotation.Value;

public class Constantes {
    public static final String EX_ERROR_PARAMETROS = "Error en parámetros, verificar pertición";
    public static final String EX_VALORES_VACIOS = "El valor viene vacío, es requerido para continuar";
    public static final String EX_NOT_FOUND_RECURSO = "No existe el recurso";
    public static final String USUARIO_ENCONTRADO = "Este usuario ya ha sido registrado";
    public static final String EX_ERROR_FORMAT_NUMBER_ID = "El valor no es numérico";
    public static final Integer ESTADO_ACTIVO = 1;
    public static final Integer ESTADO_INACTIVO = 2;
    public static final String ALGO_SALIO_MAL = "Ocurrió un error";
    
    public static final String SECRET_TOKEN = "secret_token_klevert";

}
