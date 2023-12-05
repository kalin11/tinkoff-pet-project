package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.scheduler.SchedulerProperties;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final Scheduler scheduler;
    private final SchedulerProperties schedulerProperties;

    public Integer setVerificationTime(Integer minutes) throws SchedulerException, ParseException {
        var cron = "0 */" + minutes + " * * * ?";
        CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(
                TriggerKey.triggerKey("deleteCommentJobTrigger", schedulerProperties.getPermanentJobsGroupName())
        );
        trigger.setCronExpression(cron);
        scheduler.rescheduleJob(trigger.getKey(), trigger);
        return minutes;
    }
}