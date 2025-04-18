package io.quarkiverse.langchain4j.sample;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.oidc.IdToken;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

/**
 * Login resource which returns a poem welcome page to the authenticated user
 */
@Path("/model")
@Authenticated
public class LoginResource {

    @Inject
    @IdToken
    JsonWebToken idToken;

    @Inject
    Template poem;

    @GET
    @Path("{model}")
    @Produces("text/html")
    public TemplateInstance poem(@PathParam("model") String model) {
        return poem.data("name", idToken.getName()).data("model", model);
    }
}
