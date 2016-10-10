package com.example.constantin.avonotifier.impl.just;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 5.0.0.2
//
// Created by Quasar Development at 09/10/2016
//
//---------------------------------------------------


import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class AAKDosar extends AttributeContainer implements KvmSerializable
{

    
    public AAKArrayOfDosarParte parti;
    
    public AAKArrayOfDosarSedinta sedinte;
    
    public AAKArrayOfDosarCaleAtac caiAtac;
    
    public String numar;
    
    public String numarVechi;
    
    public java.util.Date data;
    
    public AAKEnums.Institutie institutie=AAKEnums.Institutie.CurteadeApelBUCURESTI;
    
    public String departament;
    
    public AAKEnums.CategorieCaz categorieCaz;
    
    public AAKEnums.StadiuProcesual stadiuProcesual;
    
    public String obiect;
    
    public java.util.Date dataModificare;
    
    public String categorieCazNume;
    
    public String stadiuProcesualNume;

    public AAKDosar ()
    {
    }

    public AAKDosar (java.lang.Object paramObj,AAKExtendedSoapSerializationEnvelope __envelope)
    {
	    
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        if(inObj instanceof SoapObject)
        {
            SoapObject soapObject=(SoapObject)inObj;
            int size = soapObject.getPropertyCount();
            for (int i0=0;i0< size;i0++)
            {
                //if you have compilation error here, please use a ksoap2.jar and ExKsoap2.jar from libs folder (in the generated zip file)
                PropertyInfo info=soapObject.getPropertyInfo(i0);
                java.lang.Object obj = info.getValue(); 
                if (info.name.equals("parti"))
                {
                    if(obj!=null)
                    {
                        java.lang.Object j = obj;
                        this.parti = new AAKArrayOfDosarParte(j,__envelope);
                    }
                    continue;
                }
                if (info.name.equals("sedinte"))
                {
                    if(obj!=null)
                    {
                        java.lang.Object j = obj;
                        this.sedinte = new AAKArrayOfDosarSedinta(j,__envelope);
                    }
                    continue;
                }
                if (info.name.equals("caiAtac"))
                {
                    if(obj!=null)
                    {
                        java.lang.Object j = obj;
                        this.caiAtac = new AAKArrayOfDosarCaleAtac(j,__envelope);
                    }
                    continue;
                }
                if (info.name.equals("numar"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.numar = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.numar = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("numarVechi"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.numarVechi = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.numarVechi = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("data"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.data = AAKHelper.ConvertFromWebService(j.toString());
                            }
                        }
                        else if (obj instanceof java.util.Date){
                            this.data = (java.util.Date)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("institutie"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.institutie = AAKEnums.Institutie.fromString(j.toString());
                            }
                        }
                        else if (obj instanceof AAKEnums.Institutie){
                            this.institutie = (AAKEnums.Institutie)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("departament"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.departament = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.departament = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("categorieCaz"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.categorieCaz = AAKEnums.CategorieCaz.fromString(j.toString());
                            }
                        }
                        else if (obj instanceof AAKEnums.CategorieCaz){
                            this.categorieCaz = (AAKEnums.CategorieCaz)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("stadiuProcesual"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.stadiuProcesual = AAKEnums.StadiuProcesual.fromString(j.toString());
                            }
                        }
                        else if (obj instanceof AAKEnums.StadiuProcesual){
                            this.stadiuProcesual = (AAKEnums.StadiuProcesual)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("obiect"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.obiect = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.obiect = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("dataModificare"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.dataModificare = AAKHelper.ConvertFromWebService(j.toString());
                            }
                        }
                        else if (obj instanceof java.util.Date){
                            this.dataModificare = (java.util.Date)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("categorieCazNume"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.categorieCazNume = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.categorieCazNume = (String)obj;
                        }
                    }
                    continue;
                }
                if (info.name.equals("stadiuProcesualNume"))
                {
                    if(obj!=null)
                    {
        
                        if (obj.getClass().equals(SoapPrimitive.class))
                        {
                            SoapPrimitive j =(SoapPrimitive) obj;
                            if(j.toString()!=null)
                            {
                                this.stadiuProcesualNume = j.toString();
                            }
                        }
                        else if (obj instanceof String){
                            this.stadiuProcesualNume = (String)obj;
                        }
                    }
                    continue;
                }

            }

        }



    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.parti!=null?this.parti:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.sedinte!=null?this.sedinte:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.caiAtac!=null?this.caiAtac:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.numar!=null?this.numar:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.numarVechi!=null?this.numarVechi:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.data!=null?AAKHelper.getDateTimeFormat().format(this.data):SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.institutie!=null?this.institutie.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.departament!=null?this.departament:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==8)
        {
            return this.categorieCaz!=null?this.categorieCaz.toString():SoapPrimitive.NullNilElement;
        }
        if(propertyIndex==9)
        {
            return this.stadiuProcesual!=null?this.stadiuProcesual.toString():SoapPrimitive.NullNilElement;
        }
        if(propertyIndex==10)
        {
            return this.obiect!=null?this.obiect:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==11)
        {
            return this.dataModificare!=null?AAKHelper.getDateTimeFormat().format(this.dataModificare):SoapPrimitive.NullSkip;
        }
        if(propertyIndex==12)
        {
            return this.categorieCazNume!=null?this.categorieCazNume:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==13)
        {
            return this.stadiuProcesualNume!=null?this.stadiuProcesualNume:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 14;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "parti";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "sedinte";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "caiAtac";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "numar";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "numarVechi";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "data";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "institutie";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "departament";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==8)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "categorieCaz";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==9)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "stadiuProcesual";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==10)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "obiect";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==11)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "dataModificare";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==12)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "categorieCazNume";
            info.namespace= "portalquery.just.ro";
        }
        if(propertyIndex==13)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "stadiuProcesualNume";
            info.namespace= "portalquery.just.ro";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}
