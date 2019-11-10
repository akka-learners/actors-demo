package org.akka.learner.system.user;

import akka.actor.ActorSystem;
import akka.actor.Props;
import org.akka.learner.system.user.actors.UserActor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserSystemInitializer implements InitializingBean {

    @Autowired
    @Qualifier("userSystem")
    ActorSystem userSystem;

    @Override
    public void afterPropertiesSet() {
        userSystem.actorOf(Props.create(UserActor.class, UserActor::new), "users");
    }
}
