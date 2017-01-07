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
package compiler;

import akka.actor.ActorRef;
import edu.clemson.cs.rsrg.init.file.ResolveFile;
import java.util.Map;

public class ProverInvoker extends AbstractInvoker {

    public ProverInvoker(String[] args, ActorRef out) {
        super(args, out);
    }

    @Override
    public void executeJob(Map<String, ResolveFile> fileMap) {

    }

}