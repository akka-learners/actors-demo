package org.akka.learner.system.todo;

import akka.actor.ActorSystem;
import org.akka.learner.system.todo.actors.ActivityActor;
import org.akka.learner.system.todo.actors.FrontActor;
import org.akka.learner.system.todo.actors.TodoActor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TodoActorInitailizor implements InitializingBean {

    @Autowired
    @Qualifier("taskSystem")
    private ActorSystem actorSystem;

    public void afterPropertiesSet() {
        actorSystem.actorOf(TodoActor.props(), "todo");
        actorSystem.actorOf(ActivityActor.props(), "activity1");
        actorSystem.actorOf(ActivityActor.props(), "activity2");
        actorSystem.actorOf(ActivityActor.props(), "activity3");
        actorSystem.actorOf(FrontActor.props(), "front");
    }
}
