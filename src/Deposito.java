public class Deposito {

    private int cantidad = 0;

    public synchronized void agregarEnDeposito(){
        cantidad++;

        System.out.println("Producto #" + (int)(cantidad) + " agregado al deposito.");
    }

    public synchronized int cantidadDeposito(){
        return cantidad;
    }
}
