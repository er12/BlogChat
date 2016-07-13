package servicios;

import modulo.LikeC;
import modulo.Usuario;

/**
 * Created by Ernesto on 19-Jun-16.
 */
public class LikeCQueries extends GestionDB<LikeC>{
    private static LikeCQueries instancia;

    public LikeCQueries() {
        super(LikeC.class);
    }

    public static LikeCQueries getInstancia() {
        if(instancia==null){
            instancia = new LikeCQueries();
        }
         return instancia;
    }
}
