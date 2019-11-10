package org.akka.learner.system.todo.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.AllArgsConstructor;

import java.io.Serializable;

public class ActivityActor extends AbstractActor {
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(ActivityActor.class, ActivityActor::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Registration.class, this::create)
                .build();
    }

    private void create(Registration registration) {
        log.info("Create activity {} for {}", registration.content, registration.user);
    }

    @AllArgsConstructor
    public static class Registration implements Serializable {
        private String user;
        private String content;
    }
}
