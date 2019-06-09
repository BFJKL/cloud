package CloudCourse.service.impl;

import CloudCourse.hbase.HBaseConf;
import CloudCourse.service.CountService;
import CloudCourse.service.model.CountModel;
import CloudCourse.service.model.ErrorModel;
import CloudCourse.spark.SparkMap;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountServiceImpl implements CountService {
  @Override
  public List<CountModel> countCar(Integer placeId) throws IOException {
    String tablename = "VehicleCount";
    Connection connection = HBaseConf.getConnection();
    Admin admin = connection.getAdmin();
    List<CountModel> countModels = new ArrayList<>();
    TableName name = TableName.valueOf(tablename);
    if (admin.tableExists(name)) {
      String i = placeId.toString();
      TableName tableName = TableName.valueOf(Bytes.toBytes(tablename));
      Table table = connection.getTable(tableName);
      SparkMap map = new SparkMap();

      Scan scan = new Scan();
      Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(i)));
      scan.setFilter(filter);

      ResultScanner scanner = table.getScanner(scan);
      for (Result result : scanner) {
        CountModel countModel = new CountModel();
        countModel.setAddress(map.resultMapToRecord(result).getAddress());
        countModel.setPlaceId(map.resultMapToRecord(result).getPlaceId());
        countModel.setCount(map.resultMapToRecord(result).getCount());
        countModel.setLatitude(map.resultMapToRecord(result).getLatitude());
        countModel.setLongitude(map.resultMapToRecord(result).getLongitude());
        countModels.add(countModel);
      }
    }
    return countModels;
  }
}
