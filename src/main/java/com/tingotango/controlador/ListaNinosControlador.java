/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tingotango.controlador;

import co.edu.umanizales.listase.modelo.ListaDETT;
import co.edu.umanizales.listase.modelo.Nino;
import co.edu.umanizales.listase.modelo.NinoOportunidad;
import co.edu.umanizales.listase.modelo.NodoDETT;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
@Named(value = "listaNinosControlador")
@SessionScoped
public class ListaNinosControlador implements Serializable {

    //Niños
    private ListaDETT listadoDETT;
    private NodoDETT temp;
    private Nino ninoGuardar;
    private Nino ninoMostrar;
    private List<Nino> listadoNinos;
    private int cantidadNinos;
    private List<NinoOportunidad> listadoOportunidades;
    private NinoOportunidad ninoOportunidades;
    //Especiales
    private int op;
    private boolean sexo;
    //Controles
    private int arrayAleatorio[];
    private int posicionIngreso;
    private NodoDETT ayudanteColor;
    private boolean direccion;
    private String reiniciar;
    private boolean estadoCiclo;
    private boolean estadoJuego;
    //Diagrama
    private DefaultDiagramModel model;

    public ListaNinosControlador() {

    }

    @PostConstruct
    private void iniciar() {
        listadoNinos = new ArrayList<>();

        listadoNinos.add(new Nino("Camilo", true, (byte) 1));
        listadoNinos.add(new Nino("Santiago", true, (byte) 2));
        listadoNinos.add(new Nino("Alejandro", true, (byte) 3));
        listadoNinos.add(new Nino("Camila", false, (byte) 4));
        listadoNinos.add(new Nino("Sara", false, (byte) 5));
        listadoNinos.add(new Nino("Natalia", false, (byte) 6));

        listadoOportunidades = new ArrayList<>();

        listadoDETT = new ListaDETT();
        temp = listadoDETT.getCabeza();
        reiniciar = "Comenzar";
        estadoCiclo = false;
        inicializarModelo();
        ayudanteColor = listadoDETT.getCabeza();
        ninoOportunidades = new NinoOportunidad();
    }

    public ListaDETT getListadoDETT() {
        return listadoDETT;
    }

    public void setListadoDETT(ListaDETT listadoDETT) {
        this.listadoDETT = listadoDETT;
    }

    public NodoDETT getTemp() {
        return temp;
    }

    public void setTemp(NodoDETT temp) {
        this.temp = temp;
    }

    public Nino getNinoGuardar() {
        return ninoGuardar;
    }

    public void setNinoGuardar(Nino ninoGuardar) {
        this.ninoGuardar = ninoGuardar;
    }

    public Nino getNinoMostrar() {
        return ninoMostrar;
    }

    public void setNinoMostrar(Nino ninoMostrar) {
        this.ninoMostrar = ninoMostrar;
    }

    public List<Nino> getListadoNinos() {
        return listadoNinos;
    }

    public void setListadoNinos(List<Nino> listadoNinos) {
        this.listadoNinos = listadoNinos;
    }

    public int getCantidadNinos() {
        return cantidadNinos;
    }

    public void setCantidadNinos(int cantidadNinos) {
        this.cantidadNinos = cantidadNinos;
    }

    public List<NinoOportunidad> getListadoOportunidades() {
        return listadoOportunidades;
    }

    public void setListadoOportunidades(List<NinoOportunidad> listadoOportunidades) {
        this.listadoOportunidades = listadoOportunidades;
    }

    public NinoOportunidad getNinoOportunidades() {
        return ninoOportunidades;
    }

    public void setNinoOportunidades(NinoOportunidad ninoOportunidades) {
        this.ninoOportunidades = ninoOportunidades;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public int[] getArrayAleatorio() {
        return arrayAleatorio;
    }

    public void setArrayAleatorio(int[] arrayAleatorio) {
        this.arrayAleatorio = arrayAleatorio;
    }

    public int getPosicionIngreso() {
        return posicionIngreso;
    }

    public void setPosicionIngreso(int posicionIngreso) {
        this.posicionIngreso = posicionIngreso;
    }

    public NodoDETT getAyudanteColor() {
        return ayudanteColor;
    }

    public void setAyudanteColor(NodoDETT ayudanteColor) {
        this.ayudanteColor = ayudanteColor;
    }

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

    public String getReiniciar() {
        return reiniciar;
    }

    public void setReiniciar(String reiniciar) {
        this.reiniciar = reiniciar;
    }

    public boolean isEstadoCiclo() {
        return estadoCiclo;
    }

    public void setEstadoCiclo(boolean estadoCiclo) {
        this.estadoCiclo = estadoCiclo;
    }

    public boolean isEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(boolean estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    public void guardarNino() {
        boolean id = false;
        for (Nino nino : listadoNinos) {
            if (nino.getId() == ninoGuardar.getId()) {
                id = true;
            }
        }
        if (id == true) {
            JsfUtil.addErrorMessage("El ID ya se encuentra tomado, ingrese otro");
        } else {
            listadoNinos.add(ninoGuardar);
            JsfUtil.addSuccessMessage("Se ha agregado satisfactoriamente el nino " + ninoGuardar.getNombre());
            ninoGuardar = new Nino();
        }
    }

    public String irHomeTT() {
        ninoGuardar = new Nino();
        return "homeTT";
    }

    public String irRegistroNino() {
        ninoGuardar = new Nino();
        return "crearTT";
    }

    public void ninoOportunidades(Nino nino) {
        listadoOportunidades.add(new NinoOportunidad(nino, 3));
    }

    public void participantes() {

        listadoDETT = new ListaDETT();
        listadoOportunidades = new ArrayList<>();
        if (cantidadNinos > listadoNinos.size()) {

            JsfUtil.addErrorMessage("La cantidad de infantes es ,mayor a la existente");

        } else {

            arrayAleatorio = new int[cantidadNinos];
            int i = 0, rango = listadoNinos.size();
            arrayAleatorio[i] = (int) (Math.random() * rango);
            for (i = 1; i < cantidadNinos; i++) {
                arrayAleatorio[i] = (int) (Math.random() * rango);
                for (int j = 0; j < i; j++) {
                    if (arrayAleatorio[i] == arrayAleatorio[j]) {
                        i--;
                    }
                }
            }
            for (int k = 0; k < cantidadNinos; k++) {

                Nino nino = listadoNinos.get(arrayAleatorio[k]);
                listadoDETT.adicionarNodoCircularTT(nino);
                ninoOportunidades(nino);

            }
            ayudanteColor = listadoDETT.getCabeza();
            inicializarModelo();
        }
    }

    public void inicializarModelo() {
        //Instanciar el modelo
        model = new DefaultDiagramModel();
        //Definirle al modelo la cantidad de enlaces -1 (infinito)
        model.setMaxConnections(-1);

        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        model.setDefaultConnector(connector);

        //pregunto si hay datos
        if (listadoDETT.getCabeza() != null) {
            //Llamo a mi ayudante y lo ubico en el primero
            NodoDETT ayudante = listadoDETT.getCabeza();
            //recorro mientras el ayudante tenga datos
            double R = (360 / listadoDETT.getTotalNinos());
            double posX = 25;
            double posY = 15;
            double X = 0;
            double Y = 0;
            while (ayudante.getSiguiente() != listadoDETT.getCabeza()) {
                Element ninoPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");

                if (ayudante.getDato().getId() == ayudanteColor.getDato().getId()) {

                    ninoPintar.setStyleClass("ui-diagram-success");
                }

                ninoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.CONTINUOUS));
                ninoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.CONTINUOUS));
                model.addElement(ninoPintar);
                ayudante = ayudante.getSiguiente();
                X = X + R;
                Y = Y + R;
                double Xrad = Math.toRadians(X);
                double Yrad = Math.toRadians(Y);
                posX = posX + ((Math.sin(Xrad)) * 10);
                posY = posY + ((Math.cos(Yrad)) * 10);

            }
            Element ninoPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");

            if (ayudante.getDato().getId() == ayudanteColor.getDato().getId()) {

                ninoPintar.setStyleClass("ui-diagram-success");
            }

            ninoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.CONTINUOUS));
            ninoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.CONTINUOUS));
            model.addElement(ninoPintar);
            X = X + R;
            Y = Y + R;
            double Xrad = Math.toRadians(X);
            double Yrad = Math.toRadians(Y);
            posX = posX + ((Math.sin(Xrad)) * 10);
            posY = posY + ((Math.cos(Yrad)) * 10);

            // el ayudante quedo en el enlace del último
            //Ya pinte todos los elementos y los puntos de enlace
            for (int i = 0; i <= model.getElements().size() - 1; i++) {
                if (i == model.getElements().size() - 1) {
                    model.connect(createConnection(model.getElements().get(i).getEndPoints().get(0),
                            model.getElements().get(0).getEndPoints().get(1), null));
                    model.connect(createConnection(model.getElements().get(0).getEndPoints().get(1),
                            model.getElements().get(i).getEndPoints().get(0), null));
                } else {
                    model.connect(createConnection(model.getElements().get(i).getEndPoints().get(0),
                            model.getElements().get(i + 1).getEndPoints().get(1), null));
                    model.connect(createConnection(model.getElements().get(i + 1).getEndPoints().get(1),
                            model.getElements().get(i).getEndPoints().get(0), null));
                }
            }
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

     public void estadoJugar() {

        reiniciar = "Iniciar";
        estadoCiclo = !estadoCiclo;
        if (estadoCiclo) {
            reiniciar = "Parar";
        }
         quitaOportunidades();
        inicializarModelo();
    }

    public void pasarColor() {

        if (direccion == true) {
            ayudanteColor = ayudanteColor.getAnterior();
            inicializarModelo();
        } else {
            ayudanteColor = ayudanteColor.getSiguiente();
            inicializarModelo();
        }
    }

     public void quitaOportunidades() {
        if (listadoDETT.getCabeza().getSiguiente() != listadoDETT.getCabeza()) {
            if (estadoCiclo == false) {
                for (NinoOportunidad opor : listadoOportunidades) {
                    if (opor.getNino() == ayudanteColor.getDato()) {
                        opor.setOportunidad(opor.getOportunidad() - 1);
                        if (opor.getOportunidad() == 0) {
                            listadoDETT.eliminarPosicion(ayudanteColor.getDato());
                            inicializarModelo();
                        }
                    }
                }

            }
        } else {
            JsfUtil.addSuccessMessage("el ganador es " + listadoDETT.getCabeza().getDato().getNombre());
        }
    }

    public void selecccionarNino(NinoOportunidad opor) {

        ninoOportunidades = opor;
    }

    public void reingresarPorPosicion() {

        if (ninoOportunidades.getNino() != null) {

            ninoOportunidades.setOportunidad(op);
            listadoDETT.adicionarNodoEnPosicion(posicionIngreso, ninoOportunidades.getNino());
            ninoOportunidades = new NinoOportunidad();
            inicializarModelo();

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un niño para reingresar");
            inicializarModelo();
        }

    }

    public void ingresarPorGenero() {

        if (!listadoOportunidades.isEmpty()) {

            if (sexo == true) {
                for (NinoOportunidad op3 : listadoOportunidades) {
                    if (op3.getOportunidad() == 0 && op3.getNino().isSexo() == true) {
                        op3.setOportunidad(op);
                        listadoDETT.adicionarNodoCircularTT(op3.getNino());

                    }
                }
                inicializarModelo();
            } else if (sexo == false) {
                for (NinoOportunidad op3 : listadoOportunidades) {
                    if (op3.getOportunidad() == 0 && op3.getNino().isSexo() == false) {
                        op3.setOportunidad(op);
                        listadoDETT.adicionarNodoCircularTT(op3.getNino());
                    }
                }
            }
            inicializarModelo();
        } else {
            JsfUtil.addErrorMessage("No hay nadie Jugando");
        }
    }

    public boolean revisarRetiro() {
        boolean revisar = false;
        if (sexo == true) {
            for (NinoOportunidad op4 : listadoOportunidades) {
                if (op4.getNino().isSexo() == false && op4.getOportunidad() != 0) {
                    revisar = true;
                }
            }

        } else if (sexo == false) {
            for (NinoOportunidad op4 : listadoOportunidades) {
                if (op4.getNino().isSexo() == true && op4.getOportunidad() != 0) {
                    revisar = true;
                }
            }
        }
        if (revisar == false) {
            JsfUtil.addErrorMessage("El juego no puede estar vacio");
        }
        return revisar;
    }

    public void retirarPorGenero() {

        if (!listadoOportunidades.isEmpty()) {
            if (revisarRetiro() == true) {
                if (sexo == true) {
                    for (NinoOportunidad op5 : listadoOportunidades) {
                        if (op5.getNino().isSexo() == true) {
                            op5.setOportunidad(0);
                            listadoDETT.eliminarPosicion(op5.getNino());
                        }
                    }
                    inicializarModelo();
                } else if (sexo == false) {
                    for (NinoOportunidad op5 : listadoOportunidades) {
                        if (op5.getNino().isSexo() == false) {
                            op5.setOportunidad(0);
                            listadoDETT.eliminarPosicion(op5.getNino());
                        }
                    }
                }
                inicializarModelo();
            }
        } else {
            JsfUtil.addErrorMessage("No hay nadie Jugando");
        }
    }
}
