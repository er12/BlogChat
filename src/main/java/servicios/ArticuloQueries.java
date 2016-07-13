package servicios;

import modulo.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francis Cáceres on 15/6/2016.
 */
public class ArticuloQueries extends GestionDB<Articulo> {

    private static ArticuloQueries instancia;

    public ArticuloQueries() {
        super(Articulo.class);
    }

    public static ArticuloQueries getInstancia() {
        if(instancia==null){
            instancia = new ArticuloQueries();
        }
        return instancia;
    }


    public void crearEsp(Long Id, List<Etiqueta> le){

        EntityManager em = getEntityManager();


        em.getTransaction().begin();

        try {

//            em.getTransaction().commit();
            Articulo art = em.find(Articulo.class,Id);

            for (Etiqueta it : le) {
                Etiqueta e = em.find(Etiqueta.class,it.getId());
                e.setArticulo(art);
                art.addEtiqueta(e);


                //em.merge(e);
            }
            em.getTransaction().commit();

        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }

    public List<Articulo> findAllSorted(){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Articulo.findAllSorted");
        List<Articulo> lista = query.getResultList();
        return lista;
    }

    public List<Articulo> findAllByTagsSorted(String tag){
        List<Articulo> la = new ArrayList<>();
        List<Articulo> dummy = ArticuloQueries.getInstancia().findAll();

        for(Articulo a : dummy) {

            for(Etiqueta e : a.getListaEtiqueta())
            {
                if(e.getEtiqueta().equals(tag))
                {
                    la.add(a);
                    break;
                }
            }
        }
        return la;
    }

    public void delete(Long id){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Articulo entidad = em.find(Articulo.class, id);
            em.remove(entidad);
            //em.clear();
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }

    public void unlinkLike(Long idA, int idL)
    {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Articulo articulo = em.find(Articulo.class, idA);
            //List<LikeA> LA = articulo.getLikes();
/*
            for (Iterator<LikeA> iterator = articulo.getLikes().iterator(); iterator.hasNext(); ) {
                LikeA child = iterator.next();
                em.remove( child );
                iterator.remove();
            }
            em.merge( articulo );*/
            for(LikeA la: articulo.getLikes())
            {
                if(la.getId() == idL)
                {
                    articulo.getLikes().clear();
                    LikeAQueries.getInstancia().eliminar(la.getId());
                    System.out.println(articulo.getLikes().remove(la));
                    break;
                }

            }
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }
}
