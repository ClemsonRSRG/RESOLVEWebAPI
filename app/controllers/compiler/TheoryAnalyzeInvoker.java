package controllers.compiler;

import akka.actor.ActorRef;
import edu.clemson.cs.rsrg.init.file.ResolveFile;
import java.util.Map;

public class TheoryAnalyzeInvoker extends AbstractInvoker {

    public TheoryAnalyzeInvoker(String[] args, ActorRef out) {
        super(args, out);
    }

    @Override
    public void executeJob(Map<String, ResolveFile> fileMap) {
        //Run the compiler
        try{
            myCompilerInstance.invokeCompiler(fileMap, null, null);
        }
        catch(Exception ex){
            //obviously not too concerned about this situation ever happening
        }
    }

}