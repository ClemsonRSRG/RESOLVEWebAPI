/**
 * ---------------------------------
 * Copyright (c) 2017
 * RESOLVE Software Research Group
 * School of Computing
 * Clemson University
 * All rights reserved.
 * ---------------------------------
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package actors;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.japi.Creator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class VCSocketActor extends AbstractSocketActor {

    public VCSocketActor(ActorRef out, String job, String project) {
        super(out, job, project);
    }

    public static Props props(ActorRef out, String job, String project) {
        // http://doc.akka.io/docs/akka/snapshot/java/untyped-actors.html
        return Props.create(new Creator<VCSocketActor>() {

            private static final long serialVersionUID = 1L;

            @Override
            public VCSocketActor create() throws Exception {
                return new VCSocketActor(out, job, project);
            }
        });
    }

    @Override
    public void onReceive(Object message) {
        try {
            // Only deal with Strings
            if (message instanceof String) {
                JsonNode request = Json.parse((String) message);

                // Create a JSON Object informing we are starting the job
                ObjectNode result = Json.newObject();
                result.put("status", "info");
                result.put(
                        "msg",
                        "Received request with the following parameters: "
                                + myJob
                                + " and "
                                + myProject
                                + ". Launching the RESOLVE compiler with the specified arguments.");

                // Send the message through the websocket
                myWebSocketOut.tell(result.toString(), self());

                // Close the connection
                self().tell(PoisonPill.getInstance(), self());
            }
            else {
                // Send an error message back to user and close
                // socket connection for all other types.
                unhandled(message);
            }
        }
        catch (RuntimeException rte) {
            // Send an error message back to user and close
            // socket connection for all invalid JSON strings.
            unhandled(message);
        }
    }

}