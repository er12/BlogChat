package servicios;

import modulo.Articulo;
import modulo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Ernesto on 19-Jun-16.
 */
public class UsuarioQueries extends GestionDB<Usuario>{
    private static UsuarioQueries instancia;

    public UsuarioQueries() {
        super(Usuario.class);
    }

    public static UsuarioQueries getInstancia() {
        if(instancia==null){
            instancia = new UsuarioQueries ();
        }
        return instancia;
    }
    /*
    public List<Usuario> findAllByNombre(String nombre){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Usuario.findAllByName");
        query.setParameter("titulo", nombre+"%");
        List<Usuario> lista = query.getResultList();
        return lista;
    }*/
}
