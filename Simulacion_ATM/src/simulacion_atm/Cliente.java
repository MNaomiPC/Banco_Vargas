/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion_atm;

/**
 *
 * @author Naomi
 */
public class Cliente {

    private final double rLlegada;
    private double tLlegada;
    private double reloj;
    private boolean espero;
    private int cont;
    private double tInicioServicio;
    private final double rServicio;
    private double tServicio;
    private double tFinServicio;
    private double tPermanencia;

    public Cliente(double rLlegada, double rServicio) {
        this.rLlegada = rLlegada;
        this.rServicio = rServicio;
        calcularTiempoEntreLlegada();
        calcularTiempoEntreServicio();
//        calcularTiempoFinServicio();
//        calcularTiempoPermanencia();
    }

    private void calcularTiempoEntreLlegada() {
        tLlegada = -10 * Math.log(1 - rLlegada);
    }

    private void calcularTiempoEntreServicio() {
        tServicio = -4 * Math.log(1 - rServicio);
    }

    public double calcularReloj(double tiempoAcumulado) {
        reloj = tLlegada + tiempoAcumulado;
        return reloj;
    }

    public double calcularTiempoInicioServicio(double tiempoFinalAnterior) {
        if (tiempoFinalAnterior > 0 && tiempoFinalAnterior > reloj) {
            return tInicioServicio = tiempoFinalAnterior;
        } else {
            return tInicioServicio = reloj;
        }
    }

    public void calcularTiempoFinServicio() {
        tFinServicio = tServicio + tInicioServicio;
    }

    public void calcularTiempoPermanencia() {
        tPermanencia = tFinServicio - reloj;
    }

    public double gettLlegada() {
        return tLlegada;
    }

    public double getReloj() {
        return reloj;
    }

    public boolean isEspero() {
        return espero;
    }

    public int getCont() {
        return cont;
    }

    public double gettInicioServicio() {
        return tInicioServicio;
    }

    public double gettServicio() {
        return tServicio;
    }

    public double gettFinServicio() {
        return tFinServicio;
    }

    public double gettPermanecia() {
        return tPermanencia;
    }

    public double getrLlegada() {
        return rLlegada;
    }

    public double getrServicio() {
        return rServicio;
    }

    public void setEspero(boolean espero) {
        this.espero = espero;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

}
