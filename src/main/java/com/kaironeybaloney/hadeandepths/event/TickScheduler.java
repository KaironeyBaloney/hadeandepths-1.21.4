package com.kaironeybaloney.hadeandepths.event;

import net.minecraft.server.level.ServerLevel;

import java.util.*;

public class TickScheduler {
    private static final Map<ServerLevel, List<ScheduledTask>> TASKS = new HashMap<>();

    public static void schedule(ServerLevel level, int delay, Runnable task) {
        TASKS.computeIfAbsent(level, l -> new ArrayList<>())
                .add(new ScheduledTask(delay, task));
    }

    public static void onLevelTick(ServerLevel level) {
        List<ScheduledTask> list = TASKS.get(level);
        if (list == null) return;

        Iterator<ScheduledTask> it = list.iterator();
        while (it.hasNext()) {
            ScheduledTask t = it.next();
            t.ticks--;
            if (t.ticks <= 0) {
                t.action.run();
                it.remove();
            }
        }
    }

    private static class ScheduledTask {
        int ticks;
        final Runnable action;

        ScheduledTask(int ticks, Runnable action) {
            this.ticks = ticks;
            this.action = action;
        }
    }
}