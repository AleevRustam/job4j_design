package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;

        Map<Integer, User> prevMap = new HashMap<>();
        for (User user : previous) {
            prevMap.put(user.getId(), user);
        }

        for (User user : current) {
            User prevUser = prevMap.get(user.getId());
            if (prevUser == null) {
                added++;
            } else if (!Objects.equals(prevUser.getName(), user.getName())) {
                changed++;
            }
        }

        deleted = previous.size() - current.size() + added;

        return new Info(added, changed, deleted);
    }
}
