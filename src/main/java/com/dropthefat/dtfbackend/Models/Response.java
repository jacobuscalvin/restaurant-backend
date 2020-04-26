package com.dropthefat.dtfbackend.Models;

import com.fasterxml.jackson.annotation.JsonInclude;

//This class is just a wrapper to send message as response after doing a request.

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
  private boolean status;
  private Long updateTime;
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

  public String getUpdateTime() {
    if(updateTime == null){
      return null;
    }else{
      return updateTime + "ms";
    }
  }

  public void setUpdateTime(Long updateTime) {
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