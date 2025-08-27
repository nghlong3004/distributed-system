package vn.io.nghlong3004.model;

import java.sql.Timestamp;

public class Message {
  private Long id;
  private Long pubId;
  private Long subId;
  private Timestamp created;
  private String messagePub;
  private String namePub;
  private String messageSub;
  private String nameSub;

  public Message() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPubId() {
    return pubId;
  }

  public void setPubId(Long pubId) {
    this.pubId = pubId;
  }

  public Long getSubId() {
    return subId;
  }

  public void setSubId(Long subId) {
    this.subId = subId;
  }

  public String getMessagePub() {
    return messagePub;
  }

  public void setMessagePub(String messagePub) {
    this.messagePub = messagePub;
  }

  public String getNamePub() {
    return namePub;
  }

  public void setNamePub(String namePub) {
    this.namePub = namePub;
  }

  public String getMessageSub() {
    return messageSub;
  }

  public void setMessageSub(String messageSub) {
    this.messageSub = messageSub;
  }

  public String getNameSub() {
    return nameSub;
  }

  public void setNameSub(String nameSub) {
    this.nameSub = nameSub;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }


}
