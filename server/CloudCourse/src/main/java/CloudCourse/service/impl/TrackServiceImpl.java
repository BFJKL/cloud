package CloudCourse.service.impl;

import CloudCourse.HBaseSearch.Map;
import CloudCourse.hbase.HBaseConf;
import CloudCourse.service.TrackService;
import CloudCourse.service.model.TrackModel;
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
public class TrackServiceImpl implements TrackService {
    @Override
    public List<TrackModel> trackBack(Long start, Long end, String eId) throws IOException {
        String tablename = "search";
        Connection connection = HBaseConf.getConnection();
        Admin admin = connection.getAdmin();
        TableName name = TableName.valueOf(tablename);

        List<TrackModel> trackModels = new ArrayList<>();
        if (admin.tableExists(name)){
                TableName tableName = TableName.valueOf(Bytes.toBytes(tablename));
                Table table = connection.getTable(tableName);
                Map map = new Map();
                Scan scan = new Scan();
                scan.setStartRow(Bytes.toBytes(start +"##" + eId));
                scan.setStopRow(Bytes.toBytes(end +"##" + eId));
                Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new RegexStringComparator(".*"+"##"+eId));
                scan.setFilter(filter);

                ResultScanner scanner = table.getScanner(scan);

                for (Result r : scanner) {
                    TrackModel trackModel = new TrackModel();
                    trackModel.setAddress(map.resultMapToRecord(r).getAddress());
                    trackModel.setLatitude(map.resultMapToRecord(r).getLatitude());
                    trackModel.setLongitude(map.resultMapToRecord(r).getLongitude());
                    trackModels.add(trackModel);
                }
                scanner.close();
            }

        return trackModels;
    }
}
