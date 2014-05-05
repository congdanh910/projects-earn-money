/**
 * TravellerPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dwsj.ws;

public interface TravellerPortType extends java.rmi.Remote {
    public java.lang.Integer rateOnInformation(java.lang.Integer userId, java.lang.Integer infoId, java.lang.Integer rate) throws java.rmi.RemoteException;
    public java.lang.String listCommentByImage(java.lang.Integer imageId) throws java.rmi.RemoteException;
    public java.lang.Integer commentOnImage(java.lang.Integer userId, java.lang.Integer imageId, java.lang.String comment) throws java.rmi.RemoteException;
    public java.lang.String listRateByImage(java.lang.Integer imageId) throws java.rmi.RemoteException;
    public java.lang.String searchPlace(java.lang.String place) throws java.rmi.RemoteException;
    public java.lang.Integer rateOnImage(java.lang.Integer userId, java.lang.Integer imageId, java.lang.Integer rate) throws java.rmi.RemoteException;
    public java.lang.Integer commentOnInformation(java.lang.Integer userId, java.lang.Integer infoId, java.lang.String comment) throws java.rmi.RemoteException;
}
