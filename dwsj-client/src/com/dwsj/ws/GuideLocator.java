/**
 * GuideLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dwsj.ws;

public class GuideLocator extends org.apache.axis.client.Service implements com.dwsj.ws.Guide {

    public GuideLocator() {
    }


    public GuideLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GuideLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for guideHttpSoap11Endpoint
    private java.lang.String guideHttpSoap11Endpoint_address = "http://localhost:8080/axis2/services/guide.guideHttpSoap11Endpoint/";

    public java.lang.String getguideHttpSoap11EndpointAddress() {
        return guideHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String guideHttpSoap11EndpointWSDDServiceName = "guideHttpSoap11Endpoint";

    public java.lang.String getguideHttpSoap11EndpointWSDDServiceName() {
        return guideHttpSoap11EndpointWSDDServiceName;
    }

    public void setguideHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        guideHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.dwsj.ws.GuidePortType getguideHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(guideHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getguideHttpSoap11Endpoint(endpoint);
    }

    public com.dwsj.ws.GuidePortType getguideHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.dwsj.ws.GuideSoap11BindingStub _stub = new com.dwsj.ws.GuideSoap11BindingStub(portAddress, this);
            _stub.setPortName(getguideHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setguideHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        guideHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.dwsj.ws.GuidePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.dwsj.ws.GuideSoap11BindingStub _stub = new com.dwsj.ws.GuideSoap11BindingStub(new java.net.URL(guideHttpSoap11Endpoint_address), this);
                _stub.setPortName(getguideHttpSoap11EndpointWSDDServiceName());
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
        if ("guideHttpSoap11Endpoint".equals(inputPortName)) {
            return getguideHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.dwsj.com", "guide");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.dwsj.com", "guideHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("guideHttpSoap11Endpoint".equals(portName)) {
            setguideHttpSoap11EndpointEndpointAddress(address);
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
