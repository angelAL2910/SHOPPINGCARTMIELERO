package com.wapcoders.proyectos.MIELERO;

/////////////////////////////////////////////////////////
//ProdReg clase que encapsula los datos de un producto//
//es decir su nombre y su precio                     //
//////////////////////////////////////////////////////

/**
ProdReg clase que encapsula los datos de un producto
es decir su nombre y su precio

@author WapCoders
@version 0.54
*/

public final class ProdReg {

  private String strnombreproducto;
  private double dblprecioproducto;  

   
  //////////////////
  //Constructores//
  ////////////////
  
  //////////////////////////////////////////////////////////////////////////////
  //public ProdReg(String strnombreproducto, double dblprecioproducto)       //
  //Crea una instancia de la clase ProdReg en la que se especifica el nombre// 
  //del producto y el precio del mismo                                     //
  //////////////////////////////////////////////////////////////////////////

  /**
  Crea una instancia de la clase ProdReg en la que se especifica el nombre 
  del producto y el precio del mismo
  */

  public ProdReg(String strnombreproducto, double dblprecioproducto) {

    this.strnombreproducto=strnombreproducto;
    this.dblprecioproducto=dblprecioproducto;
    
 
  }

  ////////////
  //Métodos//
  //////////


  ///////////////////////////////////////////////////////////////
  //public final void setProductName(String strnombreproducto)//
  //Establece un nuevo valor al nombre del producto          //
  ////////////////////////////////////////////////////////////

  /**
  Establece un nuevo valor al nombre del producto 
  */

  public final void setProductName(String strnombreproducto) {
    this.strnombreproducto=strnombreproducto;
  }

  /////////////////////////////////////////
  //public final String getProductName()//
  //Devuelve el nombre del producto    //
  //////////////////////////////////////

  /**
  Devuelve el nombre del producto
  */
 
  public final String getProductName() {
    return this.strnombreproducto;
  }

  /////////////////////////////////////////////////////////
  //public final void setPrice(double dblprecioproducto)//
  //Establece un nuevo valor al precio del producto    //
  //////////////////////////////////////////////////////

  /**
  Establece un nuevo valor al precio del producto
  */

  public final void setPrice(double dblprecioproducto) {
    this.dblprecioproducto=dblprecioproducto;
  }

  ////////////////////////////////////
  //public final double getPrice() //
  //Obtiene el precio del producto//
  /////////////////////////////////

  /**
  Obtiene el precio del producto
  */

  public final double getPrice() {
    return this.dblprecioproducto;
  }
}