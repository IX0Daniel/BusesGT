
import dao.SucursalDAO;
import java.util.List;
import model.Sucursal;
import servicio.SucursalService;

/**
 *
 * @author dz
 */
public class Prueba1 {
    
    
     public void main(String[] args) {
        SucursalService service = new SucursalService();

        

        try {

            System.out.println("=== LISTAR SUCURSALES ===");

            for (Sucursal sucursal : service.listar()) {

                System.out.println(
                    sucursal.getCodigoSucursal()
                    + " | "
                    + sucursal.getNombre()
                );
            }

       


        } catch (Exception e) {

            System.out.println("Ocurrió un error:");
            e.printStackTrace();
        }
    }
    
}
