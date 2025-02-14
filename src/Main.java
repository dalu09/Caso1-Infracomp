import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese numero de operarios: ");
        int nOperarios = scanner.nextInt();
        System.out.print("Numero de productos a producir: ");
        int nProductos = scanner.nextInt();
        System.out.print("Tamaño revision: ");
        int nRevision = scanner.nextInt();

        scanner.close();

        Reproceso reproceso = new Reproceso();
        Revision revision = new Revision(nRevision);
        Deposito deposito = new Deposito();

        Productor.agregarReproceso(reproceso);
        Productor.agregarRevision(revision);

        Calidad.agregarReproceso(reproceso);
        Calidad.agregarRevision(revision);
        Calidad.agregarDeposito(deposito);
        Calidad.agregarLimiteFallos((int) (Math.floor(nProductos * 0.1)));
        Calidad.setTotalAProcesar(nProductos);

        for (int i = 0; i < nOperarios; i++) {
            Productor prod = new Productor(i);
            Calidad qual = new Calidad(i);

            prod.start();
            qual.start();
        }
    }
}