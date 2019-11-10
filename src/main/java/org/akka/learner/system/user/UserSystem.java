package org.akka.learner.system.user;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class UserSystem {

    @Bean
    @Profile("!dev")
    @Qualifier("userSystem")
    public ActorSystem localUserActorSystem() {
        return ActorSystem.create("user-system");
    }

    @Bean
    @Profile("dev")
    @Qualifier("userSystem")
    public ActorSystem userActorSystem() {
        Config config = ConfigFactory.parseResources("local-user.conf");
        return ActorSystem.create("user-system", config);
    }
}
