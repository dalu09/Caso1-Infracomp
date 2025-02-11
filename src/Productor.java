class Productor extends Thread {

    private int id;
    private static Reproceso reproceso;
    private static Revision revision;

    private static boolean fin = false;

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
                    System.out.println("Productor #" + ((int) id) + " crea producto.");
                    pasarProducto();
                }
                case "SI" -> {
                    System.out.println("Productor #" + ((int) id) + " reprocesa producto.");
                    reproceso.cogerProducto();
                    pasarProducto();
                }
                case "FIN" -> {
                    fin = true;
                }
            }
        }

        System.out.println("FIN PRODUCTOR #" + id);
    }

    private synchronized void pasarProducto(){
        if(revision.hayEspacio()){
            revision.crearNuevo();
        } else {
            revision.esperarEspacio();
        }
    }

    public static void agregarReproceso(Reproceso reprocesoInput){
        reproceso = reprocesoInput;
    }

    public static void agregarRevision(Revision revisionInput){
        revision = revisionInput;
    }
}
