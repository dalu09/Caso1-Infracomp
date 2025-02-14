import java.util.ArrayList;

public class Revision {

    private int limite;
    private int cantidad = 0;
    private ArrayList<Integer> lista = new ArrayList<>();

    Revision(int limite){
        this.limite = limite;
    }

    public synchronized boolean hayEspacio(){
        return (limite - cantidad) > 0;
    }

    public synchronized void crearNuevo(int idP){
        while(cantidad >= limite){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        cantidad++;
        lista.add(idP);

        System.out.println("Buzon de revision recibe producto #" + idP + ".");

        notifyAll();
    }

    public synchronized boolean hayEnRevision(){
        return cantidad > 0;
    }

    public synchronized int cogerRevision(){
        while(cantidad <= 0 || lista.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int id = lista.removeFirst();
        cantidad--;

        System.out.println("Buzon de revision cede producto #" + id + ".");

        notifyAll();

        return id;
    }

    public synchronized void esperarEspacio() {
        while(!hayEspacio()){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void checkFin(){
        notifyAll();
    }

}
