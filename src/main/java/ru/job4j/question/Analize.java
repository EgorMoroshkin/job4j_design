package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> mapPrevious = new HashMap<>();
        for (User userPrev : previous) {
            mapPrevious.put(userPrev.getId(), userPrev.getName());
        }
        for (User userCurr : current) {
            if (!mapPrevious.containsKey(userCurr.getId())) {
                added++;
            } else if (mapPrevious.containsKey(userCurr.getId()) && !mapPrevious.containsValue(userCurr.getName())) {
                changed++;
            }
            mapPrevious.remove(userCurr.getId());
        }
        deleted = mapPrevious.size();
        return new Info(added, changed, deleted);
    }
}