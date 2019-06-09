package CloudCourse.controller;


import CloudCourse.response.CommonReturnType;
import CloudCourse.service.CountService;
import CloudCourse.service.model.CountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class CountController extends BaseController {

  @Autowired
  CountService countService;

  @RequestMapping(value = "/count",method = {RequestMethod.GET})
  @ResponseBody
  public CommonReturnType countCar(@RequestParam(name = "placeId")Integer placeId) throws IOException {
    List<CountModel> countModels = countService.countCar(placeId);
    return CommonReturnType.create(countModels);
  }

}
