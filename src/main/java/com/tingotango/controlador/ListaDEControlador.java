/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tingotango.controlador;

import co.edu.umanizales.listase.modelo.ListaDETT;
import co.edu.umanizales.listase.modelo.Nino;
import co.edu.umanizales.listase.modelo.NodoDE;
import co.edu.umanizales.listase.modelo.NodoDETT;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author tsuyo
 */
@Named(value = "listaDEControlador")
public class ListaDEControlador implements Serializable {

    private ListaDETT listaNinosDETT;
    private Nino ninoMostrarDETT;
    private NodoDETT tempDETT;
    private Nino ninoEncontradoDETT;
    private int posicionUno;
    private int posicionDos;
    private int posicion;
    private int borrarPorPosicion;
    private int borrarPorGenero;
    private int reingresarPorGenero;
    private DefaultDiagramModel model;
    private int seleccionUbicacionDETT = 0;
    private int totalNinosDETT = 0;

    public ListaDEControlador() {
    }

    @PostConstruct
    private void iniciarDETT() {
        listaNinosDETT = new ListaDETT();

        ninoMostrarDETT = listaNinosDETT.getCabeza().getDato();
        tempDETT = listaNinosDETT.getCabeza();
        totalNinosDETT = listaNinosDETT.contarNodos();
//        inicializarModelo();
    }

    public ListaDETT getListaNinosDETT() {
        return listaNinosDETT;
    }

    public void setListaNinosDETT(ListaDETT listaNinosDETT) {
        this.listaNinosDETT = listaNinosDETT;
    }

    public Nino getNinoMostrarDETT() {
        return ninoMostrarDETT;
    }

    public void setNinoMostrarDETT(Nino ninoMostrarDETT) {
        this.ninoMostrarDETT = ninoMostrarDETT;
    }

    public NodoDETT getTempDETT() {
        return tempDETT;
    }

    public void setTempDETT(NodoDETT tempDETT) {
        this.tempDETT = tempDETT;
    }

    public Nino getNinoEncontradoDETT() {
        return ninoEncontradoDETT;
    }

    public void setNinoEncontradoDETT(Nino ninoEncontradoDETT) {
        this.ninoEncontradoDETT = ninoEncontradoDETT;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getBorrarPorPosicion() {
        return borrarPorPosicion;
    }

    public void setBorrarPorPosicion(int borrarPorPosicion) {
        this.borrarPorPosicion = borrarPorPosicion;
    }

    public int getBorrarPorGenero() {
        return borrarPorGenero;
    }

    public void setBorrarPorGenero(int borrarPorGenero) {
        this.borrarPorGenero = borrarPorGenero;
    }

    public int getReingresarPorGenero() {
        return reingresarPorGenero;
    }

    public void setReingresarPorGenero(int reingresarPorGenero) {
        this.reingresarPorGenero = reingresarPorGenero;
    }

    public int getSeleccionUbicacionDETT() {
        return seleccionUbicacionDETT;
    }

    public void setSeleccionUbicacionDETT(int seleccionUbicacionDETT) {
        this.seleccionUbicacionDETT = seleccionUbicacionDETT;
    }
    public void continuar() {
        
    }

    public void parar() {

    }

    public void irDerecha() {
        if (listaNinosDETT.getCabeza() != null) {
            tempDETT = tempDETT.getSiguiente();
            ninoMostrarDETT = tempDETT.getDato();
        }else{
            JsfUtil.addErrorMessage("No hay jugadores");
        }
    }
    public void irIzquierda(){
        if(listaNinosDETT.getCabeza() !=null){
            tempDETT = tempDETT.getAnterior();
            ninoMostrarDETT = tempDETT.getDato();
        }else {
            JsfUtil.addErrorMessage("No hay jugadores");
        }
    }
    public void eliminarPorPosicion() {
        if (tempDETT.getSiguiente() != null) {
            listaNinosDETT.eliminarPorPosicion(borrarPorPosicion);
            JsfUtil.addSuccessMessage("Se ha eliminado");
            //continuar
        } 
        else if (tempDETT.getSiguiente() == null && tempDETT.getAnterior() == null) {
            listaNinosDETT.eliminarPorPosicion(borrarPorPosicion);
            JsfUtil.addSuccessMessage("Se ha eliminado");
            ninoMostrarDETT = null;
            inicializarModelo();
        } 
        else {
            listaNinosDETT.eliminarPorPosicion(borrarPorPosicion);
            JsfUtil.addSuccessMessage("Se ha eliminado");
            //continuar
        }
    }
    public void filtrarM(){
        listaNinosDETT.filtrarMasculino();
        //continuar
    }
    public void filtrarF(){
        listaNinosDETT.filtrarFemenino();
        //continuar
    }
    public void inicializarModelo() {
        //Instancial el modelo
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        model.setDefaultConnector(connector);

        if (listaNinosDETT != null) {
            NodoDETT ayudante = listaNinosDETT.getCabeza();
            int posX = 2;
            int posY = 2;
            while (ayudante != null) {
                Element perroPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");
                if(ayudante.getDato()==ninoEncontradoDETT){
                    perroPintar.setStyleClass("ui-diagram-success");
                }
                if(ayudante.getDato()==(listaNinosDETT.encontrarxPosicionDETT(1))){
                    perroPintar.setStyleClass("ui-diagram-success2");
                }
                if(ayudante.getDato()==(listaNinosDETT.encontrarxPosicionDETT(listaNinosDETT.contarNodos()))){
                    perroPintar.setStyleClass("ui-diagram-success2");
                }
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                model.addElement(perroPintar);
                ayudante = ayudante.getSiguiente();
                posX = posX + 5;
                posY = posY + 5;
            }
        }
        for (int i = 0; i < model.getElements().size() - 1; i++) {
            model.connect(createConnection(model.getElements().get(i).getEndPoints().get(0), model.getElements().get(i + 1).getEndPoints().get(1), null));
        }
    }
    public DiagramModel getModel() {
        return model;
    }
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
        return conn;
    }
    public String irCrearNinoDETT(){
        ninoEncontradoDETT = new Nino();
        posicion = 1;
        seleccionUbicacionDETT = 0;
        return "crearDETT";
    }
     public void guardarNino(){
        switch(seleccionUbicacionDETT){
            case 1:
                listaNinosDETT.adicionarAlInicioDETT(ninoEncontradoDETT);
                break;
            case 2:
                listaNinosDETT.adicionarNodoCircular(ninoEncontradoDETT);
                break;
            case 3:
                listaNinosDETT.adicionarNodoPorPosicion(posicion, ninoEncontradoDETT);
            default: listaNinosDETT.adicionarNodoCircular(ninoEncontradoDETT);
        }
        ninoEncontradoDETT = new Nino();
        //continuar
        JsfUtil.addSuccessMessage("Se ha adicionado el niño a la lista");
    }
     public String irHomeTT(){
        ninoEncontradoDETT = new Nino();
        inicializarModelo();
        return "listaDETT";
    }
        
    public void mostrar() {
        listaNinosDETT.mostrarLista();
        //continuar
    }
}
