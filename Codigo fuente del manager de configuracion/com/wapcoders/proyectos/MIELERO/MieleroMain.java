package com.wapcoders.proyectos.MIELERO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//////////////////////////////////////////////////////
//MieleroMain clase principal de carga del programa//
////////////////////////////////////////////////////

/**
MieleroMain clase principal de carga del programa

@author WapCoders
@version 0.54
*/

public final class MieleroMain extends JFrame implements WindowListener{

  private JTabbedPane jtppestanas;
  private MieleroProjects mlpproyectos;  
  private MieleroGeneral mlgeneral; 
  private MieleroProducts mlproductos;
  private MieleroLogotipo mllogotipo;
  private MieleroFinal mlfinal;
  //////////////////
  //Constructores//
  ////////////////

  ////////////////////////////////////////////
  //public MieleroMain()                   //
  //Crea la ventana principal del programa//
  /////////////////////////////////////////

  /**
  Crea la ventana principal del programa
  */

  public MieleroMain() {

    super(Constantes.nombreprograma);  


    

    setSize(Constantes.anchoventana,Constantes.altoventana); 
    getContentPane().setLayout(new BorderLayout());
    mlpproyectos=new MieleroProjects();
    mlgeneral = new MieleroGeneral(mlpproyectos);
    mlproductos = new MieleroProducts(mlpproyectos);
    mllogotipo=new MieleroLogotipo();
    mlfinal=new MieleroFinal(mlpproyectos,mlgeneral,mlproductos);
    jtppestanas=new JTabbedPane();
    jtppestanas.addTab("Projects",null,mlpproyectos,"");
    jtppestanas.addTab("General",null,mlgeneral,"");
    jtppestanas.addTab("Products",null,mlproductos,"");
    jtppestanas.addTab("Final",null,mlfinal,"");
    getContentPane().add(jtppestanas,BorderLayout.NORTH);
    getContentPane().add(mllogotipo,BorderLayout.CENTER);
    addWindowListener(this);

    setVisible(true);


  }
  ///////////////
  //INTERFACES//
  /////////////

  public void windowActivated(WindowEvent e) {


  }
          
  public void windowClosed(WindowEvent e) {

  }
  public void windowClosing(WindowEvent e) {
    hide();
    System.exit(0);
  
  }
  public void windowDeactivated(WindowEvent e) {
  }
  public void windowDeiconified(WindowEvent e) {
  }
  public void windowIconified(WindowEvent e) {
  }
  public void windowOpened(WindowEvent e) {
  }

  ////////////////////
  //Auto iniciación// 
  //////////////////

  public static void main(String args[]) {
    MieleroMain m=new MieleroMain();
  }

}