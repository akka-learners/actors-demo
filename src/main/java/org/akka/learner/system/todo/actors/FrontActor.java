package org.akka.learner.system.todo.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.FromConfig;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

public class FrontActor extends AbstractActor {

    private ActorRef todoActor = getContext()
        .actorOf(FromConfig.getInstance().props(TodoActor.props()), "todoRouter");

    private ActorMaterializer mat;

    public static Props props() {
        return Props.create(FrontActor.class, FrontActor::new);
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        mat = ActorMaterializer.create(getContext().getSystem());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .matchEquals(BATCH_CREATE, ready -> {

                Source.range(1, 1000)
                    .via(Flow.<Integer>create()
                        .map((Integer index) -> new StringBuilder().append("todo").append("-").append(index).toString()))
                    .to(Sink
                        .foreach(todo -> todoActor.tell(new TodoActor.Creation(todo), ActorRef.noSender())))
                    .run(mat);
            })
            .build();
    }

    public static final String BATCH_CREATE = "batchCreate";
}
