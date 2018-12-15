package com.wapcoders.proyectos.MIELERO;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

///////////////////////////////////////////
//MieleroGeneral clase que implementa la//
//seccion General                      //
////////////////////////////////////////

/**
MieleroGeneral clase que implementa la
seccion General  

@author WapCoders
@version 0.54
*/

public final class MieleroGeneral extends JPanel implements Runnable,ActionListener {


  private MieleroProjects mlpproyectos;
  private JTextField jtxttitulo;
  private JRadioButton jrdbservidor;
  private JRadioButton jrdbcorreo;
  private JTextField jtxtservidor;
  private JTextField jtxtcorreo;
  private JTextField jtxtasunto;
  private JTextField jtxtmoneda;
  private ButtonGroup bntggrupo; 
  private JLabel jlbltitulo;
  private JLabel jlblservidor;
  private JLabel jlblcorreo;
  private JLabel jlblasunto;
  private JLabel jlblmoneda;
  private Thread thrhilo;

  //////////////////
  //Constructores//
  ////////////////

  /////////////////////////////////////////
  //public MieleroGeneral()             //
  //Crea el panel de la pestaña General//  
  //////////////////////////////////////

  /**
  Crea el panel de la pestaña General
  */

  public MieleroGeneral() {
    super();
    this.mlpproyectos=null;
    crearinterfaz();   
    thrhilo=new Thread(this);
    thrhilo.start(); 
  }

  ///////////////////////////////////////////////////////////////////
  //public MieleroGeneral(MieleroProjects mlpproyectos)           //
  //Crea el panel de la pestaña General y establece la referencia// 
  //al panel MieleroProjects correspondiente                    //
  ///////////////////////////////////////////////////////////////

  /**
  Crea el panel de la pestaña General y establece la referencia 
  al panel MieleroProjects correspondiente
  */

  public MieleroGeneral(MieleroProjects mlpproyectos) {
    super();
    this.mlpproyectos=mlpproyectos;
    crearinterfaz();
    thrhilo=new Thread(this);
    thrhilo.start();
  }

  ////////////
  //Métodos//
  //////////

  //////////////////////////////////////////////////////////////////////
  //public final void setProjectPanel(MieleroProjects mlpproyectos)  //
  //Establece la referencia al panel MieleroProjects correspondiente//
  ///////////////////////////////////////////////////////////////////
  
  /**
  Establece la referencia al panel MieleroProjects correspondiente
  */
  
  public final void setProjectPanel(MieleroProjects mlpproyectos) {

    this.mlpproyectos=mlpproyectos;

  }  
  /////////////////////////////////////////////////////////////////////
  //public final MieleroProjects getProjectPanel()                  //
  //Devuelve la referencia al panel MieleroProjects correspondiente//
  //////////////////////////////////////////////////////////////////

  /**
  Devuelve la referencia al panel MieleroProjects correspondiente
  */

  public final MieleroProjects getProjectPanel() {

    return this.mlpproyectos;

  }  
 
  /////////////////////////////////////////////////////////////////
  //public final String getPageTitle()                          //
  //Devuelve una cadena con el titulo de la página especificado//
  //////////////////////////////////////////////////////////////

  /**
  Devuelve una cadena con el titulo de la página especificado
  */

  public final String getPageTitle() {

    return jtxttitulo.getText();

  }

  ///////////////////////////////////////////////////
  //public final boolean sendDatastoServer()      //
  //Devuelve un booleano que indica si los datos //
  //seran enviados al servidor (true) o a una   //
  //dirección de correo electronico mediante la//
  //cláusula mailto:                          //
  /////////////////////////////////////////////

  /**
  Devuelve un booleano que indica si los datos 
  seran enviados al servidor (true) o a una   
  dirección de correo electronico mediante la
  cláusula mailto:
  */

  public final boolean sendDatastoServer() {

    return jrdbservidor.isSelected();

  }

  ///////////////////////////////////////////////////
  //public final String getSendtoAddress()        //
  //Devuelve una cadena de con la dirección donde//
  //se enviarán los datos                       //
  ///////////////////////////////////////////////

  /**
  Devuelve una cadena de con la dirección donde
  se enviarán los datos 
  */

  public final String getSendtoAddress() {

    if (jrdbservidor.isSelected()==true) {
      return jtxtservidor.getText();
    }
    else {
      return jtxtcorreo.getText();
    }

  }
  
  //////////////////////////////////////////
  //public final String getEmailSubject()//
  //Devuelve una cadena con el asunto   //
  //del mensaje de correo electrónico  //
  //en el que se enviara el pedido    //
  /////////////////////////////////////

  /**
  Devuelve una cadena con el asunto   
  del mensaje de correo electrónico  
  en el que se enviara el pedido    
  */

  public final String getEmailSubject() {

    return jtxtasunto.getText();

  }

  /////////////////////////////////////////////////////////////////
  //public final String getCurrencyName()                       //
  //Devuelve una cadena con el nombre de la moneda especificada//
  //////////////////////////////////////////////////////////////

  /**
  Devuelve una cadena con el nombre de la moneda especificada
  */

  public final String getCurrencyName() {

    return jtxtmoneda.getText();

  }



  /////////////////////
  //Métodos privados//
  ///////////////////

  /////////////////////////////////////////
  //private void crearinterfaz()        //
  //Procede a crear el interfaz gráfico//
  //de la pestaña general             //
  /////////////////////////////////////
  
  private void crearinterfaz() {
    GridBagLayout gblrejilla = new GridBagLayout();
    GridBagConstraints gbcrestricciones;
    setLayout(gblrejilla);

    jtxttitulo=new JTextField(25);
    jrdbservidor=new JRadioButton("Send data to server");
    jrdbcorreo=new JRadioButton("Send data to email address");
    jtxtservidor=new JTextField(25);
    jtxtcorreo=new JTextField(25);
    jtxtasunto=new JTextField(25);
    jtxtmoneda=new JTextField(25);
    jlbltitulo=new JLabel("Title of page:");
    jlblservidor=new JLabel("Server Address:");
    jlblcorreo=new JLabel("E-Mail Address:");
    jlblasunto=new JLabel("E-Mail Subject:");
    jlblmoneda=new JLabel("Currency Name:");
    jrdbservidor.addActionListener(this);
    jrdbservidor.setSelected(true);
    jrdbcorreo.addActionListener(this);
    jlblcorreo.setEnabled(false);
    jtxtcorreo.setEnabled(false);
    jlblasunto.setEnabled(false);
    jtxtasunto.setEnabled(false);
    bntggrupo=new ButtonGroup();
    bntggrupo.add(jrdbservidor);
    bntggrupo.add(jrdbcorreo);
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=0;
    gbcrestricciones.gridy=0;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);


    add(jlbltitulo);
    gblrejilla.setConstraints(jlbltitulo,gbcrestricciones);
    gbcrestricciones=null;
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=1;
    gbcrestricciones.gridy=0;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);
    add(jtxttitulo);
    gblrejilla.setConstraints(jtxttitulo,gbcrestricciones);
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
    add(jrdbservidor);
    gblrejilla.setConstraints(jrdbservidor,gbcrestricciones);

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
    add(jrdbcorreo);
    gblrejilla.setConstraints(jrdbcorreo,gbcrestricciones);

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
    add(jlblservidor);
    gblrejilla.setConstraints(jlblservidor,gbcrestricciones);
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
    add(jtxtservidor);
    gblrejilla.setConstraints(jtxtservidor,gbcrestricciones);

    gbcrestricciones=null;
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=0;
    gbcrestricciones.gridy=3;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);

    add(jlblcorreo);
    gblrejilla.setConstraints(jlblcorreo,gbcrestricciones);
    gbcrestricciones=null;
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=1;
    gbcrestricciones.gridy=3;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2); 
    add(jtxtcorreo);
    gblrejilla.setConstraints(jtxtcorreo,gbcrestricciones);


    gbcrestricciones=null;
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=0;
    gbcrestricciones.gridy=4;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);

    add(jlblasunto);
    gblrejilla.setConstraints(jlblasunto,gbcrestricciones);
    gbcrestricciones=null;
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=1;
    gbcrestricciones.gridy=4;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2); 
    add(jtxtasunto);
    gblrejilla.setConstraints(jtxtasunto,gbcrestricciones);




    gbcrestricciones=null;
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=0;
    gbcrestricciones.gridy=5;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2);

    add(jlblmoneda);
    gblrejilla.setConstraints(jlblmoneda,gbcrestricciones);
    gbcrestricciones=null;
    gbcrestricciones=new GridBagConstraints();
    gbcrestricciones.gridx=1;
    gbcrestricciones.gridy=5;
    gbcrestricciones.gridwidth=1;
    gbcrestricciones.gridheight=1;
    gbcrestricciones.fill=GridBagConstraints.NONE;
    gbcrestricciones.ipadx=0;
    gbcrestricciones.ipady=0;
    gbcrestricciones.insets = new Insets(2,2,2,2); 
    add(jtxtmoneda);
    gblrejilla.setConstraints(jtxtmoneda,gbcrestricciones);



  }
  private void actualizacontroles() {
    if (mlpproyectos==null) {
      jrdbcorreo.setEnabled(true);
    }
    else if (mlpproyectos.getProjectType()==0) {
      
      jrdbcorreo.setEnabled(true);

    }
    else {
      jrdbservidor.setSelected(true);
      jrdbcorreo.setEnabled(false);
      jtxtservidor.setEnabled(true);
      jlblservidor.setEnabled(true);
      jtxtcorreo.setEnabled(false);
      jlblcorreo.setEnabled(false);
      jtxtasunto.setEnabled(false);
      jlblasunto.setEnabled(false);

    }
  }
    ///////////////
    //INTERFACES//
    /////////////

    public final void run() {
      while(true) {
        try {
          Thread.sleep(200);
        }
        catch(Exception e){}
        actualizacontroles();
      }
      
    }
    public final void actionPerformed(ActionEvent e)  {
    
      if (e.getSource()==jrdbservidor) {

        jtxtservidor.setEnabled(true);
        jlblservidor.setEnabled(true);
        jtxtcorreo.setEnabled(false);
        jlblcorreo.setEnabled(false);
        jtxtasunto.setEnabled(false);
        jlblasunto.setEnabled(false);
        

      } 
      else {

        jtxtservidor.setEnabled(false);
        jlblservidor.setEnabled(false);
        jtxtcorreo.setEnabled(true);
        jlblcorreo.setEnabled(true);
        jtxtasunto.setEnabled(true);
        jlblasunto.setEnabled(true);

      }
    
    }

  }