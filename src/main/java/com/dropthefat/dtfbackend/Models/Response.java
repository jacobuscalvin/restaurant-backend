package com.dropthefat.dtfbackend.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.cloud.Timestamp;

//This class is just a wrapper to send message as response after doing a request.

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
  private boolean status;
  private Timestamp updateTime;
  private String message;
  private String docId;

  public Response(){}

  public Response(boolean status, String message){
    this.status = status;
    this.message = message;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDocId() {
    return docId;
  }

  public void setDocId(String docId) {
    this.docId = docId;
  }

  
}