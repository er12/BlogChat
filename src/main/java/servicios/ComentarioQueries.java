package servicios;

import modulo.*;

import javax.persistence.EntityManager;

/**
 * Created by Ernesto on 19-Jun-16.
 */
public class ComentarioQueries extends GestionDB<Comentario> {
    private static ComentarioQueries instancia;

    public ComentarioQueries() {
        super(Comentario.class);
    }

    public static ComentarioQueries getInstancia() {
        if(instancia==null){
            instancia = new ComentarioQueries();
        }
        return instancia;
    }

    public void flush(int Id, Usuario usuario)
    {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            int idLike=0;
            Comentario coment = em.find(Comentario.class,Id);
            for(LikeC la : coment.getLikes())
            {
                if( la.getUsuario().getUsername().equals(usuario.getUsername()))
                {
                    LikeCQueries.getInstancia().eliminar(la.getId());
                    usuario.getLikesC().remove(la);
                    //likes.remove(la);
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
