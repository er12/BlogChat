package modulo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Francis Cáceres on 12/6/2016.
 */

@Entity
public
class Etiqueta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String etiqueta;
    @ManyToOne
    private Articulo articulo;

    public Etiqueta(){}

    public Etiqueta(String e){

        this.etiqueta = e;
    }

    public Etiqueta(String etiqueta, Articulo articulo) {
        this.etiqueta = etiqueta;
        this.articulo = articulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;

        if (!articulo.getListaEtiqueta().contains(this)) {
            articulo.getListaEtiqueta().add(this);
        }
    }
}
