/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion_atm;

import java.util.LinkedList;

/**
 *
 * @author Naomi
 */
public class Simulacion_ATM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList fila = new LinkedList();
        Cliente anterior = null;
        double reloj = 0;
        boolean eliminar = false;
        for (int i = 0; i < 5; i++) {
            boolean espera = false;

            if (!fila.isEmpty()) {
                espera = true;
                anterior = (Cliente) fila.getLast();
            }
            fila.add(new Cliente(Math.random(), Math.random()));
            reloj = ((Cliente) fila.get(fila.size() - 1)).calcularReloj(reloj);

            while (fila.size() >= 2 && reloj > ((Cliente) fila.getFirst()).gettFinServicio()) {
                fila.poll();
            }

            espera = false;
            if (fila.size() > 1) {
                espera = true;
            }
            ((Cliente) fila.get(fila.size() - 1)).setEspero(espera);
            ((Cliente) fila.get(fila.size() - 1)).setCont(fila.size() - 1);
            try {
                ((Cliente) fila.get(fila.size() - 1)).calcularTiempoInicioServicio(((Cliente) fila.get((fila.size() - 1) - 1)).gettFinServicio());
            } catch (Exception e) {
                ((Cliente) fila.get(fila.size() - 1)).calcularTiempoInicioServicio(0);
            }
            ((Cliente) fila.get(fila.size() - 1)).calcularTiempoFinServicio();
            ((Cliente) fila.get(fila.size() - 1)).calcularTiempoPermanencia();
            Cliente actual = (Cliente) fila.get(fila.size() - 1);
            System.out.println(i + 1 + "   " + actual.getrLlegada() + "   " + actual.gettLlegada() + "   " + actual.getReloj() + "   " + actual.isEspero() + "   " + actual.getCont() + "   " + actual.gettInicioServicio() + "   " + actual.getrServicio() + "   " + actual.gettServicio() + "   " + actual.gettFinServicio() + "   " + actual.gettPermanecia());
        }
    }

}
