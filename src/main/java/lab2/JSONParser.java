package lab2;

import com.alibaba.fastjson.JSON;
import lab2.classes.TableSearcher;
import lab2.classes.Wrapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class JSONParser {
    public static void main(String[] args) throws IOException {
        String input = read();
        Wrapper wrapper = JSON.parseObject(input, Wrapper.class);
        TableSearcher searcher = new TableSearcher(wrapper.getTable());
        Map<String, Object> map = searcher.search(new String[]{"current_speed", "temperature", "salinity"});
        System.out.println(JSON.toJSON(map));


    }
    private static String read()
    {
        try(Scanner scanner = new Scanner(new FileInputStream("D:\\Projects\\TRPO\\src\\lab2\\file.json")))
        {
            String s;
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNext()) {
                s = scanner.nextLine();
                builder.append(s).append('\n');
            }
            return builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
