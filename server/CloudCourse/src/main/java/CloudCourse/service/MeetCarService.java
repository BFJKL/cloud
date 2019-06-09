package CloudCourse.service;

import CloudCourse.service.model.MeetCarModel;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface MeetCarService {
  List<MeetCarModel> searchMeetCar(String eId) throws IOException, JSONException;
}
