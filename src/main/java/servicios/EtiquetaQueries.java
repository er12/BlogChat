package servicios;

import modulo.Articulo;
import modulo.Etiqueta;

/**
 * Created by Ernesto on 19-Jun-16.
 */
public class EtiquetaQueries extends GestionDB<Etiqueta> {

    private static EtiquetaQueries instancia;

    public EtiquetaQueries() {
        super(Etiqueta.class);
    }

    public static EtiquetaQueries getInstancia() {
        if(instancia==null){
            instancia = new EtiquetaQueries();
        }
        return instancia;
    }


}
