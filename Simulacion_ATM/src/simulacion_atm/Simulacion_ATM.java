/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion_atm;

import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Naomi
 */
public class Simulacion_ATM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana=new JFrame("Simulacion");
        ventana.setSize(900,600);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelPrincipal=new JPanel();
        ventana.setResizable(false);
        
        String []Columnas={"Cliente","r","Tiempo entre llegada","Reloj","E?","Contador","Tiempo"
                + " Inicio de Servicio","r","Tiempo entre Servicio","Tiempo fin de Servicio","Tiempo de Permanencia"
                + " en el sistema"};
        String [][]Data =new String[5][11];
        
        
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
            
            Data[i][0]=(i+1)+"";
            Data[i][1]=actual.getrLlegada()+"";
            Data[i][2]=actual.gettLlegada()+"";
            Data[i][3]=actual.getReloj()+"";
            Data[i][4]=actual.isEspero()+"";
            Data[i][5]=actual.getCont()+"";
            Data[i][6]=actual.gettInicioServicio()+"";
            Data[i][7]=actual.getrServicio()+"";
            Data[i][8]=actual.gettServicio()+"";
            Data[i][9]=actual.gettFinServicio()+"";
            Data[i][10]=actual.gettPermanecia()+"";
            
//            System.out.println(i + 1 + "   " + actual.getrLlegada() + "   " + actual.gettLlegada() + " "
//                    + "  " + actual.getReloj() + "   " + actual.isEspero() + "  "
//                    + " " + actual.getCont() + "   " + actual.gettInicioServicio() + " "
//                    + "  " + actual.getrServicio() + "   " + actual.gettServicio() + "   "
//                    + "" + actual.gettFinServicio() + "   " + actual.gettPermanecia());
        }
        JTable t=new JTable(Data,Columnas);
        
        panelPrincipal.add(t);
        ventana.add(panelPrincipal);
        ventana.setVisible(true);
    }

}
