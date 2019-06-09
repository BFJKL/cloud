package CloudCourse.service.model;

public class CountModel {

  private String address;

  private Double longitude;

  private Double latitude;

  private Integer count;

  private Integer placeId;

  public Integer getPlaceId() {
    return placeId;
  }

  public void setPlaceId(Integer placeId) {
    this.placeId = placeId;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
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
