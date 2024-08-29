package tipo;

import java.time.Duration;
import java.time.Instant;

// Classe per gestire un timer che tiene traccia del tempo trascorso e verifica se supera una durata massima
public class Timer {

    private static final long MAX_DURATION = 3 * 60; //durata massima del gioco (3 minuti)
    private Instant startTime;
    private Instant endTime;

    public void start() {
        startTime = Instant.now();
    }

    public void stop() {
        endTime = Instant.now();
    }

    public long getElapsedTime() {
        if (startTime == null) {
            throw new IllegalStateException("Timer not started");
        }
        Instant end = (endTime != null) ? endTime : Instant.now();
        Duration timeElapsed = Duration.between(startTime, end);
        return timeElapsed.getSeconds();
    }

    public boolean isTimeUp() {
        return getElapsedTime() > MAX_DURATION;
    }
}
