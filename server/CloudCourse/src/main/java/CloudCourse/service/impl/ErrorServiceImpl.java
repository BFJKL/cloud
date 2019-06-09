package CloudCourse.service.impl;

import CloudCourse.HBaseSearch.SysMap;
import CloudCourse.service.ErrorService;
import CloudCourse.service.model.ErrorModel;
import CloudCourse.spark.DateTransform;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ErrorServiceImpl implements ErrorService {
  @Override
  public List<ErrorModel> findAllErrorData() throws IOException {
    List<ErrorModel> errorModels = new ArrayList<>();
    Configuration configuration = HBaseConfiguration.create();
    Connection connection = ConnectionFactory.createConnection(configuration);
    //建立表的连接
    Table table = connection.getTable(TableName.valueOf("error"));
    SysMap sysmap = new SysMap();
    List list = new ArrayList();
    //创建一个空的Scan实例
    Scan scan = new Scan();
    //在行上获取遍历器
    ResultScanner scanner = table.getScanner(scan);
    //打印行的值
    DateTransform dateTransform = new DateTransform();
    for (Result res : scanner) {

      ErrorModel errorModel = new ErrorModel();
      errorModel.setPlaceId(sysmap.resultMapToRecord(res).getPlaceId());
      errorModel.setEid(sysmap.resultMapToRecord(res).getEid());
      errorModel.setAddress(sysmap.resultMapToRecord(res).getAddress());
      errorModel.setLatitude(sysmap.resultMapToRecord(res).getLatitude());
      errorModel.setLongitude(sysmap.resultMapToRecord(res).getLongitude());
      errorModel.setTime(dateTransform.DateTransform(String.valueOf(sysmap.resultMapToRecord(res).getTime())));
      errorModels.add(errorModel);
    }
    System.out.println(list);
    //关闭释放资源
    scanner.close();

    return errorModels;
  }
}
