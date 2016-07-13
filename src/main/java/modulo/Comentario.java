package modulo;

import servicios.LikeAQueries;
import servicios.LikeCQueries;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Francis Cáceres on 12/6/2016.
 */
@Entity
public class Comentario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String comentario;
    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Articulo articulo;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Coment",cascade = CascadeType.REMOVE)
    private List<LikeC> likes;

    public Comentario() {

    }

    public Comentario(String comentario, Usuario autor) {
        this.comentario = comentario;
        this.autor = autor;
    }

    public Comentario(String comentario, Usuario autor, Articulo articulo, List<LikeC> likes) {
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public List<LikeC> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeC> likes) {
        this.likes = likes;
    }

    public void addLikeC(LikeC like) {
        this.likes.add(like);
        if (like.getComent() != this) {
            like.setComent(this);
        }
    }

    public String isLikeUsuario( Usuario u )
    {
        for(LikeC lc : likes)
        {
            if(lc.getUsuario().getUsername().equals(u.getUsername()))
            {
                if(lc.getIsLike())
                {
                    return "Like";

                }
                else
                    return "disLike";

            }
        }
        return "noLike";

    }
    public int getGoodLikes()
    {
        int sum= 0 ;
        for(LikeC la : likes)
        {
            if(la.getIsLike())
                sum++;
        }
        return sum;
    }


}
