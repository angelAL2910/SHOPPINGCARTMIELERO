package com.wapcoders.proyectos.MIELERO;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.net.*;

/////////////////////////////////////////
//MieleroFinal clase que implementa la//
//sección Final                      //
//////////////////////////////////////

/**
MieleroFinal clase que implementa la
sección Final

@author WapCoders
@version 0.54
*/


public final class MieleroFinal extends JPanel implements ActionListener {

  private MieleroProjects mlpproyectos;  
  private MieleroGeneral mlgeneral; 
  private MieleroProducts mlproductos;
  private JPanel jpnlexaminar;
  private JLabel jlblruta;
  private JTextField jtxtruta;
  private JButton jbtnexaminar;
  private JButton jbtncompilar;

  //////////////////
  //Constructores//
  ////////////////

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //MieleroFinal(MieleroProjects mlpproyectos,MieleroGeneral mlgeneral,MieleroProducts mlproductos)            //
  //Crea una instancia del panel Final y establece una referencia a los paneles MieleroProjects MieleroGeneral//
  //y MieleroProducts                                                                                        //
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  /**
  Crea una instancia del panel Final y establece una referencia a los paneles MieleroProjects MieleroGeneral
  y MieleroProducts    
  */

  public MieleroFinal(MieleroProjects mlpproyectos,MieleroGeneral mlgeneral,MieleroProducts mlproductos) {
    super();
    this.mlpproyectos=mlpproyectos;  
    this.mlgeneral=mlgeneral; 
    this.mlproductos=mlproductos;

    jlblruta=new JLabel("Project Directory:");
    jtxtruta=new JTextField(15);
    jbtnexaminar=new JButton("Browser");
    jbtnexaminar.addActionListener(this);
    jbtncompilar=new JButton("Compile");
    jbtncompilar.addActionListener(this);

    add(jlblruta);
    add(jtxtruta);
    add(jbtnexaminar);
    add(jbtncompilar);



  }

  /////////////////////
  //Métodos privados//
  ///////////////////

  ////////////////////////////////////////////////////
  //private void compilarproyecto()                //
  //Procede a compilar el proyecto tras comprobar //
  //que se han cumplimentado los datos necesarios//
  ////////////////////////////////////////////////

  private void compilarproyecto() {
    URL urlarchivojar;
    File flarchivojar;
    String strarchivojar;
    String strdirectoriodestino;
    File fldirectoriodestino;
    ProdReg prdproductos[]; 

    try {
      urlarchivojar = getClass().getResource("MieleroMain.class");
      strarchivojar=urlarchivojar.toString();
      strarchivojar=strarchivojar.substring(10);
      strarchivojar=strarchivojar.substring(0,strarchivojar.lastIndexOf("!/com/wapcoders"));
      strarchivojar=URLDecoder.decode(strarchivojar);

      flarchivojar=new File(strarchivojar);
      strarchivojar=flarchivojar.getCanonicalPath();
      strarchivojar=strarchivojar.substring(0,(strarchivojar.length()-(File.separator + "MIELERO.jar").length()));

      if (mlgeneral.getPageTitle().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "You must introduce a title of page", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      }
      if (mlgeneral.sendDatastoServer()==true && mlgeneral.getSendtoAddress().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "You must introduce the path of a webserver script", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      }
      if (mlgeneral.sendDatastoServer()==false && isValidEmail(mlgeneral.getSendtoAddress())==false) {
        JOptionPane.showMessageDialog(this, "You must introduce a valid e-mail address", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      }
      if (mlgeneral.getCurrencyName().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "You must introduce a currency name", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      }      
      prdproductos=mlproductos.getProducts();
      if (prdproductos.length<1) {
        JOptionPane.showMessageDialog(this, "You must introduce some products", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);

      }  
    
      strdirectoriodestino=jtxtruta.getText();
      if (strdirectoriodestino.trim().equals("")) {
        JOptionPane.showMessageDialog(this, "You must introduce a valid directory path", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      } 
      if (strdirectoriodestino.indexOf(File.pathSeparator)!=-1) {
        JOptionPane.showMessageDialog(this, "The directory path contains no valid characters", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;

      }
      fldirectoriodestino=new File(strdirectoriodestino);
      if (fldirectoriodestino.getCanonicalPath().indexOf(strarchivojar)==0) {
        JOptionPane.showMessageDialog(this,"The specific directory path is not valid", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;
      }

      if (fldirectoriodestino.exists()==false || fldirectoriodestino.isDirectory()==false) {
        JOptionPane.showMessageDialog(this,"The specific directory path not exists", Constantes.nombreprograma, JOptionPane.WARNING_MESSAGE);
        return;
      } 
      if (mlpproyectos.getProjectType()==0) {
        fldirectoriodestino=new File(fldirectoriodestino,"web");
        compilarweb(strarchivojar,fldirectoriodestino.getCanonicalPath());
      }
      else if (mlpproyectos.getProjectType()==1) {
        fldirectoriodestino=new File(fldirectoriodestino,"wap");
        compilarwap(strarchivojar,fldirectoriodestino.getCanonicalPath());
      }
      else {
        fldirectoriodestino=new File(fldirectoriodestino,"web");
        compilarweb(strarchivojar,fldirectoriodestino.getCanonicalPath());
        fldirectoriodestino=new File(fldirectoriodestino,"../wap");
        compilarwap(strarchivojar,fldirectoriodestino.getCanonicalPath());
      }
      JOptionPane.showMessageDialog(this,"The compilation has finished succesfully", Constantes.nombreprograma, JOptionPane.INFORMATION_MESSAGE);

    }
    catch (IOException e) {

      JOptionPane.showMessageDialog(this, "An Input/Output error has been happened", Constantes.nombreprograma, JOptionPane.ERROR_MESSAGE);
    }



  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //private void compilarweb(String strdirectorioaplicacion,String strdirectoriodestino) throws IOException//
  //Procede a crear los archivos catalog.html y order.html con los datos suministrados por el usuario     //
  /////////////////////////////////////////////////////////////////////////////////////////////////////////

  private void compilarweb(String strdirectorioaplicacion,String strdirectoriodestino) throws IOException {
    File fldirectorio;
    File flarchivo;
    Vector vctlineas;
    String strlinea;
    String strlineas[]; 
    String strscriptcabecera;
    String strformulariopedido; 
    ProdReg pdrproductos[];  
    FileReader flrcanal;
    BufferedReader bralmacen;
    FileWriter flwcanal;
    BufferedWriter bwalmacen;

    //VERIFICAMOS SI EXISTE EL DIRECTORIO DE DESTINO Y SI NO LO CREAMOS
    fldirectorio=new File(strdirectoriodestino);
    if (fldirectorio.exists()==false) {
      fldirectorio.mkdirs();
    } 
    else if (fldirectorio.exists()==true && fldirectorio.isDirectory()==false) {
      fldirectorio.delete();
      fldirectorio.mkdirs();
    } 

    //LEEMOS EL ARCHIVO CATALOG.HTML
    vctlineas=new Vector();
    flarchivo=new File(strdirectorioaplicacion + File.separator + "plantilla web" + File.separator +"catalog.html");
    flrcanal=new FileReader(flarchivo);
    bralmacen=new BufferedReader(flrcanal);
    strlinea="";
    while(strlinea!=null) {
      strlinea=bralmacen.readLine();
      if (strlinea!=null) {
        vctlineas.add(strlinea);
      }
    }
    bralmacen.close();
    flrcanal.close();
    //LO VOLCAMOS A UN ARRAY DE TIPO STRING
    strlineas=new String[vctlineas.size()];
    for (int i=0;i<vctlineas.size();i++) {
      strlineas[i]=(String)vctlineas.get(i);
    }    

    //CREAMOS EL SCRIPT DE LA CABECERA Y EL FORMULARIO DE PRODUCTOS

    pdrproductos=mlproductos.getProducts();
    strscriptcabecera="<script language=\"JavaScript\">\r\n";
    strscriptcabecera=strscriptcabecera+"<!--\r\n";
    for (int i=0;i<pdrproductos.length;i++) {
      strscriptcabecera=strscriptcabecera+"var item" + (i+1) + "_price=" + pdrproductos[i].getPrice() + ";\r\n";
      strscriptcabecera=strscriptcabecera+"var item" + (i+1) + "_name=\"" + pdrproductos[i].getProductName() + "\";\r\n";

    }
    strscriptcabecera=strscriptcabecera+"var items_number=" + pdrproductos.length +";\r\n";

    strscriptcabecera=strscriptcabecera+"function xround(num,ndec) {\r\n";
    strscriptcabecera=strscriptcabecera+"var fact=1;\r\n";
    strscriptcabecera=strscriptcabecera+"for (var i=1;i<=ndec;i++) {\r\n";
    strscriptcabecera=strscriptcabecera+"fact*=10;\r\n";
    strscriptcabecera=strscriptcabecera+"}\r\n";
    strscriptcabecera=strscriptcabecera+"return Math.round(num*fact)/fact\r\n";
    strscriptcabecera=strscriptcabecera+"}\r\n";


    for (int i=0;i<pdrproductos.length;i++) {

      strscriptcabecera=strscriptcabecera+"function inc_item"+(i+1)+"() {\r\n";
      strscriptcabecera=strscriptcabecera+"if (parseInt(form1.item"+ (i+1) + "_i.value)<99) {\r\n";
      strscriptcabecera=strscriptcabecera+"form1.item"+(i+1) + ".value=xround(form1.item" + (i+1) + ".value-(-item" + (i+1) + "_price),2);\r\n";
      strscriptcabecera=strscriptcabecera+"form1.item" +(i+1) +"_i.value=Math.round(form1.item" + (i+1) + ".value/item" + (i+1) + "_price)\r\n";
      strscriptcabecera=strscriptcabecera+"total_i()\r\n"; 
      strscriptcabecera=strscriptcabecera+"total_a()\r\n";
      strscriptcabecera=strscriptcabecera+"}\r\n"; 
      strscriptcabecera=strscriptcabecera+"}\r\n";
     
      strscriptcabecera=strscriptcabecera+"function dec_item"+(i+1)+"() {\r\n"; 
      strscriptcabecera=strscriptcabecera+"form1.item"+(i+1)+".value=xround(form1.item"+(i+1)+".value-(item"+(i+1)+"_price),2);\r\n";
      strscriptcabecera=strscriptcabecera+"form1.item"+(i+1)+"_i.value=Math.round(form1.item"+(i+1)+".value/item"+(i+1)+"_price);\r\n";
      strscriptcabecera=strscriptcabecera+"if (parseInt(form1.item"+(i+1)+"_i.value)<=0) {\r\n";
      strscriptcabecera=strscriptcabecera+"form1.item"+(i+1)+"_i.value=\"0\";\r\n";
      strscriptcabecera=strscriptcabecera+"form1.item"+(i+1)+".value=\"0\";\r\n";
      strscriptcabecera=strscriptcabecera+"}\r\n";
      strscriptcabecera=strscriptcabecera+"total_i();\r\n"; 
      strscriptcabecera=strscriptcabecera+"total_a();\r\n"; 
      strscriptcabecera=strscriptcabecera+"}\r\n";

    }
    strscriptcabecera=strscriptcabecera+"function total_i() {\r\n";
    strscriptcabecera=strscriptcabecera+"form1.total_items.value=";
    for (int i=0;i<pdrproductos.length;i++) {
    strscriptcabecera=strscriptcabecera+"-(-form1.item"+(i+1)+"_i.value)";
    } 
    strscriptcabecera=strscriptcabecera+";\r\n}\r\n";


    strscriptcabecera=strscriptcabecera+"function total_a() {\r\n";
    strscriptcabecera=strscriptcabecera+"form1.total_amount.value=";
    for (int i=0;i<pdrproductos.length;i++) {
    strscriptcabecera=strscriptcabecera+"-(-form1.item"+(i+1)+".value)";
    } 
    strscriptcabecera=strscriptcabecera+";\r\n}\r\n";



    strscriptcabecera=strscriptcabecera+"//-->\r\n";
    strscriptcabecera=strscriptcabecera+"</script>\r\n";

    strformulariopedido="<form name=\"form1\">\r\n";
    strformulariopedido=strformulariopedido+"Items <br>\r\n";
    
    for (int i=0;i<pdrproductos.length;i++) {

      strformulariopedido=strformulariopedido+"<input type=\"text\" name=\"item"+ (i+1) +"_i\" size=2 maxlength=3 readonly=readonly value=\"0\">\r\n";
      strformulariopedido=strformulariopedido+"&nbsp;&nbsp;&nbsp; "+mlgeneral.getCurrencyName()+"  <input type=\"text\" name=\"item"+(i+1)+"\" size=10 readonly=readonly value=\"0\">\r\n";
      strformulariopedido=strformulariopedido+"<input type=\"button\" value=\"Order\" onClick=\"inc_item"+(i+1)+"()\">\r\n";
      strformulariopedido=strformulariopedido+"<input type=\"button\" value=\" - \" onClick=\"dec_item"+(i+1)+"()\">\r\n";
      strformulariopedido=strformulariopedido+"<input type=\"hidden\" value=\""+ pdrproductos[i].getProductName() +"\" name=\"itemname\">\r\n";
      strformulariopedido=strformulariopedido+"<input type=\"hidden\" value=\""+ pdrproductos[i].getPrice()+"\" name=\"itemprice\">\r\n";
      strformulariopedido=strformulariopedido+pdrproductos[i].getProductName()+ " @ " + mlgeneral.getCurrencyName() + " " + pdrproductos[i].getPrice() + " <br><br>\r\n";
    }      
    strformulariopedido=strformulariopedido+"<br><input type=\"text\" name=\"total_items\" size=2 maxlength=3 readonly=readonly value=\"0\">\r\n"; 
    strformulariopedido=strformulariopedido+"&nbsp;&nbsp;&nbsp; " + mlgeneral.getCurrencyName()  +" <input type=\"text\" name=\"total_amount\" size=10 readonly=readonly value=\"0\"> Total<br><br>\r\n"; 
    strformulariopedido=strformulariopedido+"<input type=\"button\" value=\"Submit Order\" onclick=\"storeorder();\">\r\n"; 
    strformulariopedido=strformulariopedido+"<input type=\"reset\" value=\"Reset Orders\">\r\n"; 
    strformulariopedido=strformulariopedido+"</form>\r\n";
  
    //SUSTITUIMOS LAS ETIQUETAS QUE MARCAN LAS DISTINTAS PARTES DEL CÓDIGO POR SU CÓDIGO OPORTUNO

    for (int i=0;i<strlineas.length;i++) {

      strlineas[i]=strlineas[i].replaceAll("<!--TITULO DE LA TIENDA-->",mlgeneral.getPageTitle());
      strlineas[i]=strlineas[i].replaceAll("<!--SCRIPTS DE CABECERA-->",strscriptcabecera);
      strlineas[i]=strlineas[i].replaceAll("<!--FORMULARIO DE PRODUCTOS-->",strformulariopedido);

    }
   
    //VOLCAMOS AL ARCHIVO CATALOG.HTML EN EL DIRECTORIO DE DESTINO
   
    flarchivo=new File(strdirectoriodestino + File.separator + "catalog.html");
    flwcanal=new FileWriter(flarchivo);
    bwalmacen=new BufferedWriter(flwcanal);
    for (int i=0;i<strlineas.length;i++) {
      bwalmacen.write(strlineas[i],0,strlineas[i].length());
      bwalmacen.newLine();

    }
    bwalmacen.flush();
    bwalmacen.close(); 
    flwcanal.close();
    
    //LIBERAMOS LA MEMORIA DE LOS OBJETOS NO UTILIZADOS

    flarchivo=null;
    vctlineas=null;
    strlinea=null;
    strlineas=null; 
    strscriptcabecera=null;
    strformulariopedido=null; 
    pdrproductos=null;  
    flrcanal=null;
    bralmacen=null;
    flwcanal=null;
    bwalmacen=null;

    //LEEMOS EL ARCHIVO ORDER.HTML
    vctlineas=new Vector();
    flarchivo=new File(strdirectorioaplicacion + File.separator + "plantilla web" + File.separator +"order.html");
    flrcanal=new FileReader(flarchivo);
    bralmacen=new BufferedReader(flrcanal);
    strlinea="";
    while(strlinea!=null) {
      strlinea=bralmacen.readLine();
      if (strlinea!=null) {
        vctlineas.add(strlinea);
      }
    }
    bralmacen.close();
    flrcanal.close();
    //LO VOLCAMOS A UN ARRAY DE TIPO STRING
    strlineas=new String[vctlineas.size()];
    for (int i=0;i<vctlineas.size();i++) {
      strlineas[i]=(String)vctlineas.get(i);
    }    

    //CREAMOS EL SCRIPT DE LA CABECERA Y LA ETIQUETA DE INICIO DEL FORMULARIO DE ENVIO DE DATOS
    strscriptcabecera="<script language=\"JavaScript\">\r\n";
    strscriptcabecera=strscriptcabecera+"<!--\r\n";
    strscriptcabecera=strscriptcabecera+"var currency_name=\""+mlgeneral.getCurrencyName()+"\";\r\n";
    strscriptcabecera=strscriptcabecera+"//-->\r\n";
    strscriptcabecera=strscriptcabecera+"</script>\r\n";

    strformulariopedido="<form action=\"";
    if (mlgeneral.sendDatastoServer()==true) {
      strformulariopedido=strformulariopedido+mlgeneral.getSendtoAddress() + "\" method=\"post\" onsubmit=\"return validatedatas(this);\">\r\n";
    }
    else {
      strformulariopedido=strformulariopedido+"mailto:"+ mlgeneral.getSendtoAddress(); 
      if (mlgeneral.getEmailSubject().trim().equals("")==false) {
        strformulariopedido=strformulariopedido+"?subject=" + mlgeneral.getEmailSubject();
      }
      strformulariopedido=strformulariopedido+"\" method=\"post\" enctype=\"text/plain\" onsubmit=\"return validatedatas(this);\">\r\n";

    }
  //SUSTITUIMOS LAS ETIQUETAS QUE MARCAN LAS DISTINTAS PARTES DEL CÓDIGO POR SU CÓDIGO OPORTUNO

    for (int i=0;i<strlineas.length;i++) {

      strlineas[i]=strlineas[i].replaceAll("<!--TITULO DE LA TIENDA-->",mlgeneral.getPageTitle());
      strlineas[i]=strlineas[i].replaceAll("<!--SCRIPTS DE CABECERA-->",strscriptcabecera);
      strlineas[i]=strlineas[i].replaceAll("<!--CABECERA FORMULARIO ENVIO-->",strformulariopedido);

    }
   
    //VOLCAMOS AL ARCHIVO ORDER.HTML EN EL DIRECTORIO DE DESTINO
   
    flarchivo=new File(strdirectoriodestino + File.separator + "order.html");
    flwcanal=new FileWriter(flarchivo);
    bwalmacen=new BufferedWriter(flwcanal);
    for (int i=0;i<strlineas.length;i++) {
      bwalmacen.write(strlineas[i],0,strlineas[i].length());
      bwalmacen.newLine();

    }
    bwalmacen.flush();
    bwalmacen.close(); 
    flwcanal.close();
    
    //LIBERAMOS LA MEMORIA DE LOS OBJETOS NO UTILIZADOS

    flarchivo=null;
    vctlineas=null;
    strlinea=null;
    strlineas=null; 
    strscriptcabecera=null;
    strformulariopedido=null; 
    pdrproductos=null;  
    flrcanal=null;
    bralmacen=null;
    flwcanal=null;
    bwalmacen=null;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //private void compilarwap(String strdirectorioaplicacion,String strdirectoriodestino) throws IOException     //
  //Procede a compilar los ficheros catalogxxxxx.wlm, order.wml y MiscCatalog.wmls con los valores introducidos//
  //por el usuario                                                                                            //
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////// 

  private void compilarwap(String strdirectorioaplicacion,String strdirectoriodestino) throws IOException {

    File fldirectorio;
    File flarchivo;
    Vector vctlineas;
    String strlinea;
    String strlineas[]; 
    ProdReg pdrproductos[];  
    FileReader flrcanal;
    BufferedReader bralmacen;
    FileWriter flwcanal;
    BufferedWriter bwalmacen;
    int intnumeropagina;

    //VERIFICAMOS SI EXISTE EL DIRECTORIO DE DESTINO Y SI NO LO CREAMOS
    fldirectorio=new File(strdirectoriodestino);
    if (fldirectorio.exists()==false) {
      fldirectorio.mkdirs();
    } 
    else if (fldirectorio.exists()==true && fldirectorio.isDirectory()==false) {
      fldirectorio.delete();
      fldirectorio.mkdirs();
    } 

    //LEEMOS EL ARCHIVO ORDER.WML
    vctlineas=new Vector();
    flarchivo=new File(strdirectorioaplicacion + File.separator + "plantilla wap" + File.separator +"order.wml");
    flrcanal=new FileReader(flarchivo);
    bralmacen=new BufferedReader(flrcanal);
    strlinea="";
    while(strlinea!=null) {
      strlinea=bralmacen.readLine();
      if (strlinea!=null) {
        vctlineas.add(strlinea);
      }
    }
    bralmacen.close();
    flrcanal.close();
    //LO VOLCAMOS A UN ARRAY DE TIPO STRING
    strlineas=new String[vctlineas.size()];
    for (int i=0;i<vctlineas.size();i++) {
      strlineas[i]=(String)vctlineas.get(i);
    } 

    //SUSTITUIMOS LAS ETIQUETAS QUE MARCAN LAS DISTINTAS PARTES DEL CÓDIGO POR SU CÓDIGO OPORTUNO

    for (int i=0;i<strlineas.length;i++) {
      
      strlineas[i]=strlineas[i].replaceAll("<!--TITULO DE LA TIENDA-->",mlgeneral.getPageTitle());
      strlineas[i]=strlineas[i].replaceAll("<!--DIRECCION DEL SERVIDOR-->",mlgeneral.getSendtoAddress());

    }
       
    
    //VOLCAMOS AL ARCHIVO ORDER.WML EN EL DIRECTORIO DE DESTINO
   
    flarchivo=new File(strdirectoriodestino + File.separator + "order.wml");
    flwcanal=new FileWriter(flarchivo);
    bwalmacen=new BufferedWriter(flwcanal);
    for (int i=0;i<strlineas.length;i++) {
      bwalmacen.write(strlineas[i],0,strlineas[i].length());
      bwalmacen.newLine();

    }
    bwalmacen.flush();
    bwalmacen.close(); 
    flwcanal.close();
    
    //LIBERAMOS LA MEMORIA DE LOS OBJETOS NO UTILIZADOS

    flarchivo=null;
    vctlineas=null;
    strlinea=null;
    strlineas=null; 
    pdrproductos=null;  
    flrcanal=null;
    bralmacen=null;
    flwcanal=null;
    bwalmacen=null;

    //LEEMOS EL ARCHIVO MISCCATALOG.WMLS
    vctlineas=new Vector();
    flarchivo=new File(strdirectorioaplicacion + File.separator + "plantilla wap" + File.separator +"MiscCatalog.wmls");
    flrcanal=new FileReader(flarchivo);
    bralmacen=new BufferedReader(flrcanal);
    strlinea="";
    while(strlinea!=null) {
      strlinea=bralmacen.readLine();
      if (strlinea!=null) {
        vctlineas.add(strlinea);
      }
    }
    bralmacen.close();
    flrcanal.close();
    //LO VOLCAMOS A UN ARRAY DE TIPO STRING
    strlineas=new String[vctlineas.size()];
    for (int i=0;i<vctlineas.size();i++) {
      strlineas[i]=(String)vctlineas.get(i);
    } 

    //SUSTITUIMOS LAS ETIQUETAS QUE MARCAN LAS DISTINTAS PARTES DEL CÓDIGO POR SU CÓDIGO OPORTUNO

    pdrproductos=mlproductos.getProducts();

    for (int i=0;i<strlineas.length;i++) {

      strlineas[i]=strlineas[i].replaceAll("<!--NUMERO DE PRODUCTOS-->",""+pdrproductos.length+";");

    }
       
    
    //VOLCAMOS AL ARCHIVO MISCCATALOG.WMLS EN EL DIRECTORIO DE DESTINO
   
    flarchivo=new File(strdirectoriodestino + File.separator + "MiscCatalog.wmls");
    flwcanal=new FileWriter(flarchivo);
    bwalmacen=new BufferedWriter(flwcanal);
    for (int i=0;i<strlineas.length;i++) {
      bwalmacen.write(strlineas[i],0,strlineas[i].length());
      bwalmacen.newLine();

    }
    bwalmacen.flush();
    bwalmacen.close(); 
    flwcanal.close();
    
    //LIBERAMOS LA MEMORIA DE LOS OBJETOS NO UTILIZADOS

    flarchivo=null;
    vctlineas=null;
    strlinea=null;
    strlineas=null; 
    pdrproductos=null;  
    flrcanal=null;
    bralmacen=null;
    flwcanal=null;
    bwalmacen=null;

   

   //CREAMOS LAS PAGINAS CORRESPONDIENTES A LOS PRODUCTOS AÑADIDO


    
    intnumeropagina=0;
    pdrproductos=mlproductos.getProducts();
    vctlineas=new Vector();
   
    for (int i=0;i<pdrproductos.length;i++) {
      if (i%2==0) {
        intnumeropagina++;
        strlinea="";
        strlinea=strlinea+"<?xml version=\"1.0\"?>\r\n";
        strlinea=strlinea+"<!DOCTYPE wml PUBLIC \"-//WAPFORUM//DTD WML 1.1//EN\"\r\n"; 
        strlinea=strlinea+"\"http://www.wapforum.org/DTD/wml_1.1.xml\">\r\n"; 
        strlinea=strlinea+"<wml>\r\n";
      }
      if (i==0) {
        strlinea=strlinea+"<card id=\"catalogo\" title=\""+mlgeneral.getPageTitle()+"\">\r\n";
        strlinea=strlinea+"<p>\r\n";
        strlinea=strlinea+"<big>The Shopping Cart &#62;&#62; " +mlgeneral.getPageTitle() +"</big>\r\n";
        strlinea=strlinea+"</p>\r\n";
        strlinea=strlinea+"<p>\r\n";
        strlinea=strlinea+"<anchor>\r\n";
        strlinea=strlinea+"Enter\r\n";
        strlinea=strlinea+"<go href=\"#catalogo2\">\r\n";
        for (int z=0;z<pdrproductos.length;z++) {
          strlinea=strlinea+"<setvar name=\"item"+(z+1)+"\" value=\"0\" />\r\n";
          strlinea=strlinea+"<setvar name=\"precio"+(z+1)+"\" value=\"0\" />\r\n";
        }
        strlinea=strlinea+"<setvar name=\"preciototal\" value=\"0\" />\r\n";
        strlinea=strlinea+"<setvar name=\"pedido\" value=\"\" />\r\n";
        strlinea=strlinea+"</go>\r\n";
        strlinea=strlinea+"</anchor>\r\n";
        strlinea=strlinea+"</p>\r\n";
        strlinea=strlinea+"</card>\r\n";


      } 
      strlinea=strlinea+"<card id=\"catalogo"+(i%2+2)+"\" title=\""+mlgeneral.getPageTitle()+"\">\r\n";
      strlinea=strlinea+"<p>\r\n";
      strlinea=strlinea+"$(item"+(i+1)+") " + mlgeneral.getCurrencyName() +" $(precio" +(i+1)+") <br />\r\n";
      strlinea=strlinea+"<a href=\"MiscCatalog.wmls#incpro(" + (i+1) + "," + pdrproductos[i].getPrice() + ")\">Order</a> <a href=\"MiscCatalog.wmls#decpro(" + (i+1) + "," +pdrproductos[i].getPrice() + ")\">-</a> <br />\r\n";
      strlinea=strlinea+pdrproductos[i].getProductName() + " @ " +mlgeneral.getCurrencyName() + " " + pdrproductos[i].getPrice() + "\r\n";
      strlinea=strlinea+"</p>\r\n";
      strlinea=strlinea+"<p align=\"center\">\r\n";

      if (i==pdrproductos.length-1) {
         strlinea=strlinea+"<br />\r\n";
         strlinea=strlinea+"<anchor>\r\n";
         strlinea=strlinea+"Submit Order\r\n";
         strlinea=strlinea+"<go href=\"MiscCatalog.wmls#storeorder()\" />\r\n";
         strlinea=strlinea+"</anchor>\r\n";
      }
      else if (i<pdrproductos.length-1) {

        if (i%2==0) {
         
           strlinea=strlinea+"<anchor>\r\n";
           strlinea=strlinea+"Next\r\n";
           strlinea=strlinea+"<go href=\"#catalogo3\" />\r\n";
           strlinea=strlinea+"</anchor>\r\n";        
     
        }
        else {
          strlinea=strlinea+"<anchor>\r\n";
          strlinea=strlinea+"Next\r\n";
          strlinea=strlinea+"<go href=\"catalog"+(intnumeropagina+1)+".wml\" />\r\n";
          strlinea=strlinea+"</anchor>\r\n";        

        }

      }
      if (i>=1) {
         strlinea=strlinea+"<anchor>\r\n";
         strlinea=strlinea+"Back\r\n";
         strlinea=strlinea+"<prev />\r\n";
         strlinea=strlinea+"</anchor>";
      }
      strlinea=strlinea+"</p>\r\n";
      if (i==pdrproductos.length-1) {

        strlinea=strlinea+"<p align=\"center\">\r\n";
        strlinea=strlinea+"<br />\r\n";
        strlinea=strlinea+"Total: $(preciototal) " + mlgeneral.getCurrencyName() +"\r\n";
        strlinea=strlinea+"</p>\r\n";
      }

      if (i%2==1 || i==pdrproductos.length-1) {
        strlinea=strlinea+"<p align=\"center\"><br /><small>\r\n";
        strlinea=strlinea+"Powered by <a href=\"http://www.aqonlinenetworks.com\">AQ Online Networks</a> and <a href=\"http://www.wapcoders.com\">Wap Coders</a></small>\r\n";
        strlinea=strlinea+"</p>\r\n";
        strlinea=strlinea+"</card>\r\n";
        strlinea=strlinea+"</wml>\r\n"; 
        vctlineas.add(strlinea);
      }
      else {
        strlinea=strlinea+"</card>\r\n";

      }

    }

    //LO VOLCAMOS EN LOS ARCHIVOS CATALOG.WML OPORTUNOS

    for (int i=0;i<vctlineas.size();i++) {
      if (i==0) {
        flarchivo=new File(strdirectoriodestino + File.separator + "catalog.wml");

      }
      else {
        flarchivo=new File(strdirectoriodestino + File.separator + "catalog"+(i+1)+".wml");

      }
      flwcanal=new FileWriter(flarchivo);
      bwalmacen=new BufferedWriter(flwcanal);
      strlinea=(String)vctlineas.get(i);
      bwalmacen.write(strlinea,0,strlinea.length());

      bwalmacen.flush();
      bwalmacen.close(); 
      flwcanal.close();
    }

    //LIBERAMOS LA MEMORIA DE LOS OBJETOS NO UTILIZADOS

    flarchivo=null;
    vctlineas=null;
    strlinea=null;
    strlineas=null; 
    pdrproductos=null;  
    flrcanal=null;
    bralmacen=null;
    flwcanal=null;
    bwalmacen=null;      



  }



  ////////////////////////////////////////////////////////////
  //private boolean isValidEmail(String strcorreo)         //
  //Devueve un booleano para indicar si la dirección de   //
  //correo electrónico especificada es válida (true) o no//
  ////////////////////////////////////////////////////////

  private boolean isValidEmail(String strcorreo) {
    
    int intpos;
    int intpos2;

    if (strcorreo==null) {
      return false;
    }    

    intpos=strcorreo.indexOf("@");
    if (intpos==-1) return false;
    if (intpos==0 || intpos==strcorreo.length()-1) return false;
    intpos2=strcorreo.indexOf(".");
    if (intpos2<intpos+2 || intpos2==strcorreo.length()-1) return false;
    for (int i=0;i<strcorreo.length();i++) {
      if (strcorreo.charAt(i)==' ' || strcorreo.charAt(i)=='\t' || strcorreo.charAt(i)=='\n') return false;
    }
    return true;

  }


  ///////////////
  //INTERFACES//
  /////////////

  public void actionPerformed(ActionEvent e) {
    JFileChooser jfchdirectorio;
    int retval;

    if (e.getSource()==jbtncompilar) {
    
      compilarproyecto();

    }
    else {
      jfchdirectorio = new JFileChooser(); 
      jfchdirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
      retval=jfchdirectorio.showDialog(new JFrame(),Constantes.titulodialogodirectorio);
      if (retval==JFileChooser.CANCEL_OPTION) {
        return;
      }
      jtxtruta.setText(jfchdirectorio.getSelectedFile().getAbsolutePath()); 

    }

  } 

}