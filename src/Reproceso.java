public class Reproceso {

    private int cantidad = 0;
    private boolean fin = false;

    public synchronized String hayAlgo(){
        if(fin) return "FIN";

        if(cantidad > 0){
            return "SI";
        }

        return "NO";
    }

    public synchronized void cogerProducto(){
        cantidad--;

        System.out.println("Sale 1 producto del buzon de reproceso.");
    }

    public synchronized void agregarReproceso(String mensaje){
        if(!mensaje.equals("FIN")){
            cantidad++;
            System.out.println("Entra 1 producto del buzon de reproceso.");
        } else {
            fin = true;

            System.out.println("FIN LLEGA A BUZON DE REPROCESO.");
        }
    }
}
