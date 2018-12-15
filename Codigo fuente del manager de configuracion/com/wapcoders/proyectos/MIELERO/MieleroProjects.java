package com.wapcoders.proyectos.MIELERO;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.net.URL;

/////////////////////////////////////////
//MieleroProjects clase que implementa//
//la selección de tipo de proyecto   //
//////////////////////////////////////

/**
MieleroProjects clase que implementa
la selección de tipo de proyecto

@author WapCoders
@version 0.54
*/


public final class MieleroProjects extends JPanel implements ListSelectionListener {

  JList jllstproyectos;
  JLabel labelimagenproyecto;

  //////////////////
  //Constructores//  
  //////////////// 

  //////////////////////////////////////////
  //public void MieleroProjects()        //
  //Crea el panel de la pestaña Projects//
  ///////////////////////////////////////

  /**
  Crea el panel de la pestaña Projects
  */

  public MieleroProjects() {

    super();
    String[] strproyectos = new String[3];
    strproyectos[0]="Web Proyects";
    strproyectos[1]="Wap Proyects";
    strproyectos[2]="Both Proyects";
    jllstproyectos=new JList(strproyectos);
    jllstproyectos.setSelectedIndex(0);
    jllstproyectos.addListSelectionListener(this);
    setLayout(new BorderLayout());
    add(new JScrollPane(jllstproyectos),BorderLayout.WEST);
    labelimagenproyecto=new JLabel();
    labelimagenproyecto.setVerticalAlignment(JLabel.CENTER);
    labelimagenproyecto.setHorizontalAlignment(JLabel.CENTER);  
    cargarimagen();
    add(labelimagenproyecto,BorderLayout.CENTER);
      
  }
  ////////////
  //Métodos//
  //////////

  //////////////////////////////////////////
  //public final int getProjectType()    //
  //Devuelve el tipo de proyecto elegido//
  //0 web 1 wap 2 ambos                //
  //////////////////////////////////////

  /**
  Devuelve el tipo de proyecto elegido
  0 web 1 wap 2 ambos
  */

  public final int getProjectType() {
    return jllstproyectos.getSelectedIndex(); 
  }

  /////////////////////
  //Métodos privados//
  ///////////////////

  /////////////////////////////////////
  //private void cargarimagen()     //
  //Carga la imagen correspondiente//
  //al proyecto seleccionado      //
  /////////////////////////////////

  private void cargarimagen() {
 
    try {

      URL urlimagen;
      ImageIcon imgproyecto;
      int numerodeimagen;
      String strnombreimagenes[]=new String[3];
      
      strnombreimagenes[0]="web.jpg";
      strnombreimagenes[1]="wap.jpg";
      strnombreimagenes[2]="Both.jpg";

      numerodeimagen=getProjectType();
      urlimagen=getClass().getResource("imagenes/" + strnombreimagenes[numerodeimagen]); 
      if (urlimagen!=null) {
        imgproyecto=new ImageIcon(urlimagen);
        labelimagenproyecto.setIcon(imgproyecto);
      }      

    }
    catch(Exception e) {
      
    }    
  
  }

  ///////////////
  //INTERFACES//
  /////////////
  
  public final void valueChanged(ListSelectionEvent e)  {

    cargarimagen();

  }
}

  