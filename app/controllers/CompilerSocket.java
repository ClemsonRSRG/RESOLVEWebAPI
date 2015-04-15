package controllers;

import models.actors.*;
import play.mvc.Controller;
import play.mvc.WebSocket;

public class CompilerSocket extends Controller {

    public static WebSocket<String> socket(String job, String project) {
        if (job.equals("buildJar")) {
            return WebSocket.withActor(JarSocketActor::props);
        }
        else if (job.equals("ccverify")) {
            return WebSocket.withActor(CCVerifySocketActor::props);
        }
        else if (job.equals("genVCs")) {
            return WebSocket.withActor(VCSocketActor::props);
        }
        else if (job.equals("rwverify")) {
            return WebSocket.withActor(RWVerifySocketActor::props);
        }
        else if (job.equals("translate")) {
            return WebSocket.withActor(TranslateSocketActor::props);
        }
        else {
            return WebSocket.withActor(ErrorSocketActor::props);
        }
    }

}