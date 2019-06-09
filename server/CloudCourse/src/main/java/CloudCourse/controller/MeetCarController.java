package CloudCourse.controller;

import CloudCourse.response.CommonReturnType;
import CloudCourse.service.MeetCarService;
import CloudCourse.service.model.MeetCarModel;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class MeetCarController extends BaseController {


    @Autowired
    private MeetCarService meetCarService;

    @RequestMapping(value = "/meetCar",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType meetCar(@RequestParam(name = "eId")String  eId) throws IOException, JSONException {
        List<MeetCarModel> meetCarModels = meetCarService.searchMeetCar(eId);
        return CommonReturnType.create(meetCarModels);
    }
}
