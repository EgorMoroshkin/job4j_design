package serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Jerry");
        list.add("Bella");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Cat cat = new Cat(11, "Tom", true, new String[]{"Jerry", "Bella"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", cat.getAge());
        jsonObject.put("name", cat.getName());
        jsonObject.put("colorIsBlack", cat.getColorIsBlack());
        jsonObject.put("child", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(cat).toString());
    }
}