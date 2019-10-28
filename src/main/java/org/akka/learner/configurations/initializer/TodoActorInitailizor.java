package org.akka.learner.configurations.initializer;

import akka.actor.ActorSystem;
import org.akka.learner.todo.FrontActor;
import org.akka.learner.todo.TodoActor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoActorInitailizor implements InitializingBean {

    @Autowired
    private ActorSystem actorSystem;

    @Override
    public void afterPropertiesSet() {
        actorSystem.actorOf(TodoActor.props(), "todo");
        actorSystem.actorOf(FrontActor.props(), "front");
    }
}
