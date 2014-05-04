/**
 * GuidePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dwsj.ws;

public interface GuidePortType extends java.rmi.Remote {
    public java.lang.Integer deleteImageAndInfo(java.lang.Integer imageId) throws java.rmi.RemoteException;
    public java.lang.Integer deleteInformation(java.lang.Integer infoId) throws java.rmi.RemoteException;
    public java.lang.String listImagesByPlace(java.lang.Integer placeId) throws java.rmi.RemoteException;
    public java.lang.Integer updateInformation(java.lang.Integer infoId, java.lang.String information) throws java.rmi.RemoteException;
    public java.lang.Integer addPlace(java.lang.Integer userId, java.lang.String place, java.lang.String description) throws java.rmi.RemoteException;
    public java.lang.String login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.Integer addImageAndInfo(java.lang.Integer userId, java.lang.Integer placeId, byte[] image, java.lang.String type, java.lang.String information) throws java.rmi.RemoteException;
    public java.lang.Integer register(java.lang.String username, java.lang.String password, java.lang.String fullName) throws java.rmi.RemoteException;
    public java.lang.Integer updateImageAndInfo(java.lang.Integer imageId, java.lang.String information) throws java.rmi.RemoteException;
    public java.lang.Integer addInformation(java.lang.Integer userId, java.lang.Integer placeId, java.lang.String information) throws java.rmi.RemoteException;
}
