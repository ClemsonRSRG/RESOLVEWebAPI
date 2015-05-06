package models.actors;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class ErrorSocketActor extends AbstractSocketActor {

    public ErrorSocketActor(ActorRef out) {
        super(out, "errorhandler");

        // Create the error JSON Object
        ObjectNode result = Json.newObject();
        result.put("status", "error");
        result.put("msg", "Undefined job request");

        // Send the message through the websocket
        myWebSocketOut.tell(result.toString(), self());

        // Close the connection
        self().tell(PoisonPill.getInstance(), self());
    }

    public static Props props(ActorRef out) {
        return Props.create(ErrorSocketActor.class, out);
    }

    @Override
    public void onReceive(Object message) {
        // Should never get here
        throw new RuntimeException();
    }

}