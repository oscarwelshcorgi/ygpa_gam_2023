/* 330 Unicode MDI */
function IE_CreateMiPlatformCtrl_MXU(strID)
{
   document.write(" <object classid=\"clsid:9070C3BF-877E-49CC-AAD0-A02389EEEB13\" id=\""+strID+"\" width=\"1024px\" height=\"768px\" visible=\"true\" > ");
   document.write(" </object>    ");
}

/* 330 Unicode SDI */
function IE_CreateMiPlatformCtrl_XU(strID)
{
   document.write(" <object classid=\"clsid:EC3500BB-63AF-45E4-9CBE-C126C77A28B5\" id=\""+strID+"\" width=\"1024px\" height=\"768px\" visible=\"true\" > ");
   document.write(" </object>    ");
}

/* 330 Ansi MDI */
function IE_CreateMiPlatformCtrl_MX(strID)
{
   document.write(" <object classid=\"clsid:4ECAA69A-64B9-4DA6-A28A-407658770B61\" id=\""+strID+"\" width=\"1024px\" height=\"768px\" visible=\"true\" > ");
   document.write(" </object>    ");
}

/* 330 Ansi SDI */
function IE_CreateMiPlatformCtrl_X(strID)
{
   document.write(" <object classid=\"clsid:6CCC09A1-3835-427E-BAEC-A92D216A1557\" id=\""+strID+"\" width=\"1024px\" height=\"768px\" visible=\"true\" > ");
   document.write(" </object>    ");
}


function IE_CreateMiInstlr(strID, Device,Version,Key, ctxRoot)
{
	document.write("<object id=\""+strID+"\" classid=\"clsid:7BA60C95-4CDD-4c3e-9D9A-81CD3BE0B144\" border=\"0\" width=\"0\" height=\"0\" CodeBase=\""+ctxRoot+"install/330/MiUpdater331.cab#VERSION=2010,10,12,1\" >" +
		"<PARAM NAME=\"DeviceType\" VALUE=\""+Device+"\" >" +
		"<PARAM NAME=\"Version\" VALUE=\""+Version+"\" >" +
		"<PARAM NAME=\"key\" VALUE=\""+Key+"\" >" +
		"</object>" );
}

  
function IE_ObjectMiInstlr(strID, Device,Version,Key)
{
	document.write("<object id=\""+strID+"\" classid=\"clsid:7BA60C95-4CDD-4c3e-9D9A-81CD3BE0B144\" border=\"0\" width=\"0\" height=\"0\" SHOWASTEXT>" +
		"<PARAM NAME=\"DeviceType\" VALUE=\""+Device+"\" >" +
		"<PARAM NAME=\"Version\" VALUE=\""+Version+"\" >" +
		"<PARAM NAME=\"key\" VALUE=\""+Key+"\" >" +
		"</object>" );
}  
    


/* 330 Unicode SDI */
function ST_CreateMiPlatformCtrlPlugin330_XU(strID)
{
  document.write(" <embed type=\"application/mozilla-MiPlatformX330U-plugin\"  id=\""+strID+"\" width=\"1024px\" height=\"768px\" visible=\"true\" > ");
}

/* 330 Ansi SDI */
function ST_CreateMiPlatformCtrlPlugin330_XA(strID)
{
  document.write(" <embed type=\"application/mozilla-MiPlatformX330-plugin\"  id=\""+strID+"\" width=\"1024px\" height=\"768px\" visible=\"true\" > ");
}

/* 330 MiInstaller330 */
function ST_CreateMiInstlr(strID, Device,Version, strKey)
{ 	
		document.write("<embed id=\""+strID+"\" type=\"application/mozilla-MiInstaller330-plugin\" border=\"0\" width=\"0\" height=\"0\" >" +
		"<PARAM NAME=\"DeviceType\" VALUE=\""+Device+"\" >" +
		"<PARAM NAME=\"Version\" VALUE=\""+Version+"\" >" +
		"<PARAM NAME=\"Key\" VALUE=\""+strKey+"\" >" +
		"</embed>" );
}
