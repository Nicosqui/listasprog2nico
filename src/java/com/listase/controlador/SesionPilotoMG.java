/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;
import com.listase.excepciones.PilotoExcepcion;
import com.listase.modelo.Piloto;
import com.listase.modelo.ListaMotogp;
import com.listase.modelo.NodoMotogp;
import com.listase.utilidades.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author nicolas
 */
@Named(value = "sesionPilotoMG")
@SessionScoped
public class SesionPilotoMG implements Serializable {

    /**
     * Creates a new instance of SesionPilotoMG
     */
    private ListaMotogp listaPilotos;
    private Piloto piloto;
    private String alInicio="1";
    private boolean deshabilitarFormulario=true;
    private NodoMotogp ayudante;   
    private String textoVista="Gráfico";
    
    private List listadoPilotos;
    
    private DefaultDiagramModel model;
    
    private short codigoEliminar;
           
    private short pilotoSeleccionado;
    private ControladorMarcamot controlMarcamot ;
    
    private Piloto pilotoDiagrama;
    public SesionPilotoMG() {
    }
    
    @PostConstruct
    private void inicializar()
    {
        controlMarcamot = new ControladorMarcamot();
        //inicializando el combo en el primer depto
        
        
        listaPilotos = new ListaMotogp();        
        //LLenado de la bds
        listaPilotos.adicionarNodo(new Piloto("Mauricio",(short) 1, (byte)2, true,
        controlMarcamot.getMarcas().get(0).getNombre()));
        listaPilotos.adicionarNodo(new Piloto("Sebastian",(short) 2, (byte)3, false,
        controlMarcamot.getMarcas().get(3).getNombre()));
        listaPilotos.adicionarNodo(new Piloto("Alejandro",(short) 3, (byte)1,false,
        controlMarcamot.getMarcas().get(1).getNombre()));
        listaPilotos.adicionarNodoAlInicio(new Piloto("Alexis",(short) 4, (byte)5,false,
        controlMarcamot.getMarcas().get(2).getNombre()));
        ayudante = listaPilotos.getCabeza();
        piloto = ayudante.getDato();     
        //Me llena el objeto List para la tabla
        listadoPilotos = listaPilotos.obtenerListaPilotos();
        pintarLista();
   }

    public ListaMotogp getListaPilotos() {
        return listaPilotos;
    }

    public void setListaPilotos(ListaMotogp listaPilotos) {
        this.listaPilotos = listaPilotos;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public String getAlInicio() {
        return alInicio;
    }

    public void setAlInicio(String alInicio) {
        this.alInicio = alInicio;
    }

    public boolean isDeshabilitarFormulario() {
        return deshabilitarFormulario;
    }

    public void setDeshabilitarFormulario(boolean deshabilitarFormulario) {
        this.deshabilitarFormulario = deshabilitarFormulario;
    }

    public NodoMotogp getAyudante() {
        return ayudante;
    }

    public void setAyudante(NodoMotogp ayudante) {
        this.ayudante = ayudante;
    }

    public String getTextoVista() {
        return textoVista;
    }

    public void setTextoVista(String textoVista) {
        this.textoVista = textoVista;
    }

    public List getListadoPilotos() {
        return listadoPilotos;
    }

    public void setListadoPilotos(List listadoPilotos) {
        this.listadoPilotos = listadoPilotos;
    }

    

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    public short getCodigoEliminar() {
        return codigoEliminar;
    }

    public void setCodigoEliminar(short codigoEliminar) {
        this.codigoEliminar = codigoEliminar;
    }

    public short getPilotoSeleccionado() {
        return pilotoSeleccionado;
    }

    public void setPilotoSeleccionado(short pilotoSeleccionado) {
        this.pilotoSeleccionado = pilotoSeleccionado;
    }

    public Piloto getInfanteDiagrama() {
        return pilotoDiagrama;
    }

    public void setInfanteDiagrama(Piloto infanteDiagrama) {
        this.pilotoDiagrama = infanteDiagrama;
    }
    
    public DiagramModel getModel() {
        return model;
    }
     
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
         
        if(label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
         
        return conn;
    }
    
    public void guardarPiloto()
    {
        //obtiene el consecutivo
        piloto.setCodigo((short)(listaPilotos.contarNodos()+1));
        if(alInicio.compareTo("1")==0)
        {
            listaPilotos.adicionarNodoAlInicio(piloto);
        }
        else
        {
            listaPilotos.adicionarNodo(piloto);
        }  
        //Vuelvo a llenar la lista para la tabla
        listadoPilotos = listaPilotos.obtenerListaPilotos();
        pintarLista();
        deshabilitarFormulario=true;
        JsfUtil.addSuccessMessage("El plioto se ha guardado exitosamente");
        
    }
    
    public void habilitarFormulario()
    {
        deshabilitarFormulario=false;
        piloto = new Piloto();
    }
    
    public void irAnterior()
    {
        if(ayudante.getAnterior()!=null)
        {
            ayudante = ayudante.getAnterior();
            piloto = ayudante.getDato();
        }        
    }
    
    
    public void irSiguiente()
    {
        if(ayudante.getSiguiente()!=null)
        {
            ayudante = ayudante.getSiguiente();
            piloto = ayudante.getDato();
        }        
    }
    
    public void irPrimero()
    {
        if(listaPilotos.getCabeza()!=null)
        {
            ayudante = listaPilotos.getCabeza();
            piloto = ayudante.getDato();
            
        }
        else
        {
            piloto = new Piloto();
        }
        listadoPilotos = listaPilotos.obtenerListaPilotos();
        pintarLista();
             
    }
    
    public void irUltimo()
    {
        if(listaPilotos.getCabeza()!=null)
        {            
            while(ayudante.getSiguiente()!=null)
            {
                ayudante = ayudante.getSiguiente();
            }
            piloto=ayudante.getDato();
        }
    }
    
    public void cambiarVistaPilotos()
    {
        if(textoVista.compareTo("Tabla")==0)
        {
            textoVista = "Gráfico";
        }
        else
        {
            textoVista = "Tabla";
        }
    }
     
    
    public void pintarLista() {
        //Instancia el modelo
        model = new DefaultDiagramModel();
        //Se establece para que el diagrama pueda tener infinitas flechas
        model.setMaxConnections(-1);

        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);

        ///Adicionar los elementos
        if (listaPilotos.getCabeza() != null) {
            //llamo a mi ayudante
            NodoMotogp temp = listaPilotos.getCabeza();
            int posX=2;
            int posY=2;
            //recorro la lista de principio a fin
            while(temp !=null)
            {
                //Parado en un elemento
                //Crea el cuadrito y lo adiciona al modelo
                Element ele = new Element(temp.getDato().getCodigo()+" "+
                        temp.getDato().getNombre(), 
                        posX+"em", posY+"em");
                ele.setId(String.valueOf(temp.getDato().getCodigo()));
                //adiciona un conector al cuadrito
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
                
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                model.addElement(ele);                    
                temp=temp.getSiguiente();
                posX=  posX+5;
                posY= posY+6;
            }            
           
            //Pinta las flechas            
            for(int i=0; i < model.getElements().size() -1; i++)
            {
                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(1), 
                        model.getElements().get(i+1).getEndPoints().get(0), "Sig"));
                
                
                model.connect(createConnection(model.getElements().get(i+1).getEndPoints().get(2), 
                        model.getElements().get(i).getEndPoints().get(3), "Ant"));
            }
            
        }
    }
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");
         
        pilotoSeleccionado = Short.valueOf(id.replaceAll("frmInfante:diagrama-", ""));
        
    }

    public void eliminarInfante()
    {
        if(codigoEliminar >0)
        {
            //llamo el eliminar de la lista
            try{
                listaPilotos.eliminarPiloto(codigoEliminar);
                irPrimero();
                JsfUtil.addSuccessMessage("Infante "+codigoEliminar +" eliminado.");
            }
            catch(PilotoExcepcion e)
            {
                JsfUtil.addErrorMessage(e.getMessage());
            }
        }
        else
        {
            JsfUtil.addErrorMessage("El código a eliminar "+codigoEliminar+ " no es válido");
        }
    }
    
    
    public void obtenerInfanteDiagrama()
    {
        try {
            pilotoDiagrama = listaPilotos.obtenerPiloto(pilotoSeleccionado);
        } catch (PilotoExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlFinal()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Piloto infTemporal = listaPilotos.obtenerPiloto(pilotoSeleccionado);
            // Eliminar el nodo
            listaPilotos.eliminarPiloto(pilotoSeleccionado);
            // Adicionarlo al final
            listaPilotos.adicionarNodo(infTemporal);
            
            pintarLista();
        } catch (PilotoExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlInicio()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Piloto infTemporal = listaPilotos.obtenerPiloto(pilotoSeleccionado);
            // Eliminar el nodo
            listaPilotos.eliminarPiloto(pilotoSeleccionado);
            // Adicionarlo al inicio
            listaPilotos.adicionarNodoAlInicio(infTemporal);
            
            pintarLista();
        } catch (PilotoExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
}
