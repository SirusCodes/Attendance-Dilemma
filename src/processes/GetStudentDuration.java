package processes;

import gui.observableModel.RecordTableObservable;
import models.StudentRawModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetStudentDuration {
    HashMap<String, HashMap<String, Object>> map = new HashMap<>();

    public ArrayList<RecordTableObservable> getDuration(ArrayList<StudentRawModel> list, String startTime, String endTime, double minDuration) {
        ArrayList<RecordTableObservable> observableArrayList = new ArrayList<>();
        LocalDateTime startDateTime = list.get(0).getDateTime();
        if (startTime != null) {
            String[] start = startTime.split(":");
            startDateTime = startDateTime.withHour(Integer.parseInt(start[0])).withMinute(Integer.parseInt(start[1]));
        }
        String[] end = endTime.split(":");
        LocalDateTime endDateTime = list.get(0).getDateTime().withHour(Integer.parseInt(end[0])).withMinute(Integer.parseInt(end[1]));

        for (StudentRawModel model : list) {
            HashMap<String, Object> data = new HashMap<>();
            if (map.containsKey(model.getName())) {
                int duration = (int) map.get(model.getName()).get("duration");
                if (model.getStatus().equals("Left")) {
                    LocalDateTime date = (LocalDateTime) map.get(model.getName()).get("join");
                    duration += ChronoUnit.MINUTES.between(date, model.getDateTime());
                    data.put("duration", duration);
                    data.put("status", "Left");
                } else {
                    data.put("duration", duration);
                    data.put("join", model.getDateTime());
                    data.put("status", "Joined");
                }
            } else {
                data.put("duration", 0);
                data.put("join", model.getDateTime());
                data.put("status", "Joined");
            }
            map.put(model.getName(), data);
        }

        int max = ((int) ChronoUnit.MINUTES.between(startDateTime, endDateTime));
        double minRequiredDuration = (minDuration * max) / 100;

        for (Map.Entry<String, HashMap<String, Object>> it : map.entrySet()) {
            int duration = (int) it.getValue().get("duration");
            if (Objects.equals(it.getValue().get("status"), "Joined") || duration > max)
                duration = max;

            RecordTableObservable observable = new RecordTableObservable();
            observable.setName(it.getKey());
            observable.setDuration(duration);

            if(duration>=minRequiredDuration)
                observable.setStatus("P");
            else
                observable.setStatus("A");

            observableArrayList.add(observable);
        }

        return observableArrayList;
    }
}
