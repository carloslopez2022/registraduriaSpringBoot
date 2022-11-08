package com.registraduria2.registraduria.Modelos;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()

public class RolPermiso {
    @Id
     private String _id;
    @DBRef
    private Rol _idRol;
    @DBRef
    private permiso _idPermiso;

    public RolPermiso() {
        this._idRol = _idRol;
        this._idPermiso = _idPermiso;
    }

       public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Rol get_idRol() {
        return _idRol;
    }

    public void set_idRol(Rol _idRol) {
        this._idRol = _idRol;
    }

    public permiso get_idPermiso() {
        return _idPermiso;
    }

    public void set_idPermiso(permiso _idPermiso) {
        this._idPermiso = _idPermiso;
    }
}


