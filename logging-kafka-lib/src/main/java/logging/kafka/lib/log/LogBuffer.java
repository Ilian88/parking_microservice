package logging.kafka.lib.log;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class LogBuffer {
    private final List<LogEvent> buffer = new CopyOnWriteArrayList<>();

    public void add(LogEvent log) {
        buffer.add(log);
    }

    public List<LogEvent> drain() {
        List<LogEvent> logEvents = List.copyOf(buffer);
        buffer.clear();
        return logEvents;
    }
}
