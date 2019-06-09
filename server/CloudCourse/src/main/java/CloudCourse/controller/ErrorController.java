package CloudCourse.controller;


import CloudCourse.controller.viewobject.ErrorVO;
import CloudCourse.response.CommonReturnType;
import CloudCourse.service.ErrorService;
import CloudCourse.service.model.CountModel;
import CloudCourse.service.model.ErrorModel;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
//@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class ErrorController extends BaseController{

  @Autowired
  private ErrorService errorService;

  @RequestMapping(value = "/we",method = {RequestMethod.GET})
  @ResponseBody
  public CommonReturnType errorData() throws IOException {
    List<ErrorModel> errorModels = errorService.findAllErrorData();
    return CommonReturnType.create(errorModels);
  }

}
