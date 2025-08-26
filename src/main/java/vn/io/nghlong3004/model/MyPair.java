package vn.io.nghlong3004.model;

import java.sql.Timestamp;

public class MyPair {
  private Timestamp created;
  private Long a;
  private Long b;

  public MyPair(Timestamp created, Long a, Long b) {
    super();
    this.created = created;
    this.a = a;
    this.b = b;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public Long getA() {
    return a;
  }

  public void setA(Long a) {
    this.a = a;
  }

  public Long getB() {
    return b;
  }

  public void setB(Long b) {
    this.b = b;
  }

}
