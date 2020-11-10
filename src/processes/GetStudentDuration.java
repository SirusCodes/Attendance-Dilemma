package processes;

import models.StudentRawModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetStudentDuration {
    HashMap<String, HashMap<String, Object>> map = new HashMap<>();

    public void getDuration(ArrayList<StudentRawModel> list) {
        int maxDuration = 0;
        for (StudentRawModel model : list) {
            HashMap<String, Object> data = new HashMap<>();
            if (map.containsKey(model.getName())) {
                int duration = (int) map.get(model.getName()).get("duration");
                if (model.getStatus().equals("Left")) {
                    LocalDateTime date = (LocalDateTime) map.get(model.getName()).get("join");
                    duration += ChronoUnit.MINUTES.between(date, model.getDateTime());
                    data.put("duration", duration);
                    maxDuration = Math.max(duration, maxDuration);
                } else {
                    data.put("duration", duration);
                    data.put("join", model.getDateTime());
                }
            } else {
                data.put("duration", 0);
                data.put("join", model.getDateTime());
            }
            map.put(model.getName(), data);
        }
        for (Map.Entry<String, HashMap<String, Object>> it : map.entrySet()) {
            int duration = (int) it.getValue().get("duration");
            if ((int) it.getValue().get("duration") == 0) {
                duration = maxDuration;
            }
            System.out.println("name=" + it.getKey() + " duration=" + duration);
        }
    }
}
