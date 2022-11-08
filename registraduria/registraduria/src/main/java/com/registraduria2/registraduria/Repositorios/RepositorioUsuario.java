package com.registraduria2.registraduria.Repositorios;
import com.registraduria2.registraduria.Modelos.usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioUsuario extends MongoRepository<usuario,String>{
    @Query("{'correo':?0}")
    public usuario getusuarioBycorreo(String correo);
}

