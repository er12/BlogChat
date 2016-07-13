package servicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Francis Cáceres on 15/6/2016.
 */
public class GestionDB<T> {
    private static EntityManagerFactory emf;
    private Class<T> claseEntidad;


    public GestionDB(Class<T> claseEntidad) {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
        }
        this.claseEntidad = claseEntidad;

    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    /**
     *
     * @param entidad
     */
    public void crear(T entidad){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(entidad);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param entidad
     */
    public void editar(T entidad){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(entidad);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param entidadId
     */
    public void eliminar(Object  entidadId){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {

            T entidad = em.find(claseEntidad, entidadId);
            em.remove(em.contains(entidad)? entidad : em.merge(entidad));
            //System.out.println();
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        EntityManager em = getEntityManager();
        try{
            return em.find(claseEntidad, id);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;//Agregado por que me daba error sin un return
    }

    /**
     *
     * @return
     */
    public List<T> findAll(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(claseEntidad);
            criteriaQuery.select(criteriaQuery.from(claseEntidad));
            return em.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
        }
        return null;//Agregado por que me daba error sin un return
    }
}
