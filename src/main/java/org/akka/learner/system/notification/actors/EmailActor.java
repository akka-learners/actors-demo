package org.akka.learner.system.notification.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.AllArgsConstructor;

import java.io.Serializable;

public class EmailActor extends AbstractActor {
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(EmailActor.class, EmailActor::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Creation.class, this::create)
                .build();
    }

    private void create(Creation creation) {
        log.info("Send email to {} with {}", creation.to, creation.subject);
    }

    @AllArgsConstructor
    public static class Creation implements Serializable {
        private String to;
        private String subject;
    }
}
