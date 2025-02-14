import java.util.ArrayList;

public class Deposito {

    private int cantidad = 0;
    private ArrayList<Integer> lista = new ArrayList<>();

    public synchronized void agregarEnDeposito(int idP){
        cantidad++;
        lista.add(idP);

        System.out.println("Producto #" + (int)(idP) + " agregado al deposito.");
    }

    public synchronized int cantidadDeposito(){
        return cantidad;
    }
}
