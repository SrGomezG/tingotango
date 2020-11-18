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
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
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
@SessionScoped
public class ListaDEControlador implements Serializable {

    //Niños
    private List<Nino> listadoNinos;
    private List<Nino> listadoNinoEliminados;
    private List<NinoOportunidad> listadoOportunidades;
    private NinoOportunidad ninoSeleccionado;
    private Nino NinoGuardar;
    private ListaDETT listaNinosDETT;
    private Nino ninoMostrarDETT;
    private NodoDETT tempDETT;
    private Nino ninoEncontradoDETT;
    private byte oportunidades;
    private byte cantidadNinos;
    private NodoDETT tempColor;
    private byte generadorDeID;
    //selecciones
    private int seleccionUbicacionDETT = 0;
    private int generoIngresar = 0;
    private NinoOportunidad ninoReingresar;
    private int posicion;
    private int borrarPorPosicion;
    private int borrarPorGenero;
    private int reingresarPorGenero;
    private byte xIngreso;
    private byte xGenero;
    //model
    private DashboardModel model;
    private DefaultDiagramModel modelTable;
    //estado juego
    private String textoBotonParar;
    private String textoBotonDer;
    private String textoBotonIzq;
    private boolean estadoDer;
    private boolean estadoIzq;
    private boolean estadoParar;
    private String colParar;
    private String colIzq;
    private String colDer;

    public ListaDEControlador() {
    }

    @PostConstruct
    private void iniciarDETT() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();

        column1.addWidget("Acciones");
        column2.addWidget("Lista");

        column1.addWidget("Grafica");

        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);

        listaNinosDETT = new ListaDETT();
        listadoNinos = new ArrayList<>();
        listadoNinoEliminados = new ArrayList<>();
        listadoOportunidades = new ArrayList<>();
        generadorDeID = 1;
        textoBotonDer = "Iniciar Derecha";
        textoBotonIzq = "Iniciar Izquierda";
        textoBotonParar = "Parar";
        estadoDer = false;
        estadoIzq = false;
        colDer = "#F9E79F";
        colIzq = "#F9E79F";
        colParar = "#F9E79F";
        tempColor = listaNinosDETT.getCabeza();
    }

    public NinoOportunidad getNinoSeleccionado() {
        return ninoSeleccionado;
    }

    public void setNinoSeleccionado(NinoOportunidad ninoSeleccionado) {
        this.ninoSeleccionado = ninoSeleccionado;
    }

    public String getTextoBotonParar() {
        return textoBotonParar;
    }

    public void setTextoBotonParar(String textoBotonParar) {
        this.textoBotonParar = textoBotonParar;
    }

    public DefaultDiagramModel getModelTable() {
        return modelTable;
    }

    public void setModelTable(DefaultDiagramModel modelTable) {
        this.modelTable = modelTable;
    }

    public boolean isEstadoParar() {
        return estadoParar;
    }

    public void setEstadoParar(boolean estadoParar) {
        this.estadoParar = estadoParar;
    }

    public String getColParar() {
        return colParar;
    }

    public void setColParar(String colParar) {
        this.colParar = colParar;
    }

    public List<Nino> getListadoNinoEliminados() {
        return listadoNinoEliminados;
    }

    public void setListadoNinoEliminados(List<Nino> listadoNinoEliminados) {
        this.listadoNinoEliminados = listadoNinoEliminados;
    }

    public byte getGeneradorDeID() {
        return generadorDeID;
    }

    public void setGeneradorDeID(byte generadorDeID) {
        this.generadorDeID = generadorDeID;
    }

    public List<NinoOportunidad> getListadoOportunidades() {
        return listadoOportunidades;
    }

    public void setListadoOportunidades(List<NinoOportunidad> listadoOportunidades) {
        this.listadoOportunidades = listadoOportunidades;
    }

    public byte getxIngreso() {
        return xIngreso;
    }

    public void setxIngreso(byte xIngreso) {
        this.xIngreso = xIngreso;
    }

    public byte getxGenero() {
        return xGenero;
    }

    public void setxGenero(byte xGenero) {
        this.xGenero = xGenero;
    }

    public byte getCantidadNinos() {
        return cantidadNinos;
    }

    public void setCantidadNinos(byte cantidadNinos) {
        this.cantidadNinos = cantidadNinos;
    }

    public NinoOportunidad getNinoReingresar() {
        return ninoReingresar;
    }

    public void setNinoReingresar(NinoOportunidad ninoReingresar) {
        this.ninoReingresar = ninoReingresar;
    }

    public List<Nino> getListadoNinos() {
        return listadoNinos;
    }

    public void setListadoNinos(List<Nino> listadoNinos) {
        this.listadoNinos = listadoNinos;
    }

    public Nino getNinoGuardar() {
        return NinoGuardar;
    }

    public void setNinoGuardar(Nino NinoGuardar) {
        this.NinoGuardar = NinoGuardar;
    }

    public byte getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(byte oportunidades) {
        this.oportunidades = oportunidades;
    }

    public NodoDETT getTempColor() {
        return tempColor;
    }

    public void setTempColor(NodoDETT tempColor) {
        this.tempColor = tempColor;
    }

    public String getTextoBotonDer() {
        return textoBotonDer;
    }

    public void setTextoBotonDer(String textoBotonDer) {
        this.textoBotonDer = textoBotonDer;
    }

    public String getTextoBotonIzq() {
        return textoBotonIzq;
    }

    public void setTextoBotonIzq(String textoBotonIzq) {
        this.textoBotonIzq = textoBotonIzq;
    }

    public boolean isEstadoDer() {
        return estadoDer;
    }

    public void setEstadoDer(boolean estadoDer) {
        this.estadoDer = estadoDer;
    }

    public boolean isEstadoIzq() {
        return estadoIzq;
    }

    public void setEstadoIzq(boolean estadoIzq) {
        this.estadoIzq = estadoIzq;
    }

    public String getColIzq() {
        return colIzq;
    }

    public void setColIzq(String colIzq) {
        this.colIzq = colIzq;
    }

    public String getColDer() {
        return colDer;
    }

    public void setColDer(String colDer) {
        this.colDer = colDer;
    }

    public int getGeneroIngresar() {
        return generoIngresar;
    }

    public void setGeneroIngresar(int generoIngresar) {
        this.generoIngresar = generoIngresar;
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

//    public void eliminacionDirecta() {
//        if (tempDETT.getSiguiente() != null) {
//            listaNinosDETT.eliminar(ninoMostrarDETT);
//            JsfUtil.addSuccessMessage(ninoMostrarDETT.getNombre() + "Se ha eliminado");
//            diagramaDerecha();
//        } else if (tempDETT.getSiguiente() == null && tempDETT.getAnterior() == null) {
//            listaNinosDETT.eliminar(ninoMostrarDETT);
//            JsfUtil.addSuccessMessage(ninoMostrarDETT.getNombre() + "Se ha eliminado");
//            ninoMostrarDETT = null;
//            diagramaDerecha();
//        } else {
//            listaNinosDETT.eliminar(ninoMostrarDETT);
//            JsfUtil.addSuccessMessage(ninoMostrarDETT.getNombre() + "Se ha eliminado");
//            diagramaDerecha();
//        }
//    }
    public void sacarNinas() {

        int contador = listaNinosDETT.contarNiños("niña");
        int cont = listaNinosDETT.contarNiños("niño");
        if (cont == 1) {
            JsfUtil.addErrorMessage("No puede quedar un solo niño en el juego");
        } else {
            if (contador != listaNinosDETT.contarNodos()) {
                guardarNiñas();
                listaNinosDETT.eliminarNiños("niña");
                tempColor = listaNinosDETT.getCabeza();
                diagramaDerecha();
            } else {
                JsfUtil.addErrorMessage("Solo hay niñas , el juego no puede quedar vacio");
            }
        }
    }

    public void sacarNinos() {
        int contador = listaNinosDETT.contarNiños("niño");
        int cont = listaNinosDETT.contarNiños("niña");
        if (cont == 1) {
            JsfUtil.addErrorMessage("No puede quedar una sola niña en el juego");
        } else {
            if (contador != listaNinosDETT.contarNodos()) {
                guardarNiños();
                listaNinosDETT.eliminarNiños("niño");
                tempColor = listaNinosDETT.getCabeza();
                diagramaDerecha();
            } else {
                JsfUtil.addErrorMessage("Solo hay niños , el juego no puede quedar vacio");
            }
        }
    }

    public void reingresarPosicion() {
        ninoReingresar.setOportunidades(xIngreso);
        listaNinosDETT.adicionarNodoPorPosicion(ninoReingresar, posicion);
        diagramaIzquierda();
        listadoOportunidades.remove(ninoReingresar);
        ninoReingresar = new NinoOportunidad();
        JsfUtil.addSuccessMessage("Se ha reingreso el niño a la lista");
        listaNinosDETT.mostrarLista();
    }

    public void eliminarDirecto() {

        listadoOportunidades.add(tempColor.getDato2());
        listaNinosDETT.eliminar(tempColor.getDato2());
        tempColor = listaNinosDETT.getCabeza();
        diagramaDerecha();
    }

    public void reingresarNiñas(NinoOportunidad ninoEliminado) {
        int cont = 0;
        List<NinoOportunidad> listaTemporal = new ArrayList<>();
        for (NinoOportunidad Eliminado : listadoOportunidades) {
            if (Eliminado.getDato2().getSexo().equals("Femenino")) {
                Eliminado.setOportunidades(xGenero);
                listaNinosDETT.adicionarNodoCircular(Eliminado);
                listaTemporal.add(Eliminado);
                cont++;
            }
        }
        listadoOportunidades.removeAll(listaTemporal);
        diagramaDerecha();
        if (cont == 0) {
            JsfUtil.addErrorMessage("No hay Niñas para Reingresar");
        }
    }

    public void reingresarNiños(NinoOportunidad ninoEliminado) {
        int cont = 0;
        List<NinoOportunidad> listaTemporal = new ArrayList<>();
        for (NinoOportunidad Eliminado : listadoOportunidades) {
            if (Eliminado.getDato2().getSexo().equals("Masculino")) {
                Eliminado.setOportunidades(xGenero);
                listaNinosDETT.adicionarNodoCircular(Eliminado);
                listaTemporal.add(Eliminado);
                cont++;
            }
        }
        listadoOportunidades.removeAll(listaTemporal);
        diagramaDerecha();
        if (cont == 0) {
            JsfUtil.addErrorMessage("No hay Niños para Reingresar");
        }
    }

    public void reiniciar() {

        listaNinosDETT.setCabeza(null);
        listadoOportunidades.clear();
        diagramaDerecha();
    }

    public void seleccionarNino(NinoOportunidad nino) {
        ninoReingresar = nino;
    }

    public void capturarNinoEditar(Nino nino) {

        listadoNinos.remove(nino);
        NinoGuardar = nino;

    }

    public void actualizarNino() {

        listadoNinos.add(NinoGuardar);
        NinoGuardar = new Nino();

    }

    public int contarInfantes() {

        int tamanio = listadoNinos.size();
        return tamanio;
    }

    public int contarNinosEliminados() {
        int tamanio = listadoOportunidades.size();
        return tamanio;
    }

    public void eliminarNino(Nino nino) {
        listadoNinos.remove(nino);
    }

    public DiagramModel getModel() {
        return modelTable;
    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
        return conn;
    }

    public String irCrearNinoDETT() {
        NinoGuardar = new Nino();
//        ninoEncontradoDETT = new Nino();
//        posicion = 1;
//        seleccionUbicacionDETT = 0;
        return "crearDETT";
    }

    public void guardarNiñas() {
        int cont = 0;
        for (NinoOportunidad eliminarNino : listaNinosDETT.eliminarNina(femenino)) {
            if (eliminarNino.getDato2() instanceof Nino) {
                listadoOportunidades.add(eliminarNino);
                cont++;
            }
        }
        if (cont == 0) {

            JsfUtil.addErrorMessage("No hay Niñas para retirar del juego");
        }
    }

    public void guardarNiños() {
        int cont = 0;
        for (NinoOportunidad eliminarNino : listaNinosDETT.eliminarNino(masculino)) {
            if (eliminarNino.getDato2() instanceof Nino) {
                listadoOportunidades.add(eliminarNino);
                cont++;
            }
        }
        if (cont == 0) {
            JsfUtil.addErrorMessage("No hay Niños para retirar del juego");
        }
    }

    public void reingresarNiño() {

        ninoReingresar.setOportunidades(xIngreso);

        switch (seleccionUbicacionDETT) {

            case 1:
                listaNinosDETT.adicionarNodoCircular(ninoReingresar);
                seleccionUbicacionDETT = 0;

                break;
            case 2:
                listaNinosDETT.adicionarNodoAlFinalNino(ninoReingresar);
                seleccionUbicacionDETT = 0;
                ;
                break;

            default:
                listaNinosDETT.adicionarNodoAlFinalNino(ninoReingresar);
                seleccionUbicacionDETT = 0;

        }

        listadoOportunidades.remove(ninoReingresar);
        diagramaDerecha();
        ninoReingresar = new NinoOportunidad();

        JsfUtil.addSuccessMessage("Se ha reingreso el niño a la lista");

    }

    public String irHomeTT() {
        NinoGuardar = new Nino();
        ninoEncontradoDETT = new Nino();
        return "listaDETT";
    }

    public void contarLista() {
        listaNinosDETT.contarNodos();
    }

    public int contarNinos() {
        int tamanio1 = listaNinosDETT.tamanio();
        return tamanio1;
    }

    public void seleccionarInfante(NinoOportunidad nino) {

        ninoReingresar = nino;
    }

    public void ninoEditar(Nino nino) {
        listadoNinos.remove(nino);
        NinoGuardar = nino;
    }

    public void eliminarGenero(NinoOportunidad nino) {

        listadoOportunidades.add(nino);
        listaNinosDETT.eliminar(nino);
        tempColor = listaNinosDETT.getCabeza();
        diagramaDerecha();

    }

    public void reingresarIndividual() {

        listaNinosDETT.adicionarNodoCircular(ninoReingresar);
        listadoOportunidades.remove(ninoReingresar);
        diagramaDerecha();
        ninoReingresar = new NinoOportunidad();

    }

    public void reingresarNino(NinoOportunidad ninoEliminado) {

        listaNinosDETT.adicionarNodoCircular(ninoEliminado);
        diagramaDerecha();
        listadoOportunidades.remove(ninoEliminado);
    }
    //FUNCIONALIDADES DIRECCIONALES

    public void pasarSiguienteDerColor() {
        if (tempColor.getSiguiente() != null) {
            tempColor = tempColor.getSiguiente();
        } else {
            /// empieza de nuevo
        }
        diagramaDerecha();
    }

    public void pasarAnteriorDerColor() {
        if (tempColor.getAnterior() != null) {
            tempColor = tempColor.getAnterior();
        } else {
            /// empieza de nuevo
        }
        diagramaIzquierda();
    }

    public void controlarDerecha() {
        textoBotonDer = "Iniciar";
        colDer = "#F9E79F";
        estadoDer = !estadoDer;
        if (estadoDer) {
            textoBotonDer = "Parar";
            colDer = "#82E0AA ";
        }
    }

    public void controlarIzquierda() {
        textoBotonIzq = "Iniciar";
        colIzq = "#F9E79F";
        estadoIzq = !estadoIzq;
        if (estadoIzq) {
            textoBotonIzq = "Parar";
            colIzq = "#82E0AA ";
        }
    }

    public void diagramaDerecha() {
        modelTable = new DefaultDiagramModel();
        modelTable.setMaxConnections(-1);
        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#092CB0',lineWidth:3}");
        modelTable.setDefaultConnector(connector);
        int jugadorPorCuadrante = 0;
        int numeroCuadrante = 1;
        int residuoJugagores = listaNinosDETT.contarNodos() % 4;
        int cuadrantey = 0;
        String posFlecha = "";
        int cantJugadores = 0;
        if (listaNinosDETT.getCabeza() != null) {
            //Llamar ayudante y ubicar en el primero 
            NodoDETT ayudanteDos = listaNinosDETT.getCabeza();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 13;
            int posY = 2;
            int contFila = 0;
            jugadorPorCuadrante = listaNinosDETT.contarNodos() / 4;
            if (residuoJugagores == 3) {
                jugadorPorCuadrante++;
                cuadrantey = 2;
            }
            do {
                if (contFila == jugadorPorCuadrante) {
                    numeroCuadrante++;
                    contFila = 0;
                    if (numeroCuadrante == 2 && contFila == 0) {
                        posX = posX + 8;
                        if (residuoJugagores == 2 || residuoJugagores == 1) {
                            jugadorPorCuadrante++;
                            cuadrantey = 3;
                        }
                    } else if (numeroCuadrante == 3 && contFila == 0) {
                        posY = posY + 4;
                    } else if (numeroCuadrante == 4 && contFila == 0) {
                        posX = posY - 8;
                    }
                }
                switch (numeroCuadrante) {
                    case 1:
                        posX = posX + 8;
                        posY = posY;
                        posFlecha = "LEFT";
                        break;
                    case 2:
                        posX = posX;
                        posY = posY + 5;
                        break;
                    case 3:
                        posX = posX - 8;
                        posY = posY;
                        break;
                    case 4:
                        posX = posX;
                        posY = posY - 5 - cuadrantey;
                        break;
                }
                Element niñoPintar = new Element(ayudanteDos.getDato2().getNombre(), posX + "em", posY + "em");
                niñoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
                niñoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
                modelTable.addElement(niñoPintar);
                if (ayudanteDos.getDato().getId()
                        == tempColor.getDato().getId()) {
                    niñoPintar.setStyleClass("ui-diagram-success");
                }
                ayudanteDos = ayudanteDos.getSiguiente();
                contFila++;
                cantJugadores++;
            } while (ayudanteDos != listaNinosDETT.getCabeza());
            for (int i = 0; i < modelTable.getElements().size() - 1; i++) {
                modelTable.connect(createConnection(modelTable.getElements().get(i).getEndPoints().get(0), modelTable.getElements().get(i + 1).getEndPoints().get(1), null));
                modelTable.connect(createConnection(modelTable.getElements().get(modelTable.getElements().size() - 1).getEndPoints().get(0), modelTable.getElements().get(0).getEndPoints().get(1), null));
            }
        } else {

        }
    }

    public void diagramaIzquierda() {

        //Instanciar el modelo
        modelTable = new DefaultDiagramModel();
        //Definir el modelo la cantidad de enlaces -1 (Infinito)
        modelTable.setMaxConnections(-1);
        //Pregunto si hay datos

        //YA SE PINTARON TODOS LOS ELEMENTOS 
        //PROPIEDAD PARA EL CONECTOR
        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#092CB0',lineWidth:3}");
        modelTable.setDefaultConnector(connector);

        int jugadorPorCuadrante = 0;
        int numeroCuadrante = 1;
        int residuoJugagores = listaNinosDETT.contarNodos() % 4;
        int cuadrantey = 0;
        String posFlecha = "";
        int cantJugadores = 0;

        if (listaNinosDETT.getCabeza() != null) {

            //Llamar ayudante y ubicar en el primero 
            NodoDETT ayudanteDos = listaNinosDETT.getCabeza();
            //Recorro mientras el ayudante tenga ddato 
            int posX = 13;
            int posY = 2;
            int contFila = 0;
            jugadorPorCuadrante = listaNinosDETT.contarNodos() / 4;

            if (residuoJugagores == 3) {
                jugadorPorCuadrante++;
                cuadrantey = 2;
            }
            do {
                if (contFila == jugadorPorCuadrante) {
                    numeroCuadrante++;
                    contFila = 0;
                    if (numeroCuadrante == 2 && contFila == 0) {
                        posX = posX + 8;
                        if (residuoJugagores == 2 || residuoJugagores == 1) {
                            jugadorPorCuadrante++;
                            cuadrantey = 3;
                        }
                    } else if (numeroCuadrante == 3 && contFila == 0) {
                        posY = posY + 4;
                    } else if (numeroCuadrante == 4 && contFila == 0) {
                        posX = posY - 8;
                    }
                }
                switch (numeroCuadrante) {
                    case 1:
                        posX = posX + 8;
                        posY = posY;
                        posFlecha = "LEFT";
                        break;
                    case 2:
                        posX = posX;
                        posY = posY + 5;
                        break;
                    case 3:
                        posX = posX - 8;
                        posY = posY;
                        break;
                    case 4:
                        posX = posX;
                        posY = posY - 5 - cuadrantey;
                        break;
                }
                Element niñoPintar = new Element(ayudanteDos.getDato().getNombre(), posX + "em", posY + "em");
                niñoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
                niñoPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
                modelTable.addElement(niñoPintar);
                if (ayudanteDos.getDato().getId()
                        == tempColor.getDato().getId()) {
                    niñoPintar.setStyleClass("ui-diagram-success");
                }
                ayudanteDos = ayudanteDos.getSiguiente();
                contFila++;
                cantJugadores++;
            } while (ayudanteDos != listaNinosDETT.getCabeza());
            for (int i = 0; i < modelTable.getElements().size() - 1; i++) {
                modelTable.connect(createConnection(modelTable.getElements().get(i).getEndPoints().get(0), modelTable.getElements().get(i + 1).getEndPoints().get(1), null));
                modelTable.connect(createConnection(modelTable.getElements().get(modelTable.getElements().size() - 1).getEndPoints().get(0), modelTable.getElements().get(0).getEndPoints().get(1), null));
            }

        } else {

        }

    }

    public void configurarJuego() {

        List<Nino> listaTemporal = new ArrayList<>();

        if (cantidadNinos > contarNinos()) {

            JsfUtil.addErrorMessage("No tiene los suficientes niños para jugar ");

        } else {

            int cont = 0;
            int maximo = listadoNinos.size();

            for (int i = 0; i < maximo; i++) {

                Random r = new Random();
                int inicio = 0;
                int fin = listadoNinos.size();
                int result = r.nextInt(fin - inicio) + inicio;

                if (cantidadNinos == cont) {
                    break;
                }

                System.out.println("SI ENTRA AGREGAR");

                ninoSeleccionado = new NinoOportunidad(listadoNinos.get(result), (byte) oportunidades);

                listaNinosDETT.adicionarNodoCircular(ninoSeleccionado);
                listaTemporal.add(ninoSeleccionado);
                listadoNinos.remove(ninoSeleccionado.getDato2());
                cont++;

            }

            listadoNinos.addAll(listaTemporal);

        }

        listaNinosDETT.mostrarLista();
        tempColor = listaNinosDETT.getCabeza();
        diagramaDerecha();

    }

    public void adicionarArray() {

        NinoGuardar.setId(generadorDeID++);
        listadoNinos.add(NinoGuardar);
        NinoGuardar = new Nino();

    }
}
