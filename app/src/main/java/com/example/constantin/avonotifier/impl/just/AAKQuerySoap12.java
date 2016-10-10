package com.example.constantin.avonotifier.impl.just;




//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 5.0.0.2
//
// Created by Quasar Development at 09/10/2016
//
//---------------------------------------------------




import org.ksoap2.HeaderProperty;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;
import org.kxml2.kdom.Element;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AAKQuerySoap12
{
    interface AAKIWcfMethod
    {
        AAKExtendedSoapSerializationEnvelope CreateSoapEnvelope() throws java.lang.Exception;

        java.lang.Object ProcessResult(AAKExtendedSoapSerializationEnvelope __envelope,java.lang.Object result) throws java.lang.Exception;
    }

    String url="http://portalquery.just.ro/query.asmx";

    int timeOut=60000;
    public List< HeaderProperty> httpHeaders;
    public boolean enableLogging;

    AAKIServiceEvents callback;
    public AAKQuerySoap12(){}

    public AAKQuerySoap12 (AAKIServiceEvents callback)
    {
        this.callback = callback;
    }
    public AAKQuerySoap12(AAKIServiceEvents callback,String url)
    {
        this.callback = callback;
        this.url = url;
    }

    public AAKQuerySoap12(AAKIServiceEvents callback,String url,int timeOut)
    {
        this.callback = callback;
        this.url = url;
        this.timeOut=timeOut;
    }

    protected org.ksoap2.transport.Transport createTransport()
    {
        try
        {
            java.net.URI uri = new java.net.URI(url);
            if(uri.getScheme().equalsIgnoreCase("https"))
            {
                int port=uri.getPort()>0?uri.getPort():443;
                return new HttpsTransportSE(uri.getHost(), port, uri.getPath(), timeOut);
            }
            else
            {
                return new HttpTransportSE(url,timeOut);
            }

        }
        catch (java.net.URISyntaxException e)
        {
        }
        return null;
    }
    
    protected AAKExtendedSoapSerializationEnvelope createEnvelope()
    {
        AAKExtendedSoapSerializationEnvelope envelope= new AAKExtendedSoapSerializationEnvelope(AAKExtendedSoapSerializationEnvelope.VER12);
        return envelope;
    }
    
    protected java.util.List sendRequest(String methodName,AAKExtendedSoapSerializationEnvelope envelope,org.ksoap2.transport.Transport transport  )throws java.lang.Exception
    {
        return transport.call(methodName, envelope, httpHeaders);
    }

    java.lang.Object getResult(java.lang.Class destObj,java.lang.Object source,String resultName,AAKExtendedSoapSerializationEnvelope __envelope) throws java.lang.Exception
    {
        if(source==null)
        {
            return null;
        }
        if(source instanceof SoapPrimitive)
        {
            SoapPrimitive soap =(SoapPrimitive)source;
            if(soap.getName().equals(resultName))
            {
                java.lang.Object instance=__envelope.get(source,destObj,false);
                return instance;
            }
        }
        else
        {
            SoapObject soap = (SoapObject)source;
            if (soap.hasProperty(resultName))
            {
                java.lang.Object j=soap.getProperty(resultName);
                if(j==null)
                {
                    return null;
                }
                java.lang.Object instance=__envelope.get(j,destObj,false);
                return instance;
            }
            else if( soap.getName().equals(resultName)) {
                java.lang.Object instance=__envelope.get(source,destObj,false);
                return instance;
            }
       }

       return null;
    }

        
    public String HelloWorld( ) throws java.lang.Exception
    {
        return (String)execute(new AAKIWcfMethod()
        {
            @Override
            public AAKExtendedSoapSerializationEnvelope CreateSoapEnvelope(){
              AAKExtendedSoapSerializationEnvelope __envelope = createEnvelope();
                SoapObject __soapReq = new SoapObject("portalquery.just.ro", "HelloWorld");
                __envelope.setOutputSoapObject(__soapReq);
                
                PropertyInfo __info=null;
                return __envelope;
            }
            
            @Override
            public java.lang.Object ProcessResult(AAKExtendedSoapSerializationEnvelope __envelope,java.lang.Object __result)throws java.lang.Exception {
                SoapObject __soap=(SoapObject)__result;
                java.lang.Object obj = __soap.getProperty("HelloWorldResult");
                if (obj != null && obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    return j.toString();
                }
                else if (obj!= null && obj instanceof String){
                    return (String)obj;
                }
                return null;
            }
        },"portalquery.just.ro/HelloWorld");
    }
    
    public android.os.AsyncTask< Void, Void, AAKOperationResult< String>> HelloWorldAsync()
    {
        return executeAsync(new AAKFunctions.IFunc< String>() {
            public String Func() throws java.lang.Exception {
                return HelloWorld( );
            }
        });
    }
    
    public AAKArrayOfDosar CautareDosare(final String numarDosar,final String obiectDosar,final String numeParte,final AAKEnums.Institutie institutie,final java.util.Date dataStart,final java.util.Date dataStop ) throws java.lang.Exception
    {
        return (AAKArrayOfDosar)execute(new AAKIWcfMethod()
        {
            @Override
            public AAKExtendedSoapSerializationEnvelope CreateSoapEnvelope(){
              AAKExtendedSoapSerializationEnvelope __envelope = createEnvelope();
                SoapObject __soapReq = new SoapObject("portalquery.just.ro", "CautareDosare");
                __envelope.setOutputSoapObject(__soapReq);
                
                PropertyInfo __info=null;
                __info = new PropertyInfo();
                __info.namespace="portalquery.just.ro";
                __info.name="numarDosar";
                __info.type=PropertyInfo.STRING_CLASS;
                __info.setValue(numarDosar!=null?numarDosar:SoapPrimitive.NullSkip);
                __soapReq.addProperty(__info);
                __info = new PropertyInfo();
                __info.namespace="portalquery.just.ro";
                __info.name="obiectDosar";
                __info.type=PropertyInfo.STRING_CLASS;
                __info.setValue(obiectDosar!=null?obiectDosar:SoapPrimitive.NullSkip);
                __soapReq.addProperty(__info);
                __info = new PropertyInfo();
                __info.namespace="portalquery.just.ro";
                __info.name="numeParte";
                __info.type=PropertyInfo.STRING_CLASS;
                __info.setValue(numeParte!=null?numeParte:SoapPrimitive.NullSkip);
                __soapReq.addProperty(__info);
                __info = new PropertyInfo();
                __info.namespace="portalquery.just.ro";
                __info.name="institutie";
                __info.type=PropertyInfo.STRING_CLASS;
                __info.setValue(institutie!=null?institutie.toString():SoapPrimitive.NullNilElement);
                __soapReq.addProperty(__info);
                __info = new PropertyInfo();
                __info.namespace="portalquery.just.ro";
                __info.name="dataStart";
                __info.type=PropertyInfo.STRING_CLASS;
                __info.setValue(dataStart!=null?AAKHelper.getDateTimeFormat().format(dataStart):SoapPrimitive.NullNilElement);
                __soapReq.addProperty(__info);
                __info = new PropertyInfo();
                __info.namespace="portalquery.just.ro";
                __info.name="dataStop";
                __info.type=PropertyInfo.STRING_CLASS;
                __info.setValue(dataStop!=null?AAKHelper.getDateTimeFormat().format(dataStop):SoapPrimitive.NullNilElement);
                __soapReq.addProperty(__info);
                return __envelope;
            }
            
            @Override
            public java.lang.Object ProcessResult(AAKExtendedSoapSerializationEnvelope __envelope,java.lang.Object __result)throws java.lang.Exception {
                return (AAKArrayOfDosar)getResult(AAKArrayOfDosar.class,__result,"CautareDosareResult",__envelope);
            }
        },"portalquery.just.ro/CautareDosare");
    }
    
    public android.os.AsyncTask< Void, Void, AAKOperationResult< AAKArrayOfDosar>> CautareDosareAsync(final String numarDosar,final String obiectDosar,final String numeParte,final AAKEnums.Institutie institutie,final java.util.Date dataStart,final java.util.Date dataStop)
    {
        return executeAsync(new AAKFunctions.IFunc< AAKArrayOfDosar>() {
            public AAKArrayOfDosar Func() throws java.lang.Exception {
                return CautareDosare( numarDosar,obiectDosar,numeParte,institutie,dataStart,dataStop);
            }
        });
    }
    
    public String CautareDosare2(final String numarDosar,final String obiectDosar,final String numeParte,final String institutie,final String dataStart,final String dataStop,final String dataUltimaModificareStart,final String dataUltimaModificareStop ) throws java.lang.Exception
    {
/*This feature is available in Premium account, Check http://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account*/
        return null;    
    }
    
    public android.os.AsyncTask< Void, Void, AAKOperationResult< String>> CautareDosare2Async(final String numarDosar,final String obiectDosar,final String numeParte,final String institutie,final String dataStart,final String dataStop,final String dataUltimaModificareStart,final String dataUltimaModificareStop)
    {
        return executeAsync(new AAKFunctions.IFunc< String>() {
            public String Func() throws java.lang.Exception {
                return CautareDosare2( numarDosar,obiectDosar,numeParte,institutie,dataStart,dataStop,dataUltimaModificareStart,dataUltimaModificareStop);
            }
        });
    }
    
    public String CautareSedinte(final String dataSedinta,final String institutie ) throws java.lang.Exception
    {
/*This feature is available in Premium account, Check http://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account*/
        return null;    
    }
    
    public android.os.AsyncTask< Void, Void, AAKOperationResult< String>> CautareSedinteAsync(final String dataSedinta,final String institutie)
    {
        return executeAsync(new AAKFunctions.IFunc< String>() {
            public String Func() throws java.lang.Exception {
                return CautareSedinte( dataSedinta,institutie);
            }
        });
    }

    
    protected java.lang.Object execute(AAKIWcfMethod wcfMethod,String methodName) throws java.lang.Exception
    {
        org.ksoap2.transport.Transport __httpTransport=createTransport();
        __httpTransport.debug=enableLogging;
        AAKExtendedSoapSerializationEnvelope __envelope=wcfMethod.CreateSoapEnvelope();
        try
        {
            sendRequest(methodName, __envelope, __httpTransport);
            
        }
        finally {
            if (__httpTransport.debug) {
                if (__httpTransport.requestDump != null) {
                    android.util.Log.i("requestDump",__httpTransport.requestDump);    
                    
                }
                if (__httpTransport.responseDump != null) {
                    android.util.Log.i("responseDump",__httpTransport.responseDump);
                }
            }
        }
        java.lang.Object __retObj = __envelope.bodyIn;
        if (__retObj instanceof org.ksoap2.SoapFault){
            org.ksoap2.SoapFault __fault = (org.ksoap2.SoapFault)__retObj;
            throw convertToException(__fault,__envelope);
        }else{
            return wcfMethod.ProcessResult(__envelope,__retObj);
        }
    }
    
    protected < T> android.os.AsyncTask< Void, Void, AAKOperationResult< T>>  executeAsync(final AAKFunctions.IFunc< T> func)
    {
        return new android.os.AsyncTask< Void, Void, AAKOperationResult< T>>()
        {
            @Override
            protected void onPreExecute() {
                callback.Starting();
            };
            @Override
            protected AAKOperationResult< T> doInBackground(Void... params) {
                AAKOperationResult< T> result = new AAKOperationResult< T>();
                try
                {
                    result.Result= func.Func();
                }
                catch(java.lang.Exception ex)
                {
                    ex.printStackTrace();
                    result.Exception=ex;
                }
                return result;
            }
            
            @Override
            protected void onPostExecute(AAKOperationResult< T> result)
            {
                callback.Completed(result);
            }
        }.execute();
    }
        
    java.lang.Exception convertToException(org.ksoap2.SoapFault fault,AAKExtendedSoapSerializationEnvelope envelope)
    {

        return new java.lang.Exception(fault.faultstring);
    }
}


