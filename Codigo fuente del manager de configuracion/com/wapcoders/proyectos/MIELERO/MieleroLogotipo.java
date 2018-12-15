package com.wapcoders.proyectos.MIELERO;
import java.awt.*;
import java.net.*;

////////////////////////////////////////////////  
//Clase que implementa la imagen del logotipo// 
//del programa                              //
/////////////////////////////////////////////

/**
Clase que implementa la imagen del logotipo 
del programa

@author WapCoders
@version 0.54
*/

public final class MieleroLogotipo extends Canvas {
  
  
  private Image imglogotipo;

  //////////////////
  //Constructores//
  ////////////////

  //////////////////////////////////  
  //public MieleroLogotipo()     //
  //Crea una instancia de canvas//
  //del logotipo               //
  //////////////////////////////

  /**
  Crea una instancia de canvas
  del logotipo
  */

  public MieleroLogotipo() {
    
    super();
    URL urlimagen;
    Toolkit tlkventana;
    try {
      urlimagen=getClass().getResource("imagenes/logotipo.jpg"); 
      if (urlimagen!=null) {
        tlkventana=Toolkit.getDefaultToolkit();
        imglogotipo=tlkventana.getImage(urlimagen);
      }
    }
    catch(Exception e) {
    }   
  }
  
  ////////////
  //Métodos//
  //////////

  //////////////////////////////////////////////
  //public void paint(Graphics g)            //
  //Procede a dibujar la imagén del logotipo//
  //al cargar el canvas                    //
  //////////////////////////////////////////

  /**
  Procede a dibujar la imagén del logotipo
  al cargar el canvas
  */

  public void paint(Graphics g) {
    super.paint(g);
    if (imglogotipo!=null) {
      g.drawImage(imglogotipo,0,0,getWidth(),getHeight(),this);
    }
  }

  //////////////////////////////////////////////
  //public void update(Graphics g)           //
  //Procede a dibujar la imagén del logotipo//
  //al refrescar el canvas                 //
  //////////////////////////////////////////

  /**
  Procede a dibujar la imagén del logotipo
  al refrescar el canvas
  */

  public void update(Graphics g) {
    super.update(g);
    if (imglogotipo!=null) {
      g.drawImage(imglogotipo,0,0,getWidth(),getHeight(),this);
    }
  }


}