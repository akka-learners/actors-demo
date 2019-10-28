package org.akka.learner.todo;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.stream.ActorMaterializer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class TodoActor extends AbstractActor {


    private ActorMaterializer mat;

    public static Props props() {
        return Props.create(TodoActor.class, TodoActor::new);
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        mat = ActorMaterializer.create(getContext().getSystem());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()

            .match(Creation.class, this::create)
            .build();
    }

    private void create(Creation creation) {
        log.info("create todo which named {}", creation.name);
    }


    @AllArgsConstructor
    public static class Creation implements Serializable {
        private String name;
    }

}
