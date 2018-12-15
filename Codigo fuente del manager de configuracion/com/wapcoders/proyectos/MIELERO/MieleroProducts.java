package com.wapcoders.proyectos.MIELERO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Vector;

////////////////////////////////////////////////////
//MieleroProducts clase que implementa la sección//
//de añadir y quitar productos                  //
/////////////////////////////////////////////////

/**
MieleroProducts clase que implementa la sección
de añadir y quitar productos

@author WapCoders
@version 0.54
*/

public final class MieleroProducts extends JPanel implements ActionListener, ListSelectionListener, Runnable {

  private MieleroProjects mlpproyectos;
  private JList jlstproductos;
  private JPanel jpninferior;
  private JButton jbntanadir;
  private JButton jbnteditar;
  private JButton jbntborrar;
  private Vector vctproductos;
  private DefaultListModel dlmmodelo;
  private Thread thrhilo;

  //////////////////
  //Constructores//
  ////////////////

  //////////////////////////////////////////
  //public MieleroProducts()             //
  //Crea el panel de la pestaña Products//  
  ///////////////////////////////////////

  /**
  Crea el panel de la pestaña Products
  */

  public MieleroProducts() {
    super();
    this.mlpproyectos=null;
    construirinterfaz(); 
    vctproductos=new Vector();
    thrhilo=new Thread(this);
    thrhilo.start(); 
  }

  ////////////////////////////////////////////////////////////////////
  //public MieleroProducts(MieleroProjects mlpproyectos)           //
  //Crea el panel de la pestaña Products y establece la referencia// 
  //al panel MieleroProjects correspondiente                     //
  ////////////////////////////////////////////////////////////////

  /**
  Crea el panel de la pestaña Products y establece la referencia 
  al panel MieleroProjects correspondiente
  */

  public MieleroProducts(MieleroProjects mlpproyectos) {
    super();
    this.mlpproyectos=mlpproyectos;
    construirinterfaz();
    vctproductos=new Vector();
    thrhilo=new Thread(this);
    thrhilo.start(); 
  }

  ////////////
  //Métodos//
  //////////

  //////////////////////////////////////////////////////////////////////
  //public void setProjectPanel(MieleroProjects mlpproyectos)        //
  //Establece la referencia al panel MieleroProjects correspondiente//
  ///////////////////////////////////////////////////////////////////

  /**
  Establece la referencia al panel MieleroProjects correspondiente
  */  
  
  public void setProjectPanel(MieleroProjects mlpproyectos) {

    this.mlpproyectos=mlpproyectos;

  }  
  /////////////////////////////////////////////////////////////////////
  //public MieleroProjects getProjectPanel()                        //
  //Devuelve la referencia al panel MieleroProjects correspondiente//
  //////////////////////////////////////////////////////////////////
  
  /**
  Devuelve la referencia al panel MieleroProjects correspondiente
  */
  public MieleroProjects getProjectPanel() {

    return this.mlpproyectos;

  }  

  ///////////////////////////////////////// 
  //public ProdReg[] getProducts()      //
  //Devuele un array de tipo ProReg con//
  //los productos añadidos            //
  /////////////////////////////////////

  /**
  Devuele un array de tipo ProReg con
  los productos añadidos
  */

  public ProdReg[] getProducts() {

    ProdReg pdrproductos[];
    pdrproductos=new ProdReg[vctproductos.size()];
    for (int i=0;i<vctproductos.size();i++) {
      pdrproductos[i]=(ProdReg)vctproductos.get(i);
    }
    return pdrproductos;
  }

  /////////////////////
  //Métodos privados//
  ///////////////////

  /////////////////////////////////////////
  //private void crearinterfaz()        //
  //Procede a crear el interfaz gráfico//
  //de la pestaña productos           //
  /////////////////////////////////////

  private void construirinterfaz() {

    setLayout(new BorderLayout());
    dlmmodelo=new DefaultListModel();
    jlstproductos=new JList(dlmmodelo);
    jlstproductos.addListSelectionListener(this);
    add(new JScrollPane(jlstproductos),BorderLayout.CENTER);
    jpninferior=new JPanel();
    jpninferior.setLayout(new GridLayout(1,2,2,2));

    jbntanadir=new JButton("Add Product");
    jbntanadir.addActionListener(this);
    jbnteditar=new JButton("Edit Product");
    jbnteditar.setEnabled(false);
    jbnteditar.addActionListener(this);
    jbntborrar=new JButton("Remove Product");
    jbntborrar.setEnabled(false);
    jbntborrar.addActionListener(this);
    jpninferior.add(jbntanadir);
    jpninferior.add(jbnteditar);
    jpninferior.add(jbntborrar);
    add(jpninferior,BorderLayout.SOUTH);

  }




  ////////////////////////////////////////////////////////
  //private void anadirproducto()                      //
  //Procede a mostrar una ventana donde se solicitarán// 
  //los datos de del producto y en el caso oportuno  //
  //añadira el producto en la lista                 //
  ///////////////////////////////////////////////////

  private void anadirproducto() {
    MieleroProductDialog mpddialogo;
    ProdReg prdproducto;
    mpddialogo=new MieleroProductDialog(this);
    mpddialogo.show();
    prdproducto=mpddialogo.getProduct();
    if (prdproducto==null) {
      return;
    }
    vctproductos.addElement(prdproducto);
    dlmmodelo.addElement(prdproducto.getProductName());

    if (mlpproyectos==null && vctproductos.size()>=Constantes.numeromaximodeproductosenweb) {
      jbntanadir.setEnabled(false);
    }
    else if (mlpproyectos.getProjectType()==0 && vctproductos.size()>=Constantes.numeromaximodeproductosenweb) {
      jbntanadir.setEnabled(false);
    }
    else if (mlpproyectos.getProjectType()==1 && vctproductos.size()>=Constantes.numeromaximodeproductosenwap) {
      jbntanadir.setEnabled(false);
    }
    else if (mlpproyectos.getProjectType()==2 && vctproductos.size()>=Constantes.numeromaximodeproductosenwap) {
      jbntanadir.setEnabled(false);
    }

    
  }

  ////////////////////////////////////////////// 
  //private void editarproducto()            //
  //Procede a mostrar una ventana donde     //
  //se puede cambiar el precio del producto//
  //seleccionado                          //
  /////////////////////////////////////////

  private void editarproducto() {
    MieleroProductDialog mpddialogo;
    ProdReg prdproducto;
    
    prdproducto=(ProdReg)vctproductos.get(jlstproductos.getSelectedIndex()); 
    mpddialogo=new MieleroProductDialog(prdproducto.getProductName(),prdproducto.getPrice());
    mpddialogo.show();


    prdproducto=mpddialogo.getProduct();
    if (prdproducto==null) {
      return;
    }
    vctproductos.remove(jlstproductos.getSelectedIndex());
    vctproductos.insertElementAt(prdproducto,jlstproductos.getSelectedIndex());
    
  }
  
  //////////////////////////////////////////////////
  //private void borrarproducto()                //
  //Procede a borra el producto seleccionado    //
  //tras una confirmación por parte del usuario//
  //////////////////////////////////////////////

  private void borrarproducto() {
    int intresultado;

    intresultado=JOptionPane.showConfirmDialog(this,"Are you sure that you want to delete this product?",Constantes.nombreprograma,JOptionPane.YES_NO_OPTION);    
    if (intresultado==JOptionPane.YES_OPTION) {
      vctproductos.remove(jlstproductos.getSelectedIndex());
      dlmmodelo.remove(jlstproductos.getSelectedIndex());
      jbntanadir.setEnabled(true);
    }
  
  }

  ////////////////////////////////////////////////////////////
  //private void actualizarproductos()                     //
  //Procede a borrar los productos que se                 //
  //hayan añadido de más del límite de productos         //
  //permitidos para la página wap y activar o desactivar//
  //el botón añadir si es necesario                    //
  //////////////////////////////////////////////////////

  private void actualizarproductos() {

    if (mlpproyectos!=null && mlpproyectos.getProjectType()==1 && vctproductos.size()>Constantes.numeromaximodeproductosenwap) {
      while(vctproductos.size()>Constantes.numeromaximodeproductosenwap) {
        vctproductos.remove(Constantes.numeromaximodeproductosenwap);
        dlmmodelo.remove(Constantes.numeromaximodeproductosenwap);
      }
      
    }

    if (mlpproyectos==null && vctproductos.size()>=Constantes.numeromaximodeproductosenweb) {
      jbntanadir.setEnabled(false);
    }
    else if (mlpproyectos.getProjectType()==0 && vctproductos.size()>=Constantes.numeromaximodeproductosenweb) {
      jbntanadir.setEnabled(false);
    }
    else if (mlpproyectos.getProjectType()==1 && vctproductos.size()>=Constantes.numeromaximodeproductosenwap) {
      jbntanadir.setEnabled(false);
    }
    else if (mlpproyectos.getProjectType()==2 && vctproductos.size()>=Constantes.numeromaximodeproductosenwap) {
      jbntanadir.setEnabled(false);
    }
    else {
      jbntanadir.setEnabled(true);
    }
  }

  ///////////////
  //INTERFACES//
  /////////////

  public void actionPerformed(ActionEvent e) {

    if (e.getSource()==jbntanadir) {
      anadirproducto();
    }
    else if (e.getSource()==jbnteditar) {
      editarproducto();
    }
    else {
      borrarproducto();
    }

  }
  public void valueChanged(ListSelectionEvent e) {
    JList jlstlista;
    
    jlstlista=(JList)e.getSource();
    if (jlstlista.isSelectionEmpty()) {

      jbnteditar.setEnabled(false);
      jbntborrar.setEnabled(false);
     
    }
    else {
      jbnteditar.setEnabled(true);
      jbntborrar.setEnabled(true);

    }  
  }

  public void run() {
    while(true) {
      try {
        Thread.sleep(200);
      }
      catch(Exception e){}
      actualizarproductos();
    }
  }
}