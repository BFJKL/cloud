package CloudCourse.service;

import CloudCourse.service.model.CountModel;

import java.io.IOException;
import java.util.List;

public interface CountService {
  List<CountModel> countCar(Integer placeId) throws IOException;
}
