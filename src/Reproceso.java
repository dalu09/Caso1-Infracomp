import java.util.ArrayList;

public class Reproceso {

    private int cantidad = 0;
    private boolean fin = false;
    private ArrayList<Integer> lista = new ArrayList<>();

    public synchronized String hayAlgo(){
        if(fin) return "FIN";

        if(cantidad > 0){
            return "SI";
        }

        return "NO";
    }

    public synchronized int cogerProducto(){
        if(cantidad <= 0 || lista.isEmpty()) return -1;

        cantidad--;

        int res = lista.getFirst();

        System.out.println("Sale el producto #" + res + " del buzon de reproceso.");

        lista.removeFirst();

        return res;
    }

    public synchronized void agregarReproceso(String mensaje, int idProductoEntrante){
        if(!mensaje.equals("FIN")){
            cantidad++;
            lista.add(idProductoEntrante);
            System.out.println("Entra el producto #" + idProductoEntrante + " del buzon de reproceso.");
        } else {
            fin = true;

            System.out.println("FIN LLEGA A BUZON DE REPROCESO.");
        }
    }
}
