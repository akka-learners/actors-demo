package org.akka.learner.system.todo;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ActorSystemConfiguration {

    @Bean
    @Profile("!dev")
    @Qualifier("taskSystem")
    public ActorSystem localActorSystem() {
        return ActorSystem.create("task-system");
    }

    @Bean
    @Profile("dev")
    @Qualifier("taskSystem")
    public ActorSystem devActorSystem() {
        Config config = ConfigFactory.parseResources("local.conf");
        return ActorSystem.create("task-system", config);
    }

}
