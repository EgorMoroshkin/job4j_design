package serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Cat cat = new Cat(11, "Tom", true, new String[]{"Jerry", "Bella"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cat));

        /* Создаём новую json-строку с модифицированными данными*/
        final String catJson =
                "{"
                        + "\"age\":5,"
                        + "\"name\":Rufus,"
                        + "\"contact\":"
                        + "{"
                        + "\"colorIsBlack\":true"
                        + "},"
                        + "\"child\":"
                        + "[\"Emma\",\"Ira\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        final Cat personMod = gson.fromJson(catJson, Cat.class);
        System.out.println(personMod);
    }
}
