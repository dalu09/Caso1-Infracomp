public class Revision {

    private int limite;
    private int cantidad = 0;

    Revision(int limite){
        this.limite = limite;
    }

    public synchronized boolean hayEspacio(){
        return (limite - cantidad) > 0;
    }

    public synchronized void crearNuevo(){
        cantidad++;

        System.out.println("Buzon de revision recibe producto.");
    }

    public synchronized boolean hayEnRevision(){
        return cantidad > 0;
    }

    public synchronized void cogerRevision(){
        cantidad--;
        if(limite - cantidad >= 1){
            notifyAll();
        }

        System.out.println("Buzon de revision cede producto.");
    }

    public synchronized void esperarEspacio() {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
