package modulo;

import servicios.ArticuloQueries;
import servicios.LikeAQueries;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by Francis Cáceres on 12/6/2016.
 */

@Entity
@NamedQueries(
        {@NamedQuery(name = "Articulo.findAllSorted", query = "SELECT a FROM Articulo a order by a.fecha desc"),
                @NamedQuery(name = "Articulo.findAllByTagsSorted", query = "SELECT a FROM Articulo a where :tag member of a.listaEtiqueta order by a.fecha desc")
        })

public class Articulo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 1000)
    private String titulo;
    @Column(length = 8000)
    private String cuerpo;
    @ManyToOne
    private Usuario autor;
    @OrderBy
    private Date fecha;
    @OneToMany(mappedBy = "articulo",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Comentario> listaComentario;
    @OneToMany(mappedBy = "articulo",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Etiqueta> listaEtiqueta;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "articulo",cascade = CascadeType.REMOVE)
    private List<LikeA> likes;

    public Articulo(){

    }

    public Articulo(String titulo, String cuerpo, Usuario autor,  List<Comentario> listaComentario, List<Etiqueta> listaEtiqueta, List<LikeA> likes) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        //this.fecha = fecha;
        this.listaComentario = listaComentario;
        this.listaEtiqueta = listaEtiqueta;

        this.likes = likes;

        fecha = new Date();
        java.sql.Date fechas = new java.sql.Date(fecha.getTime());

        this.setFecha(fechas);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Comentario> getListaComentario() {
        return listaComentario;
    }

    public void setListaComentario(List<Comentario> listaComentario) {
        this.listaComentario = listaComentario;
    }

    public List<Etiqueta> getListaEtiqueta() {
        return listaEtiqueta;
    }

    public void setListaEtiqueta(List<Etiqueta> listaEtiqueta) {
        this.listaEtiqueta = listaEtiqueta;
    }

    public List<LikeA> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeA> likes) {
        this.likes = likes;
    }

    public void addLikeA(LikeA like) {
        this.likes.add(like);
        if (like.getArticulo() != this) {
            like.setArticulo(this);
            //ArticuloQueries.getInstancia().editar(this);
        }
    }

    public void addEtiqueta(Etiqueta et) {
        this.listaEtiqueta.add(et);
        if (et.getArticulo() != this) {
            et.setArticulo(this);
        }
    }
    public int getGoodLikes()
    {
        int sum= 0 ;
        for(LikeA la : likes)
        {
            if(la.getIsLike())
                sum++;
        }
        return sum;
    }
    public int getTHeLike(Usuario usuario)
    {
        for(LikeA la : likes) {
            if( la.getUsuario().getUsername().equals(usuario.getUsername())) {
                int dummy =la.getId();
                likes.clear();
                return dummy;
            }
        }

        return -1;

    }


}
