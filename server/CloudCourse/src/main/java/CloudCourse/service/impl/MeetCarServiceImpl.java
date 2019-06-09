package CloudCourse.service.impl;

import CloudCourse.hbase.HBaseConf;
import CloudCourse.service.MeetCarService;
import CloudCourse.service.model.MeetCarModel;
import CloudCourse.spark.CarMap;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetCarServiceImpl implements MeetCarService {
  @Override
  public List<MeetCarModel> searchMeetCar(String eId) throws IOException {
    List<MeetCarModel> meetCarModels = new ArrayList<>();
    String tablename = "MeetCount";
    Connection connection = HBaseConf.getConnection();
    Admin admin = connection.getAdmin();
    TableName name = TableName.valueOf(tablename);
    List list = new ArrayList();
    if (admin.tableExists(name)) {
      TableName tableName = TableName.valueOf(Bytes.toBytes(tablename));
      Table table = connection.getTable(tableName);
      CarMap map = new CarMap();
      Scan scan = new Scan();
      Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(eId));
      scan.setFilter(filter);
      ResultScanner scanner = table.getScanner(scan);
      for (Result result : scanner) {
        MeetCarModel meetCarModel = new MeetCarModel();
        meetCarModel.seteId1(map.resultMapToRecord(result).geteId1());
        meetCarModel.seteId2(map.resultMapToRecord(result).geteId2());
        meetCarModel.setCount(map.resultMapToRecord(result).getCount());
        meetCarModels.add(meetCarModel);
      }
    }else {
      System.out.println("Table doesn't exist!");
    }

    return meetCarModels;
  }
}
