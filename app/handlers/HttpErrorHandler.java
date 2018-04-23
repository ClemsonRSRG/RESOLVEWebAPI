/*
 * ---------------------------------
 * Copyright (c) 2018
 * RESOLVE Software Research Group
 * School of Computing
 * Clemson University
 * All rights reserved.
 * ---------------------------------
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package handlers;

import com.typesafe.config.Config;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;
import scala.Some;

/**
 * <p>This singleton class serves as the HTTP error handler.</p>
 *
 * <p>For more information, see:
 * <a href="https://www.playframework.com/documentation/latest/JavaErrorHandling">Handling Errors</a></p>
 *
 * @author Yu-Shan Sun
 * @version 1.0
 */
@Singleton
public class HttpErrorHandler extends DefaultHttpErrorHandler {

    // ===========================================================
    // Member Fields
    // ===========================================================

    /** <p>Contains all information related current deployment environment.</p> */
    private final Environment myEnvironment;

    /** <p>Contains all the routes available.</p> */
    private final Provider<Router> myRoutes;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * {@inheritDoc}
     */
    @Inject
    public HttpErrorHandler(Config config, Environment environment,
            OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(config, environment, sourceMapper, routes);
        myEnvironment = environment;
        myRoutes = routes;
    }

    // ===========================================================
    // Public Methods
    // ===========================================================

    /**
     * {@inheritDoc}
     */
    protected final CompletionStage<Result> onBadRequest(RequestHeader request,
            String message) {
        return CompletableFuture.completedFuture(Results
                .badRequest(views.html.defaultpages.badRequest.render(
                        request.method(), request.uri(), message)));
    }

    /**
     * {@inheritDoc}
     */
    protected final CompletionStage<Result> onForbidden(RequestHeader request,
            String message) {
        return CompletableFuture.completedFuture(Results
                .forbidden(views.html.defaultpages.unauthorized.render()));
    }

    /**
     * {@inheritDoc}
     */
    protected final CompletionStage<Result> onNotFound(RequestHeader request,
            String message) {
        if (myEnvironment.isProd()) {
            return CompletableFuture.completedFuture(Results
                    .notFound(views.html.defaultpages.notFound.render(
                            request.method(), request.uri())));
        }
        else {
            return CompletableFuture.completedFuture(Results
                    .notFound(views.html.defaultpages.devNotFound.render(
                            request.method(), request.uri(),
                            Some.apply(myRoutes.get()))));
        }
    }

    /**
     * {@inheritDoc}
     */
    protected final CompletionStage<Result> onOtherClientError(
            RequestHeader request, int statusCode, String message) {
        return CompletableFuture.completedFuture(Results.status(statusCode,
                views.html.defaultpages.badRequest.render(request.method(),
                        request.uri(), message)));
    }

    /**
     * {@inheritDoc}
     */
    protected final CompletionStage<Result> onProdServerError(
            RequestHeader request, UsefulException exception) {
        return CompletableFuture.completedFuture(Results
                .internalServerError(views.html.defaultpages.error
                        .render(exception)));
    }

}