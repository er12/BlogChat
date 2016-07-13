package servicios;

import modulo.LikeA;

/**
 * Created by Ernesto on 19-Jun-16.
 */
public class LikeAQueries extends GestionDB<LikeA>{
    private static LikeAQueries instancia;

    public LikeAQueries() {
        super(LikeA.class);
    }

    public static LikeAQueries getInstancia() {
        if(instancia==null){
            instancia = new LikeAQueries();
        }
        return instancia;
    }
}
