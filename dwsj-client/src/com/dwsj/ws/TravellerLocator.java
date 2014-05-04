/**
 * TravellerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dwsj.ws;

public class TravellerLocator extends org.apache.axis.client.Service implements com.dwsj.ws.Traveller {

    public TravellerLocator() {
    }


    public TravellerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TravellerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for travellerHttpSoap11Endpoint
    private java.lang.String travellerHttpSoap11Endpoint_address = "http://localhost:8080/axis2/services/traveller.travellerHttpSoap11Endpoint/";

    public java.lang.String gettravellerHttpSoap11EndpointAddress() {
        return travellerHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String travellerHttpSoap11EndpointWSDDServiceName = "travellerHttpSoap11Endpoint";

    public java.lang.String gettravellerHttpSoap11EndpointWSDDServiceName() {
        return travellerHttpSoap11EndpointWSDDServiceName;
    }

    public void settravellerHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        travellerHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.dwsj.ws.TravellerPortType gettravellerHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(travellerHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return gettravellerHttpSoap11Endpoint(endpoint);
    }

    public com.dwsj.ws.TravellerPortType gettravellerHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.dwsj.ws.TravellerSoap11BindingStub _stub = new com.dwsj.ws.TravellerSoap11BindingStub(portAddress, this);
            _stub.setPortName(gettravellerHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void settravellerHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        travellerHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.dwsj.ws.TravellerPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.dwsj.ws.TravellerSoap11BindingStub _stub = new com.dwsj.ws.TravellerSoap11BindingStub(new java.net.URL(travellerHttpSoap11Endpoint_address), this);
                _stub.setPortName(gettravellerHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("travellerHttpSoap11Endpoint".equals(inputPortName)) {
            return gettravellerHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.dwsj.com", "traveller");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.dwsj.com", "travellerHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("travellerHttpSoap11Endpoint".equals(portName)) {
            settravellerHttpSoap11EndpointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
