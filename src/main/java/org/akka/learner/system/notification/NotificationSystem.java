package org.akka.learner.system.notification;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class NotificationSystem {
    @Bean
    @Profile("!dev")
    @Qualifier("notificationSystem")
    public ActorSystem localNotificationActorSystem() {
        return ActorSystem.create("notification-system");
    }

    @Bean
    @Profile("dev")
    @Qualifier("notificationSystem")
    public ActorSystem notificationActorSystem() {
        Config config = ConfigFactory.parseResources("local-notification.conf");
        return ActorSystem.create("notification-system", config);
    }
}
