package org.akka.learner.system.user.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.FromConfig;
import lombok.AllArgsConstructor;
import org.akka.learner.system.notification.actors.EmailActor;
import org.akka.learner.system.todo.actors.ActivityActor;
import org.akka.learner.system.todo.actors.TodoActor;

public class UserActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef emailActor = getContext().actorOf(EmailActor.props(), "email");
    private ActorSelection todoActor = getContext().actorSelection("akka.tcp://task-system@127.0.0.1:2552/user/todo");
    private ActorRef activityActor = getContext().actorOf(FromConfig.getInstance().props(), "activity");


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Creation.class, this::createUser)
                .build();
    }

    private void createUser(Creation creation) {
        log.info("create user {}", creation.name);
        emailActor.tell(new EmailActor.Creation(creation.name, "Welcome to actor world"), sender());
        todoActor.tell(new TodoActor.Creation("verify email"), self());
        activityActor.tell(new ActivityActor.Registration(creation.name, "Registration"), self());
    }

    @AllArgsConstructor
    public static class Creation {
        private String name;
    }
}
