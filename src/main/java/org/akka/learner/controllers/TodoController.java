package org.akka.learner.controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.akka.learner.system.todo.actors.FrontActor.BATCH_CREATE;


@RestController
@RequestMapping("/todo")
public class TodoController {
    private ActorSelection frontActor;

    public TodoController(@Qualifier("taskSystem") ActorSystem actorSystem) {
        frontActor = actorSystem.actorSelection("/user/front");
    }

    @PostMapping("/batch")
    public void batchCreate() {
        frontActor.tell(BATCH_CREATE, ActorRef.noSender());
    }
}
