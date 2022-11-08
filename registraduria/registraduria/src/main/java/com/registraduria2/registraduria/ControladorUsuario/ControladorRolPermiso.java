package com.registraduria2.registraduria.ControladorUsuario;
import com.registraduria2.registraduria.Modelos.Rol;
import com.registraduria2.registraduria.Modelos.RolPermiso;
import com.registraduria2.registraduria.Modelos.permiso;
import com.registraduria2.registraduria.Repositorios.RepositorioPermiso;
import com.registraduria2.registraduria.Repositorios.RepositorioRol;
import com.registraduria2.registraduria.Repositorios.RepositorioRolPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rol-permisos")

public class ControladorRolPermiso {
    @Autowired
    private RepositorioPermiso miRepoPermiso;

    @Autowired
    private RepositorioRol miRepoRol;
    @Autowired
    private RepositorioRolPermiso miRepoRolPermiso;

    @GetMapping("")
    public List<RolPermiso> index(){
        return this.miRepoRolPermiso.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/(id_rol)/permiso(id_permiso)")
    public RolPermiso create(@PathVariable String id_rol,@PathVariable String id_permiso) {
        RolPermiso elRolPermiso=new RolPermiso();
        Rol elRol = this.miRepoRol.findById(id_rol).get();
        permiso elPermiso = this.miRepoPermiso.findById(id_permiso).get();
        if (elRol != null && elPermiso != null) {
            elRolPermiso.set_idPermiso(elPermiso);
            elRolPermiso.set_idRol(elRol);
            return this.miRepoRolPermiso.save(elRolPermiso);
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public RolPermiso show(@PathVariable String id){
        RolPermiso RolPermisoActual=this.miRepoRolPermiso
                .findById(id)
                .orElse(null);
        return RolPermisoActual;
    }
    @PutMapping("{id}")
    public RolPermiso update(@PathVariable String id, @RequestBody RolPermiso
            infoRolPermiso){
        RolPermiso RolPermisoActual=this.miRepoRolPermiso
                .findById(id)
                .orElse(null);
        if (RolPermisoActual!=null){
            RolPermisoActual.set_idRol(infoRolPermiso.get_idRol());
            RolPermisoActual.set_idPermiso(infoRolPermiso.get_idPermiso());
            return this.miRepoRolPermiso.save(RolPermisoActual);
        }else{
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        RolPermiso RolPermisoActual=this.miRepoRolPermiso
                .findById(id)
                .orElse(null);
        if (RolPermisoActual!=null){
            this.miRepoRolPermiso.delete(RolPermisoActual);
        }
    }

}
