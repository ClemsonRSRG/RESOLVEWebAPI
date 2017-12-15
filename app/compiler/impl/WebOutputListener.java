/*
 *  ---------------------------------
 *  Copyright (c) 2017
 *  RESOLVE Software Research Group
 *  School of Computing
 *  Clemson University
 *  All rights reserved.
 *  ---------------------------------
 *  This file is subject to the terms and conditions defined in
 *  file 'LICENSE.txt', which is part of this source code package.
 */

package compiler.impl;

import edu.clemson.cs.r2jt.rewriteprover.Metrics;
import edu.clemson.cs.r2jt.rewriteprover.model.PerVCProverModel;
import edu.clemson.cs.rsrg.astoutput.GenerateGraphvizModel;
import edu.clemson.cs.rsrg.init.file.ResolveFile;
import edu.clemson.cs.rsrg.init.output.OutputListener;
import edu.clemson.cs.rsrg.statushandling.StatusHandler;
import edu.clemson.cs.rsrg.vcgeneration.VCGenerator;
import edu.clemson.cs.rsrg.vcgeneration.utilities.AssertiveCodeBlock;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>A listener that contains methods for retrieving compilation
 * results from the compiler and outputs as messages to the client.</p>
 *
 * @author Yu-Shan Sun
 * @version 1.0
 */
public class WebOutputListener implements OutputListener {

    // ===========================================================
    // Member Fields
    // ===========================================================

    /** <p>This is the status handler for the RESOLVE compiler.</p> */
    private final StatusHandler myStatusHandler;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * <p>This constructs a output listener for the WebAPI.</p>
     *
     * @param handler The status handler for the RESOLVE compiler.
     */
    public WebOutputListener(StatusHandler handler) {
        myStatusHandler = handler;
    }

    // ===========================================================
    // Public Methods
    // ===========================================================

    /**
     * <p>This method outputs the provided {@code Graphviz} model generated
     * from the {@link GenerateGraphvizModel}.</p>
     *
     * @param outputFileName A name for the output file.
     * @param graphvizModel The inner {@code AST} represented in a {@code GraphViz}
     *                      file format.
     */
    @Override
    public final void astGraphvizModelResult(String outputFileName,
            String graphvizModel) {
        throw new UnsupportedOperationException("Needs to be implemented!");
    }

    /**
     * <p>This method outputs the provided the java translation results
     * from the {@code JavaTranslator}.</p>
     *
     * @param inputFileName Name of the {@link ResolveFile} we are generating {@code Java} translations.
     * @param outputFileName A name for the output file.
     * @param javaTranslation The translated {@code Java} source code.
     */
    @Override
    public final void javaTranslationResult(String inputFileName,
            String outputFileName, String javaTranslation) {
        throw new UnsupportedOperationException("Needs to be implemented!");
    }

    /**
     * <p>This method outputs the provided results
     * from the {@code CCProver}.</p>
     *
     * @param inputFileName Name of the {@link ResolveFile} we are generating proofs.
     * @param outputFileName A name for the output file.
     */
    @Override
    public final void proverResult(String inputFileName, String outputFileName) {
        throw new UnsupportedOperationException("Needs to be implemented!");
    }

    /**
     * <p>This method outputs the provided {@link AssertiveCodeBlock AssertiveCodeBlocks}
     * and/or raw output result from the {@link VCGenerator}.</p>
     *
     * @param inputFileName Name of the {@link ResolveFile} we are generating VCs for.
     * @param outputFileName A name for the output file.
     * @param blocks A list of final {@link AssertiveCodeBlock AssertiveCodeBlocks}.
     * @param verboseOutput The verbose output string generated by the {@link VCGenerator}.
     */
    @Override
    public final void vcGeneratorResult(String inputFileName,
            String outputFileName, List<AssertiveCodeBlock> blocks,
            String verboseOutput) {
        throw new UnsupportedOperationException("Needs to be implemented!");
    }

    /**
     * <p>This method outputs the prover results for a given {@code VC}.</p>
     *
     * @param proved {@code true} if the {@code VC} was proved,
     *               {@code false} otherwise.
     * @param finalModel The prover representation for a {@code VC}.
     * @param m The prover generated metrics.
     */
    @Override
    public final void vcResult(boolean proved, PerVCProverModel finalModel,
            Metrics m) {
        throw new UnsupportedOperationException("Needs to be implemented!");
    }

    // ===========================================================
    // Private Methods
    // ===========================================================

    /**
     * <p>An helper method that helps us encode the generated content
     * before sending it out through the stream.</p>
     *
     * @param content A content string generated by the
     *                {@code RESOLVE} compiler.
     *
     * @return The encoded string.
     */
    private String encode(String content) {
        String encoded = null;
        try {
            // Replace all the %20 with spaces
            encoded =
                    URLEncoder.encode(content.replaceAll(" ", "%20"), "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            myStatusHandler.error(null,
                    "Something went wrong while attempting to output the results."
                            + "Please contact the administrators for support!");
        }

        return encoded;
    }

}