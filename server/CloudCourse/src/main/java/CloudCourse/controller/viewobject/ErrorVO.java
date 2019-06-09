package CloudCourse.controller.viewobject;

public class ErrorVO {
  private String eid;

  private String time;

  private Integer placeId;

  private String address;

  private Double longitude;

  private Double latitude;

  public String getEid() {
    return eid;
  }

  public void setEid(String eid) {
    this.eid = eid;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Integer getPlaceId() {
    return placeId;
  }

  public void setPlaceId(Integer placeId) {
    this.placeId = placeId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

}
