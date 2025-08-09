public class SistemaDistribuido {
    public static void main(String[] args) {
        Nodo nodoA = new Nodo("Nodo-A");
        Nodo nodoB = new Nodo("Nodo-B");
        Nodo nodoC = new Nodo("Nodo-C");

        nodoA.start();
        nodoB.start();
        nodoC.start();

        while (true) {
            esperar(3000);

            for (Nodo nodo : new Nodo[]{nodoA, nodoB, nodoC}) {
                if (!nodo.estaActivo()) {
                    nodo.recuperar();
                } else {
                    nodo.optimizar();
                }
            }
        }
    }

    private static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println("Sistema interrumpido.");
        }
    }
}
