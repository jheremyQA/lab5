public class Nodo extends Thread {
    private final String nombre;
    private boolean estaActivo = true;
    private int cargaTrabajo = 50;
    private int velocidadProceso = 5;

    public Nodo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            if (!estaActivo) {
                System.out.println(nombre + " está inactivo. Esperando recuperación...");
                esperar(3000);
                continue;
            }

            System.out.println(nombre + " procesando con carga: " + cargaTrabajo + "%");
            cargaTrabajo += (int)(Math.random() * 10 - 5);
            cargaTrabajo = Math.max(10, Math.min(cargaTrabajo, 100));

            if (Math.random() < 0.05) {
                System.out.println("⚠️  " + nombre + " ha fallado.");
                estaActivo = false;
            }

            esperar(velocidadProceso * 100);
        }
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(nombre + " fue interrumpido.");
        }
    }

    public void recuperar() {
        estaActivo = true;
        System.out.println("✅ " + nombre + " se ha recuperado.");
    }

    public boolean estaActivo() {
        return estaActivo;
    }

    public int obtenerCargaTrabajo() {
        return cargaTrabajo;
    }

    public void optimizar() {
        if (cargaTrabajo > 80) {
            System.out.println("🔄 " + nombre + " optimiza carga (↓ velocidad).");
            velocidadProceso += 1;
        } else if (cargaTrabajo < 30) {
            System.out.println("⚡ " + nombre + " acelera procesamiento.");
            velocidadProceso = Math.max(1, velocidadProceso - 1);
        }
    }

    public String obtenerNombre() {
        return nombre;
    }
}
