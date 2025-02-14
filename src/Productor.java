class Productor extends Thread {

    private int id;
    private static Reproceso reproceso;
    private static Revision revision;

    private static boolean fin = false;
    private static int idCounterProductos = 0;

    Productor(int id){
        this.id = id;
    }

    @Override
    public void run() {
        super.run();

        while(!fin) {
            String res = reproceso.hayAlgo();

            switch (res) {
                case "NO" -> { // no hay nada en reproceso -> crea
                    int cId = getContador();
                    System.out.println("Productor #" + ((int) id) + " crea producto #" + cId + ".");
                    pasarProducto(cId);
                }
                case "SI" -> {
                    int idRecepcion = reproceso.cogerProducto();
                    if(idRecepcion == -1) continue;

                    System.out.println("Productor #" + ((int) id) + " reprocesa producto #" + idRecepcion + ".");
                    pasarProducto(idRecepcion);
                }
                case "FIN" -> {
                    fin = true;
                    break;
                }
            }
        }

        System.out.println("FIN PRODUCTOR #" + id);

        revision.checkFin();
    }

    private synchronized int getContador(){
        idCounterProductos++;
        return idCounterProductos;
    }

    private synchronized void pasarProducto(int idP){
        while (!revision.hayEspacio()) {
            if(fin) return;
            revision.esperarEspacio();
        }

        revision.crearNuevo(idP);
    }

    public static void agregarReproceso(Reproceso reprocesoInput){
        reproceso = reprocesoInput;
    }

    public static void agregarRevision(Revision revisionInput){
        revision = revisionInput;
    }
}
