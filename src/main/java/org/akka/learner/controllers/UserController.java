package org.akka.learner.controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import org.akka.learner.system.user.actors.UserActor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ActorSelection userActor;

    public UserController(@Qualifier("userSystem") ActorSystem userSystem) {
        userActor = userSystem.actorSelection("/user/users");
    }

    @PostMapping("/{name}")
    public void create(@PathVariable(name = "name") String name) {
        userActor.tell(new UserActor.Creation(name), ActorRef.noSender());
    }
}
