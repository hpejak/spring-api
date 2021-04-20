package hr.pejak.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class ExternalAudit implements Runnable{

    AtomicBoolean running = new AtomicBoolean(true);

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public ExternalAudit(final ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @PostConstruct
    private void init() {
        log.info(" >>>>> Staring ExternalAudit class with separated thread");
        threadPoolTaskExecutor.execute(this);
    }

    @Override
    public void run() {
        log.info("I am running in Another Thread");
    }

    @PreDestroy
    public void shutdown() {
        log.info(" >>>>> Shutting down ExternalAudit");
    }

}
