package lab2.classes;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TableSearcher {
    public static final int MIN = 0;
    public static final int MAX = 1;
    public static final int AVERAGE = 2;
    private Table table;
    private final int timeIndex;

    public TableSearcher(Table table) {
        this.table = table;
        this.timeIndex = getIndex("time");
    }
    public Map<String, Object> search(String[] fields)
    {
        int size = fields.length;
        int[] indexes = new int[size];
        for(int i = 0; i < size; i++) {
            int index = getIndex(fields[i]);
            if (!table.getColumnTypes().get(index).equals("float"))
                throw new RuntimeException("The field '" + fields[i] + "' is not a number! Found: " + table.getColumnTypes().get(index));
            indexes[i] = index;
        }

        Node[] min = new Node[size], max = new Node[size];

        Arrays.setAll(min, i -> new Node(Float.MAX_VALUE, null));
        Arrays.setAll(max, i -> new Node(Float.MIN_VALUE, null));

        float[] sum = new float[size];
        int[] count = new int[size];

        List<List<Object>> rows = table.getRows();
        for(List<Object> list: rows)
        {
            for (int i = 0; i < size; i++) {
                if (check(fields[i], list))
                {
                    count[i]++;
                    float value = Float.parseFloat(list.get(indexes[i]).toString());
                    String time = list.get(timeIndex).toString();
                    sum[i] += value;
                    if (min[i].key > value) {
                        min[i].key = value;
                        min[i].value = time;
                    }
                    if (max[i].key < value)
                    {
                        max[i].key = value;
                        max[i].value = time;
                    }
                }
            }
        }
        Map<String, Object> output = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("start_date", ((String)rows.get(0).get(timeIndex)).substring(0, 10));
            map.put("end_date", ((String)rows.get(rows.size() - 1).get(timeIndex)).substring(0, 10));
            map.put("num_records", count[i]);
            map.put("min_"+fields[i], min[i].key);
            map.put("min_time", min[i].value);
            map.put("max_"+fields[i], max[i].key);
            map.put("max_time", max[i].value);
            map.put("avg_"+fields[i], sum[i]/count[i]);
            output.put(fields[i], map);
        }
        return output;
    }
    private boolean check(String field, List<Object> list)
    {
        field += "_qc";
        int index = getIndex(field);
        return Float.parseFloat(list.get(index).toString()) == 0;
    }
    private int getIndex(String s)
    {
        int index = 0;
        boolean found = false;
        for ( ;index < table.getColumnNames().size(); index++) {
            if (table.getColumnNames().get(index).equals(s)) {
                found = true;
                break;
            }
        }
        if (found)
            return index;
        else
            throw new RuntimeException("The column with name '"+s+"' hasn't been found!");
    }
    private static class Node
    {
        Float key;
        String value;
        public Node(Float key, String value) {
            this.key = key;
            this.value = value;
        }
    }

}
