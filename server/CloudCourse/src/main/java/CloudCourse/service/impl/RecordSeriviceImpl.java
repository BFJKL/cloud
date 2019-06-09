package CloudCourse.service.impl;

import CloudCourse.HBaseSearch.SysMap;
import CloudCourse.hbase.HBaseConf;
import CloudCourse.service.RecordService;
import CloudCourse.service.model.RecordModel;
import CloudCourse.spark.DateTransform;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordSeriviceImpl implements RecordService {

    @Override
    public List<RecordModel> ruleResearch(Long start, Long end, Integer placeId) throws IOException {
        String tablename = "rule";
        Connection connection = HBaseConf.getConnection();
        Admin admin = connection.getAdmin();
        TableName name = TableName.valueOf(tablename);
        List<RecordModel> recordModels = new ArrayList();
        if (admin.tableExists(name)){
            TableName tableName = TableName.valueOf(Bytes.toBytes(tablename));
            Table table = connection.getTable(tableName);
            SysMap sysMap = new SysMap();
            Scan scan = new Scan();
            scan.setStartRow(Bytes.toBytes(placeId +"##" + start ));
            scan.setStopRow(Bytes.toBytes(placeId +"##" + end));
            Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new RegexStringComparator(placeId+"##"+".*"));
            scan.setFilter(filter);

            DateTransform dateTransform = new DateTransform();
            ResultScanner scanner = table.getScanner(scan);
            for (Result r : scanner) {
                RecordModel recordModel = new RecordModel();
                recordModel.setAddress(sysMap.resultMapToRecord(r).getAddress());
                recordModel.setEid(sysMap.resultMapToRecord(r).getEid());
                recordModel.setTime(sysMap.resultMapToRecord(r).getTime());

                recordModels.add(recordModel);
            }
            scanner.close();
        }
        else{
            System.out.println("table not exits");
        }
        return recordModels;
    }
}
