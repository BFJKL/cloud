package CloudCourse.service;

import CloudCourse.service.model.TrackModel;

import java.io.IOException;
import java.util.List;

public interface TrackService {

    List<TrackModel> trackBack(Long start, Long end, String eId) throws IOException;

}
