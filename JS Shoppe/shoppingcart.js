/////
/////
/////		The Shopping Cart Generator v1.1
/////
/////			by Elaine Aquino [ elaineaqs@gmail.com ]
/////
/////
/////
/////	Hi and thank you for downloading the Shopping Cart Generator v1.1
/////	This is my first release of the generator so if you find errors and stuff like that, email me at 
/////	my email address above or better yet, visit AQ Online Networks ( www.aqonlinenetworks.com) for technical 
/////	support and more script downloads.
/////
/////	The Shopping Cart Generator is a JavaScript program that generates a shopping cart
/////	which may be used for e-commerce purposes. It doesn't need any other scripting
/////	languages to run. It works independently on most browsers which are javascript-enabled.
/////	
/////	With Shopping Cart Generator v1.1, you can also work with PHP for processing the
/////	cart information. Variables are identified along with the program.
/////
/////	What's new with this version?
/////
/////		>> You can now specify the currency for your shop
/////		>> Ordered items may now be decreased
/////		>> Ordered items are read-only to prevent changes that can result to errors in the code
/////		>> Status bars in the generator form are now invisible (but they're still there)
/////		>> Updated links
/////		>> Some other little tweaks added to make the script more user-friendly
/////
/////
/////	Version 1.0 features:
/////		
/////		>> Works with most browsers
/////		>> Can be previewed before saving
/////		>> UNLIMITED number of shop products/items
/////		>> Method and Action of submitting form data is customizable
/////		>> Shop Title is customizable
/////		>> Comes with Instructions and Variables Used
/////		>> Filesize is small
/////		>> Shopping Cart is generated in a single file only
/////
/////	Enjoy,
/////	Elaine ^_^
/////
/////
/////	This program is free software; you can redistribute it and/or modify it under
/////	the terms of the GNU General Public License as published by the Free Software 
/////	Foundation.
/////
/////	LICENSE FOR THE SHOPPING CART GENERATOR V1.1
/////	License file: License.htm
/////
/////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function num_of_items()
{	
	var num=prompt('Hi! How many items are you selling?','5');
	var x=0;
	
	if(num==null) {alert('You have chosen to cancel.'); document.replace('Shoppe Generator.html');}
	else {
	if(num<=0) {alert('You cannot input any number less than or equal to zero.'); num_of_items();}
	else alert('You may now add '+num+' item(s) to your shop.');
		}

	document.writeln("<html><head><title> Shopping Cart Generator </title>");
	document.writeln("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\" />");
	document.writeln("<script language=\"javascript\" src=\"shoppingcart.js\"></script></head>");
	document.writeln("<body bgcolor=\"silver\" vlink=blue alink=blue>");
	document.writeln("<font color=blue><h1 align=center> The Shopping Cart Generator </h1></font> ");

     document.writeln("<form name=\"generatorform\"><center>");
     document.writeln("<table border=0 cellpadding=9 cellspacing=0><tr><td>");
     document.writeln("Shop Title <br> <input type=\"text\" name=\"shoptitle\"> <br>");
     document.writeln("<br> Currency: <small>(PHP, USD, etc.)</small> <br> <input type=\"text\" name=\"currency\" value=\"PHP\"> <br>");
     document.writeln("<br> Form Method: <br> <input type=\"text\" name=\"formmethod\"> <br>");
     document.writeln("<br> Form Action: <br> <input type=\"text\" name=\"formaction\"> <br></td>");

     document.writeln("<td>Code for HTML file (Save as any filename)");
     document.writeln("<br> <textarea name=\"code1\" rows=7 cols=40></textarea> <br><br>");
     document.writeln("<input type=\"button\" value=\"Select HTML Code\" onClick=\"generatorform.code1.select();\"> ");
     document.writeln("<input type=\"button\" value=\"Preview\" onClick=\"preview();\"> ");
     document.writeln("<input type=\"reset\" value=\"Refresh\" onClick=\"refresh();\"> ");
     document.writeln("<input type=\"button\" value=\"New\" onClick=\"history.go(-1);\"> ");
     document.writeln("</td></tr></table></center>");

     document.writeln("<input type=\"hidden\" value=\""+num+"\" name=\"totalitems\">");		
     document.writeln("<input type=\"hidden\" value=\""+x+"\" name=\"current_i\">");		

	document.writeln("<br><hr>");
	document.writeln("<center> <input type=\"button\" value=\"Instructions\" onClick=\"instructions()\">");
	document.writeln("<input type=\"button\" value=\"Reminders\" onClick=\"reminders()\">");
	document.writeln("<input type=\"button\" value=\"Variables Used\" onClick=\"variables()\">");
	document.writeln("<input type=\"button\" value=\"Add "+num+" Item(s)\" onClick=\"add()\">");
	document.writeln("<input type=\"button\" value=\"Generate Code!\" onClick=\"generate()\">");
	document.writeln("<input type=\"hidden\" name=\"item_price\" >");
	document.writeln("<input type=\"hidden\" name=\"item_name\" >");
	document.writeln("<hr><br>");
	document.writeln("</center>");

     document.writeln("<br> <p align=\"center\"> <font size=2> Powered by ");
     document.writeln("<a href=\"http://www.aqonlinenetworks.com\">");
     document.writeln("AQ Online Networks</a><br> Script Written by Elaine (elaineaqs@gmail.com)</font> </p> ");

     document.writeln("<br> <p align=\"center\" style=\"visibility: hidden\"><font color=\"#aa0000\">Status Bars: (Do not alternate) </font></p>");
     document.writeln("<center><table border=0 cellpadding=0 cellspacing=0 width=\"500px\" style=\"visibility: hidden\"><tr><td colspan=2>Status (JavaScript):");
     document.writeln("<br><textarea name=\"code2\" rows=1 cols=50></textarea> </td></tr>");
     document.writeln("<tr><td>Status (HTML):<br>");
     document.writeln("<textarea name=\"status1\" rows=1 cols=20></textarea> </td>");
     document.writeln("<td>Status (Variables):<br>");
     document.writeln("<textarea name=\"status2\" rows=1 cols=20></textarea> </td></tr>");

     document.writeln("<tr><td>Status (Function 1):<br>");
     document.writeln("<textarea name=\"status3\" rows=1 cols=20></textarea> </td>");
     document.writeln("<td>Status (Function 2):<br>");
     document.writeln("<textarea name=\"status4\" rows=1 cols=20></textarea> </td></tr>");
     document.writeln("<tr><td>Status (Function 3):<br>");
     document.writeln("<textarea name=\"status5\" rows=1 cols=20></textarea> </td>");
     document.writeln("<td>Status (Function 4):<br>");
     document.writeln("<textarea name=\"status6\" rows=1 cols=20></textarea> </td></tr></table></center>");

     document.writeln("<br> </form></body></html>");

}


function add()
{
	var num=document.generatorform.totalitems.value;
	var x=document.generatorform.current_i.value;
	var px=document.generatorform.item_price.value;
	var nx=document.generatorform.item_name.value;	

	do
	{	x++;
		n=prompt('Name for Item '+x+':','Item '+x);
		if (n!==null) {
		nx=n;

		p=prompt('Price per item of the product, \''+n+'\':',''+x);
		if (p!==null) {
		px=p;
		alert('The product named \''+n+'\' has been added to database.\n\n'+x+' item(s) added.'); 

document.generatorform.current_i.value=x;

document.generatorform.status1.value=document.generatorform.status1.value
+'<input type="text" name="item'+x+'_i" size=2 maxlength=3 readonly=readonly>\n'
+'	&nbsp;&nbsp;&nbsp; '+ document.generatorform.currency.value 
+'  <input type="text" name="item'+x+'" size=10 readonly=readonly>\n'
+'	<input type="button" value="Order" onClick="inc_item'+x+'()">\n'
+'	<input type="button" value=" - " onClick="dec_item'+x+'()">\n'
+'	<input type="hidden" value="'+n+'" name="itemname">\n'
+'	<input type="hidden" value="'+p+'" name="itemprice">\n'
+'	'+ n + ' @ '+ document.generatorform.currency.value +' '+ p +'<br><br>\n\n';

document.generatorform.status2.value=document.generatorform.status2.value
+'item'+x+'_price='+p+';\n';

document.generatorform.status3.value=document.generatorform.status3.value
+'function inc_item'+x+'() \n'
+'	{form1.item'+x+'.value=form1.item'+x+'.value-(-item'+x+'_price);\n '
+'	form1.item'+x+'_i.value=form1.item'+x+'.value/item'+x+'_price;\n '
+'	total_i(); total_a(); }\n\n';

document.generatorform.status4.value=document.generatorform.status4.value
+'function dec_item'+x+'() \n'
+'	{form1.item'+x+'.value=form1.item'+x+'.value-(item'+x+'_price);\n '
+'	form1.item'+x+'_i.value=form1.item'+x+'.value/item'+x+'_price;\n '
+'	total_i(); total_a(); }\n\n';

document.generatorform.status5.value=document.generatorform.status5.value
+'-(-form1.item'+x+'_i.value)';

document.generatorform.status6.value=document.generatorform.status6.value
+'-(-form1.item'+x+'.value)'; }

	else { alert('You did not enter any price for the product named \''+n+'\'. \nNo items are currently in your database. \n'); refresh(); document.generatorform.reset(); document.replace('Shoppe Generator.html');}

		}

	else { alert('You have chosen to cancel. \nNo items are currently in your database. \n'); refresh(); document.generatorform.reset(); document.replace('Shoppe Generator.html');}
	}

	while (x<num);
}

function generate()
{
	var shoptitle=document.generatorform.shoptitle.value;
	var formmethod=document.generatorform.formmethod.value;
	var formaction=document.generatorform.formaction.value;

	var num=document.generatorform.totalitems.value;
	var x=document.generatorform.current_i.value;

	if (x>=num)
	{
		if ((document.generatorform.code1.value=="")&&(document.generatorform.code2.value==""))
		{

document.generatorform.code2.value=document.generatorform.code2.value 
+document.generatorform.status2.value+'\n '
+document.generatorform.status3.value+'\n '
+document.generatorform.status4.value+'\n '
+'function total_i() \n{form1.total_items.value='
+document.generatorform.status5.value+'\n '
+'; }\n\n'
+'function total_a() \n{form1.total_amount.value='
+document.generatorform.status6.value+'\n '
+'; }';	

document.generatorform.code1.value='<html> \n'
+'   <head>\n'
+'   <title> '+shoptitle+' </title>\n'
+'   <meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />\n'
+'   <script language="JavaScript"><!--\n'
+document.generatorform.code2.value
+'//--></script></head> \n'
+'   <body link="#0000ff" vlink="#0000ff" alink="#0000ff"> \n'
+'	<font color="#aa0000">The Shopping Cart &raquo;</font><font color="#ff0000"> '+shoptitle+'</font>\n\n'
+'	<form name="form1" action="'+formaction+'" method="'+formmethod+'">\nItems <br>\n\n'
+ document.generatorform.status1.value 
+' 	\n\n<br><input type="text" name="total_items" size=2 maxlength=3 readonly=readonly> \n'
+'	&nbsp;&nbsp;&nbsp; '+document.generatorform.currency.value+' <input type="text" name="total_amount" size=10 readonly=readonly> Total<br><br> \n\n'
+'   <input type="submit" value="Submit Order"> \n'
+'   <input type="reset" value="Reset Orders"> \n\n\n'
+'   </form>\n\n<!-----------------// DO NOT REMOVE POWERED BY LINK... pretty please? ----------------->\n'
+'   <div align="center"><font size=2>\n'
+'   Powered by <a href="http://www.aqonlinenetworks.com" target=_blank>AQ Online Networks</a></font>\n\n'
+'   </body>\n</html>\n\n\n';


		}
else alert('Do not input anything in the generated code\'s box yet.');
	}
else alert('You have to input items to your cart!');
}

function instructions()
{
	alert('Shopping Cart Generator Instructions\n\nStep 1\n\nEnter your shop\'s name and the form properties necessary for the information to be sent.\n\nStep 2\n\nAdd items/products to your Shop by clicking \'Add n item(s)\'. \nWhere n is the no. of items you want to add.\n\nStep 3\n\nGenerate the Code. Select, copy and paste it to another file or you may Preview it first.\n\nStep 4\n\nIf you want to submit the form to a server, click \'Variables Used\' for more information.\n\n\n*** Thank you for using the Shopping Cart Generator.\n*** For more information, contact the author.');
}

function reminders()
{
	alert('Reminders for better use.\n\n(1) For now, the item price should not be in decimal form because it will calculate erroneously.\n\n(2) Do not alternate the generated code unless you know what you\'re doing and how it works.\n\n(3) When you have already entered N items, you can still add more items IF you haven\'t clicked on the Generate Code button yet.\n\n\n*** Thank you for using the Shopping Cart Generator.\n*** For more information, contact the author.');
}

function variables()
{
	alert('These are the identifications which may be useful for processing the information.\n\nItem Price >> itemprice \n--> Example of use in PHP: $_POST[\'itemprice\']\n\n\nQuantity of Items Per Product >> itemX_i \nWhere X can be any numeric value.\n--> Example of use in PHP: $_POST[\'item2_i\']\n\n\nItem Name >> itemname \n--> Example of use in PHP: $_POST[\'itemname\']\n\n\nTotal Items Ordered >> total_items \n--> Example of use in PHP: $_POST[\'total_items\']\n\n\nTotal Amount of Items Ordered >> total_amount \n--> Example of use in PHP: $_POST[\'total_amount\'] ');
}

function preview() 
{
	win2=window.open("","_blank");
	win2.document.writeln(document.generatorform.code1.value);
}

function refresh()
{
	document.generatorform.current_i.value=0;
}

