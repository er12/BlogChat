package modulo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Francis Cáceres on 12/6/2016.
 */
@Entity
@NamedQueries({@NamedQuery(name = "Usuario.findAllByUsername", query = "SELECT a FROM Usuario a WHERE a.username like :username")})
public class Usuario implements Serializable{
    @Id
    private String username;
    private String nombre;
    private String password;
    private boolean Administrador;
    private boolean autor;
    @OneToMany(  mappedBy = "autor" ,cascade = CascadeType.REMOVE)
    private List<Articulo> articulos;
    @OneToMany(  mappedBy = "autor" ,cascade = CascadeType.REMOVE)
    private List<Comentario> comentarios;
    @OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER,orphanRemoval = true,cascade = CascadeType.REMOVE)
    private List<LikeA> likesA;
    @OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER,orphanRemoval = true,cascade = CascadeType.REMOVE)
    private List<LikeC> likesC;

    public Usuario(){

    }

    public Usuario(String username, String nombre, String password, boolean administrador) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        Administrador = administrador;
        this.autor = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrador() {
        return Administrador;
    }

    public void setAdministrador(boolean administrador) {
        Administrador = administrador;
    }

    public boolean isAutor() {
        return autor;
    }

    public void setAutor(boolean autor) {
        this.autor = autor;
    }

    public List<LikeA> getLikesA() {
        return likesA;
    }

    public void setLikesA(List<LikeA> likesA) {
        this.likesA = likesA;
    }

    public List<LikeC> getLikesC() {
        return likesC;
    }

    public void setLikesC(List<LikeC> likesC) {
        this.likesC = likesC;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void addArticulo(Articulo art) {
        this.articulos.add(art);
        if (art.getAutor() != this) {
            art.setAutor(this);
        }
    }
    public void addLikeA(LikeA la) {
        this.likesA.add(la);
        if (la.getUsuario() != this) {
            la.setUsuario(this);
        }
    }

    public void addLikeC(LikeC la) {
        this.likesC.add(la);
        if (la.getUsuario() != this) {
            la.setUsuario(this);
        }
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
