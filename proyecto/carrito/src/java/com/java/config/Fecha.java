 
package com.java.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

 
public class Fecha {
    
    public static Calendar calendar=Calendar.getInstance();
    private static String fecha;

    public Fecha() {
    }
    
    public static String fecha()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        fecha=sdf.format(calendar.getTime());
        return fecha;
    }
    
    public static String fechaBD()
    {
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        fecha=sdf.format(calendar.getTime());
        return fecha;
    }
    
     public static String fechaBDConvert(java.sql.Date fecha)
    {
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
         String strDate = sdf.format(fecha);  
        return strDate;
    }
    
    
}
