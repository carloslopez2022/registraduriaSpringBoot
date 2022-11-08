package com.registraduria2.registraduria.ControladorUsuario;


import com.registraduria2.registraduria.Modelos.permiso;
import com.registraduria2.registraduria.Repositorios.RepositorioPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Permisos")


public class ControladorPermiso {
    @Autowired
    private RepositorioPermiso miRepositioPermiso;
    @GetMapping("")
    public List<permiso> index(){
        return this.miRepositioPermiso.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public permiso create(@RequestBody permiso infopermiso){
        return this.miRepositioPermiso.save(infopermiso);
    }

    @GetMapping("{id}")
    public permiso show(@PathVariable String id){
        permiso permisoActual=this.miRepositioPermiso
                .findById(id)
                .orElse(null);
        return permisoActual;
    }
    @PutMapping("{id}")
    public permiso update(@PathVariable String id, @RequestBody permiso
            infopermiso){
        permiso permisoActual=this.miRepositioPermiso
                .findById(id)
                .orElse(null);
        if (permisoActual!=null){
            permisoActual.setUrl(infopermiso.getUrl());
            permisoActual.setMetodo(infopermiso.getMetodo());
            return this.miRepositioPermiso.save(permisoActual);
        }else{
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        permiso permisoActual=this.miRepositioPermiso
                .findById(id)
                .orElse(null);
        if (permisoActual!=null){
            this.miRepositioPermiso.delete(permisoActual);
        }
    }


}
