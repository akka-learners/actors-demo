package org.akka.learner.controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.akka.learner.todo.FrontActor.BATCH_CREATE;


@RestController
@RequestMapping("/todo")
public class TodoController {
    private ActorSelection frontActor;

    public TodoController(ActorSystem actorSystem) {
        frontActor = actorSystem.actorSelection("/user/front");
    }

    @PostMapping("/batch")
    public void batchCreate() {
        frontActor.tell(BATCH_CREATE, ActorRef.noSender());
    }
}
