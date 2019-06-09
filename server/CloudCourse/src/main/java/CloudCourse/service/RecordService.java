package CloudCourse.service;

import CloudCourse.service.model.RecordModel;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;

public interface RecordService {
  List<RecordModel> ruleResearch(Long start, Long end, Integer placeId) throws IOException;
}
