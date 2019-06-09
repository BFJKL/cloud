package CloudCourse.service;

import CloudCourse.service.model.ErrorModel;

import java.io.IOException;
import java.util.List;

public interface ErrorService {
  List<ErrorModel> findAllErrorData() throws IOException;
}
