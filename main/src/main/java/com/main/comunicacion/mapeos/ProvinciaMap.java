package com.main.comunicacion.mapeos;

import java.text.Normalizer;

import org.springframework.stereotype.Component;
import com.main.comunicacion.openD.DTOs.ProvinciaDTO;
import com.main.modelo.entidades.Provincia;

//Mapeos de provincia de solicitudes externas OPEN DATA
@Component
public class ProvinciaMap {

    //Mapea de provinciaDTO a la entidad
    public static Provincia toEntity(ProvinciaDTO provinciaDTO) {
        Provincia provincia = new Provincia();

        String nombre = provinciaDTO.getName();
        if (nombre.contains("/")) {
            nombre = nombre.split("/")[1].trim();
        
            nombre = Normalizer.normalize(nombre, Normalizer.Form.NFD); 
            nombre = nombre.replaceAll("\\p{M}", ""); 
        
        }

        provincia.setNombre(nombre);
        provincia.setLatitud(provinciaDTO.getLat());
        provincia.setLongitud(provinciaDTO.getLon());

        return provincia;
    }
}
