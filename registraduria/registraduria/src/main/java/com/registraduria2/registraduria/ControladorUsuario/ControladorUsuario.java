package com.registraduria2.registraduria.ControladorUsuario;
import com.registraduria2.registraduria.Modelos.Rol;
import com.registraduria2.registraduria.Modelos.usuario;
import com.registraduria2.registraduria.Repositorios.RepositorioRol;
import com.registraduria2.registraduria.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@CrossOrigin
@RestController
@RequestMapping("/usuarios")


public class ControladorUsuario {
    @Autowired
    private RepositorioUsuario miRepositorioUsuario;
    @Autowired
    private RepositorioRol miRepositrioRol;
    @GetMapping("")
    public List<usuario> index(){
        return this.miRepositorioUsuario.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public usuario create(@RequestBody usuario infoUsuario){
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.miRepositorioUsuario.save(infoUsuario);
    }
    @GetMapping("{id}")
    public usuario show(@PathVariable String id){
        usuario usuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        return usuarioActual;
    }
    @PutMapping("{id}")
    public usuario update(@PathVariable String id,@RequestBody usuario
            infoUsuario){
        usuario usuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        Rol rolActual=this.miRepositrioRol
                .findById(id)
                .orElse(null);

        if (usuarioActual!=null && rolActual!=null){
            usuarioActual.setRol(rolActual);

            return this.miRepositorioUsuario.save(usuarioActual);
        }else{
            return null;
        }
    }

    @PutMapping("{id}/rol/{id_rol}")
    public usuario AsignarRolUsuario(@PathVariable String id,@PathVariable String id_rol){

        usuario usuarioActual=this.miRepositorioUsuario.findById(id).orElseThrow(RuntimeException::new);
        Rol rolActual=this.miRepositrioRol.findById(id_rol).orElseThrow(RuntimeException::new);
        usuarioActual.setRol(rolActual);
        return this.miRepositorioUsuario.save(usuarioActual);

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        usuario usuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            this.miRepositorioUsuario.delete(usuarioActual);
        }
    }
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
