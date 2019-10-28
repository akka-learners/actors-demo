package org.akka.learner.configurations;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ActorSystemConfiguration {

    @Bean
    @Profile("!dev")
    public ActorSystem localActorSystem() {
        return ActorSystem.create("actor-demo");
    }

    @Bean
    @Profile("dev")
    public ActorSystem devActorSystem() {
        Config config = ConfigFactory.parseResources("local.conf");
        return ActorSystem.create("actor-demo", config);
    }

}
