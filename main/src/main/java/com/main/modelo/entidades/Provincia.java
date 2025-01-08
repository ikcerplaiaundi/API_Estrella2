package com.main.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provincias")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String nombre;
    private String latitud;
    private String longitud;

    @OneToMany(mappedBy = "provincia")
    private List<Ciudad> ciudades = new ArrayList<Ciudad>();
    //https://nominatim.openstreetmap.org/search?q=Buenos+Aires+Province&format=json


    public String toSringProvincia() {
        StringBuilder sb = new StringBuilder();
        sb.append("Provincia{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", latitud=").append(latitud);
        sb.append(", longitud=").append(latitud);
        sb.append(", ciudades=[");
        
        for (Ciudad ciudad : ciudades) {
            sb.append(ciudad.getNombre()).append(", ");
        }
        if (!ciudades.isEmpty()) {
            sb.setLength(sb.length() - 2); 
        }
        sb.append(']');
        sb.append('}');
        return sb.toString();
    }
}
