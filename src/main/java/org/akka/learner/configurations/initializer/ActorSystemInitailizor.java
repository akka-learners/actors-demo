package org.akka.learner.configurations.initializer;

import akka.actor.ActorSystem;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.scaladsl.AkkaManagement;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class ActorSystemInitailizor implements InitializingBean {

    @Autowired
    private ActorSystem actorSystem;

    @Override
    public void afterPropertiesSet() {
        AkkaManagement.get(actorSystem).start();
        ClusterBootstrap.get(actorSystem).start();
    }
}
