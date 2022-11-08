package com.registraduria2.registraduria.ControladorUsuario;
import com.registraduria2.registraduria.Modelos.Rol;
import com.registraduria2.registraduria.Repositorios.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Roles")


public class ControladorRol {
    @Autowired
    private RepositorioRol miRepositioRol;
    @GetMapping("")
    public List<Rol> index(){
        return this.miRepositioRol.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol create(@RequestBody Rol infoRol){
        return this.miRepositioRol.save(infoRol);
    }

    @GetMapping("{id}")
    public Rol show(@PathVariable String id){
        Rol RolActual=this.miRepositioRol
                .findById(id)
                .orElse(null);
        return RolActual;
    }
    @PutMapping("{id}")
    public Rol update(@PathVariable String id,@RequestBody Rol
            infoRol){
        Rol RolActual=this.miRepositioRol
                .findById(id)
                .orElse(null);
        if (RolActual!=null){
            RolActual.setNombre(infoRol.getNombre());
            RolActual.setDescripcion(infoRol.getDescripcion());
            return this.miRepositioRol.save(RolActual);
        }else{
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Rol RolActual=this.miRepositioRol
                .findById(id)
                .orElse(null);
        if (RolActual!=null){
            this.miRepositioRol.delete(RolActual);
        }
    }

}



