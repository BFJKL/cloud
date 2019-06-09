package CloudCourse.controller;

import CloudCourse.response.CommonReturnType;
import CloudCourse.service.CountService;
import CloudCourse.service.RecordService;
import CloudCourse.service.model.CountModel;
import CloudCourse.service.model.RecordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class RecordController extends BaseController {

  @Autowired
  private RecordService recordService;

  @RequestMapping(value = "/rule",method = {RequestMethod.GET})
  @ResponseBody
  public CommonReturnType recordSearch(@RequestParam(name = "start")String start,
                                       @RequestParam(name = "end")String end,
                                       @RequestParam(name = "placeId")Integer placeId) throws ParseException, IOException {


      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      java.util.Date startTime= null,endTime=null;
      startTime = simpleDateFormat.parse(start);
      endTime = simpleDateFormat.parse(end);
      Long st = startTime.getTime()/1000;
      Long ed = endTime.getTime()/1000;

      List<RecordModel> recordModelList =  recordService.ruleResearch(st,ed,placeId);
    return CommonReturnType.create(recordModelList);
  }

}
