package modulo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ernesto on 04-Jul-16.
 */
@Entity
public class LikeC implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean isLike;
    @ManyToOne
    private Comentario Coment;
    @ManyToOne
    private Usuario usuario;

    public LikeC(){}

    public LikeC(Boolean isLike, Comentario coment, Usuario usuario) {
        this.isLike = isLike;
        Coment = coment;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean like) {
        this.isLike = like;
    }

    public Comentario getComent() {
        return Coment;
    }

    public void setComent(Comentario coment) {
        Coment = coment;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
