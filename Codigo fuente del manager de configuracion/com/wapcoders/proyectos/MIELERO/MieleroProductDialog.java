package com.wapcoders.proyectos.MIELERO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/////////////////////////////////////////////////////////
//MieleroProductDialog implementa un cuadro de dialogo//
//para obtener los datos de los productos            //
//////////////////////////////////////////////////////

/**
MieleroProductDialog implementa un cuadro de dialogo
para obtener los datos de los productos

@author WapCoders
@version 0.54
*/

public final class MieleroProductDialog extends JDialog implements ActionListener {

  private MieleroProducts mlproductos;
  private ProdReg prdproducto;
  private boolean bolnuevoproducto;
  private JLabel jlblnombreproducto;
  private JLabel jlblprecioproducto;
  private JTextField jtxtnombreproducto;
  private JTextField jtxtprecioproducto;
  private JButton jcmdaceptar;
  private JButton jcmdcerrar;


  //////////////////
  //Constructores//
  ////////////////

  ////////////////////////////////////////////////////////////////
  //public MieleroProductDialog(MieleroProducts mlproductos)   //
  //Procede a crear una ventana de dialogo para introducir los//
  //datos de un producto nuevo                               // 
  ////////////////////////////////////////////////////////////

  /**
  Procede a crear una ventana de dialogo para introducir los
  datos de un producto nuevo
  */

  public MieleroProductDialog(MieleroProducts mlproductos) {
    super(new JFrame(),Constantes.nombreprograma,true);
    this.mlproductos=mlproductos;
    crearinterfaz();
    prdproducto=null;
    bolnuevoproducto=true;
  }


  /////////////////////////////////////////////////////////////////////
  //public MieleroProductDialog(String nombreproducto,double precio)//
  //Procede a crear una ventana de dialogo para editar los datos de// 
  //un producto                                                   // 
  /////////////////////////////////////////////////////////////////

  /**
  Procede a crear una ventana de dialogo para editar los datos de 
  un producto 
  */
  
  public MieleroProductDialog(String nombreproducto,double precio) {

    super(new JFrame(),Constantes.nombreprograma,true);
    crearinterfaz();
    jtxtnombreproducto.setText(nombreproducto);
    jtxtprecioproducto.setText(""+ precio);
    jlblnombreproducto.setEnabled(false);
    jtxtnombreproducto.setEnabled(false);
    prdproducto=null;
    bolnuevoproducto=false;
  }

  ////////////
  //Métodos//
  //////////

  //////////////////////////////////////////
  //public ProdReg getProduct()          //
  //Devuelve un objeto con los datos del//
  //productos añadido o modificado o un// 
  //null si se pulsa el bótón cerrar  //
  /////////////////////////////////////

  /**
  Devuelve un objeto con los datos del
  productos añadido o modificado o un 
  null si se pulsa el bótón cerrar
  */

  public ProdReg getProduct() {
    return prdproducto;
  }

  /////////////////////
  //Métodos privados//
  ///////////////////
  

  ////////////////////////////////////////////////
  //private void crearinterfaz()               //
  //Procede a crear el interfaz gráfico       //
  //del cuadro de diálogo de anadir productos//
  ////////////////////////////////////////////

  private void crearinterfaz() {
    GridBagLayout gblrejilla;
    GridBagConstraints gbcrestricciones;
    setSize(Constantes.anchodialogo,Constantes.altodialogo);
    gblrejilla=new GridBagLayout();
    getContentPane().setLayout(gblrejilla);
    jlblnombreproducto=new JLabel("Product Name:");
    jlblprecioproducto=new JLabel("Product Price:");
    jtxtnombreproducto=new JTextField(15);
    jtxtprecioproducto=new JTextField(15);
    jcmdaceptar=new JButton("Accept");
    jcmdaceptar.addActionListener(this);
    jcmdcerrar=new JButton("Cancel");
    jcmdcerrar.addActionListener(this);

    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=0;
    gbcrestricciones.gridy=0;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);
    getContentPane().add(jlblnombreproducto);
    gblrejilla.setConstraints(jlblnombreproducto,gbcrestricciones);
    gbcrestricciones=null;
    
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=1;
    gbcrestricciones.gridy=0;
    gbcrestricciones.gridwidth=2;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);
    gblrejilla.setConstraints(jtxtnombreproducto,gbcrestricciones);
    getContentPane().add(jtxtnombreproducto);
    gbcrestricciones=null;
    
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=0;
    gbcrestricciones.gridy=1;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);
    getContentPane().add(jlblprecioproducto);
    gblrejilla.setConstraints(jlblprecioproducto,gbcrestricciones);
    gbcrestricciones=null;
    
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=1;
    gbcrestricciones.gridy=1;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);
    getContentPane().add(jtxtprecioproducto);
    gblrejilla.setConstraints(jtxtprecioproducto,gbcrestricciones);
    gbcrestricciones=null;

    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=0;
    gbcrestricciones.gridy=2;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);
    getContentPane().add(jcmdaceptar);
    gblrejilla.setConstraints(jcmdaceptar,gbcrestricciones);
    gbcrestricciones=null;
    
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=1;
    gbcrestricciones.gridy=2;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);
    getContentPane().add(jcmdcerrar);
    gblrejilla.setConstraints(jcmdcerrar,gbcrestricciones);


  }

  /////////////////////////////////
  //private void cerrardialogo()//  
  //Procede a cerrar el dialogo//
  //////////////////////////////

  private void cerrardialogo() {

    hide();

  }

  //////////////////////////////////////////////
  //private void verificardatos()            //
  //Procede a verificar si los datos        //
  //introducidos son correctos y en caso   //
  //afimativo crea un objeto ProdReg que  //
  //se puede obtener en el método público//
  //getProduct y cierra la ventana      //
  ///////////////////////////////////////

  private void verificardatos() {
    double dblprecio;
    String strnombreproducto;
    ProdReg pdrproductos[];
    try {
      if (jtxtnombreproducto.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "You must introduce a valid product name", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      }

      if (jtxtnombreproducto.getText().length()>35) {
        JOptionPane.showMessageDialog(this, "The maxlength of product name must be 35 characters", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      }

      if (bolnuevoproducto==true) {
        pdrproductos=mlproductos.getProducts();
        for (int i=0;i<pdrproductos.length;i++) {
          if (jtxtnombreproducto.getText().toUpperCase().equals(pdrproductos[i].getProductName().toUpperCase())) {
            JOptionPane.showMessageDialog(this, "The product name already exists", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
            return;
          }
        }
      }

      if (jtxtprecioproducto.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "You must introduce a valid product price", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      }
      dblprecio=Double.parseDouble(jtxtprecioproducto.getText()); 
      if (dblprecio<=0) {
        JOptionPane.showMessageDialog(this, "You must introduce a valid product price", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;
      }
      prdproducto=new ProdReg(jtxtnombreproducto.getText(),dblprecio);
      hide();
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(this, "You must introduce a valid product price", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);

    }

  }

  ///////////////
  //INTERFACES//
  /////////////
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource()==jcmdaceptar) {
      verificardatos();      
    }
    else {
      cerrardialogo();
    }
  }
}