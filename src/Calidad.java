import java.util.Random;

public class Calidad extends Thread{

    private int id;

    private static Reproceso reproceso;
    private static Revision revision;
    private static Deposito deposito;

    private static int contadorFallos = 0;
    private static int maximoAFallar;

    private static int totalAProcesar;
    private static boolean fin = false;
    private static boolean yaSeEnvioFin = false;

    Calidad(int id){
        this.id = id;
    }

    @Override
    public void run() {
        super.run();

        while(!(fin && !revision.hayEnRevision())){

            if(revision.hayEnRevision()){
                int idP = revision.cogerRevision();

                if(idP == -1) continue;

                if(controlCalidad()){
                    if(deposito.cantidadDeposito() >= totalAProcesar && !yaSeEnvioFin){
                        fin = true;
                        yaSeEnvioFin = true;
                        System.out.println("FIN SE ENVIA A BUZON DE REPROCESO.");
                        reproceso.agregarReproceso("FIN", idP);
                    } else {
                        deposito.agregarEnDeposito(idP);
                    }
                } else {
                    contadorFallos++;
                    reproceso.agregarReproceso("", idP);
                }
            }
            Thread.yield();
        }

        System.out.println("FIN INSPECTOR DE CALIDAD #" + id + ".");
    }

    private boolean controlCalidad(){
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        return !(randomNumber % 7 == 0 && maximoAFallar > contadorFallos);
    }

    public static void agregarReproceso(Reproceso reprocesoInput){
        reproceso = reprocesoInput;
    }

    public static void agregarRevision(Revision revisionInput){
        revision = revisionInput;
    }

    public static void agregarDeposito(Deposito depositoInput){
        deposito = depositoInput;
    }

    public static void agregarLimiteFallos(int limiteFallosInput){
        maximoAFallar = limiteFallosInput;
    }

    public static void setTotalAProcesar(int totalAProcesarInput){
        totalAProcesar = totalAProcesarInput;
    }
}
