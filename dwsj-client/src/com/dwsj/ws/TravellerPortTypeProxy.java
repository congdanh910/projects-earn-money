package com.dwsj.ws;

public class TravellerPortTypeProxy implements com.dwsj.ws.TravellerPortType {
  private String _endpoint = null;
  private com.dwsj.ws.TravellerPortType travellerPortType = null;
  
  public TravellerPortTypeProxy() {
    _initTravellerPortTypeProxy();
  }
  
  public TravellerPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initTravellerPortTypeProxy();
  }
  
  private void _initTravellerPortTypeProxy() {
    try {
      travellerPortType = (new com.dwsj.ws.TravellerLocator()).gettravellerHttpSoap11Endpoint();
      if (travellerPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)travellerPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)travellerPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (travellerPortType != null)
      ((javax.xml.rpc.Stub)travellerPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.dwsj.ws.TravellerPortType getTravellerPortType() {
    if (travellerPortType == null)
      _initTravellerPortTypeProxy();
    return travellerPortType;
  }
  
  public java.lang.Integer rateOnInformation(java.lang.Integer userId, java.lang.Integer infoId, java.lang.Integer rate) throws java.rmi.RemoteException{
    if (travellerPortType == null)
      _initTravellerPortTypeProxy();
    return travellerPortType.rateOnInformation(userId, infoId, rate);
  }
  
  public java.lang.Integer commentOnImage(java.lang.Integer userId, java.lang.Integer imageId, java.lang.String comment) throws java.rmi.RemoteException{
    if (travellerPortType == null)
      _initTravellerPortTypeProxy();
    return travellerPortType.commentOnImage(userId, imageId, comment);
  }
  
  public java.lang.String searchPlace(java.lang.String place) throws java.rmi.RemoteException{
    if (travellerPortType == null)
      _initTravellerPortTypeProxy();
    return travellerPortType.searchPlace(place);
  }
  
  public java.lang.Integer rateOnImage(java.lang.Integer userId, java.lang.Integer imageId, java.lang.Integer rate) throws java.rmi.RemoteException{
    if (travellerPortType == null)
      _initTravellerPortTypeProxy();
    return travellerPortType.rateOnImage(userId, imageId, rate);
  }
  
  public java.lang.Integer commentOnInformation(java.lang.Integer userId, java.lang.Integer infoId, java.lang.String comment) throws java.rmi.RemoteException{
    if (travellerPortType == null)
      _initTravellerPortTypeProxy();
    return travellerPortType.commentOnInformation(userId, infoId, comment);
  }
  
  
}