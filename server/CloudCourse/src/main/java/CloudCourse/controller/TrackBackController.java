package CloudCourse.controller;

import CloudCourse.response.CommonReturnType;
import CloudCourse.service.RecordService;
import CloudCourse.service.TrackService;
import CloudCourse.service.model.RecordModel;
import CloudCourse.service.model.TrackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/")
public class TrackBackController extends BaseController {

    @Autowired
    private TrackService trackService;

    @RequestMapping(value = "/track",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType trackBack(@RequestParam(name = "start")String start,
                                         @RequestParam(name = "end")String end,
                                         @RequestParam(name = "eId")String eId) throws ParseException, IOException {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date startTime= null,endTime=null;
        startTime = simpleDateFormat.parse(start);
        endTime = simpleDateFormat.parse(end);
        Long st = startTime.getTime()/1000;
        Long ed = endTime.getTime()/1000;

        List<TrackModel> trackModelList =  trackService.trackBack(st,ed,eId);
        return CommonReturnType.create(trackModelList);
    }
}
