package com.dwsj.ws;

public class GuidePortTypeProxy implements com.dwsj.ws.GuidePortType {
  private String _endpoint = null;
  private com.dwsj.ws.GuidePortType guidePortType = null;
  
  public GuidePortTypeProxy() {
    _initGuidePortTypeProxy();
  }
  
  public GuidePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initGuidePortTypeProxy();
  }
  
  private void _initGuidePortTypeProxy() {
    try {
      guidePortType = (new com.dwsj.ws.GuideLocator()).getguideHttpSoap11Endpoint();
      if (guidePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)guidePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)guidePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (guidePortType != null)
      ((javax.xml.rpc.Stub)guidePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.dwsj.ws.GuidePortType getGuidePortType() {
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType;
  }
  
  public java.lang.Integer deleteImageAndInfo(java.lang.Integer imageId) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.deleteImageAndInfo(imageId);
  }
  
  public java.lang.Integer deleteInformation(java.lang.Integer infoId) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.deleteInformation(infoId);
  }
  
  public java.lang.String listImagesByPlace(java.lang.Integer placeId) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.listImagesByPlace(placeId);
  }
  
  public java.lang.Integer updateInformation(java.lang.Integer infoId, java.lang.String information) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.updateInformation(infoId, information);
  }
  
  public java.lang.String placeInfo(java.lang.Integer placeId) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.placeInfo(placeId);
  }
  
  public java.lang.Integer addPlace(java.lang.Integer userId, java.lang.String place, java.lang.String description, byte[] placeImage, java.lang.String type) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.addPlace(userId, place, description, placeImage, type);
  }
  
  public java.lang.String searchPlaceByUser(java.lang.Integer userId) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.searchPlaceByUser(userId);
  }
  
  public java.lang.String login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.login(username, password);
  }
  
  public java.lang.Integer addImageAndInfo(java.lang.Integer userId, java.lang.Integer placeId, byte[] image, java.lang.String type, java.lang.String information) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.addImageAndInfo(userId, placeId, image, type, information);
  }
  
  public java.lang.Integer register(java.lang.String username, java.lang.String password, java.lang.String fullName, java.lang.Integer guide) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.register(username, password, fullName, guide);
  }
  
  public java.lang.String allPlace() throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.allPlace();
  }
  
  public java.lang.Integer updateImageAndInfo(java.lang.Integer imageId, java.lang.String information) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.updateImageAndInfo(imageId, information);
  }
  
  public java.lang.Integer addInformation(java.lang.Integer userId, java.lang.Integer placeId, java.lang.String information) throws java.rmi.RemoteException{
    if (guidePortType == null)
      _initGuidePortTypeProxy();
    return guidePortType.addInformation(userId, placeId, information);
  }
  
  
}