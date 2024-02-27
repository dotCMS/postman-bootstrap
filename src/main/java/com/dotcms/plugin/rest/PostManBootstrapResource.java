package com.dotcms.plugin.rest;

import java.net.URISyntaxException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.dotcms.rest.InitDataObject;
import com.dotcms.rest.WebResource;
import com.dotcms.rest.annotation.NoCache;
import com.dotmarketing.business.DotStateException;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotSecurityException;
import com.liferay.portal.model.User;
import org.glassfish.jersey.server.JSONP;

/**
 * Allows the execution of a PostManBootstrap implementation.
 * @author jsanca
 */
@Path("/postman")
public class PostManBootstrapResource {

	private final PostmanBootstrapAPI postmanBootstrapAPI = new PostmanBootstrapAPI();
    private final WebResource webResource = new WebResource();

	@POST
	public Response doPost(@Context HttpServletRequest request, @PathParam("params") String params) throws URISyntaxException {
		InitDataObject auth = webResource.init(true, request, false);
		User user = auth.getUser();
		String username = (user != null) ? user.getFullName() : " unknown ";
		CacheControl cc = new CacheControl();
		cc.setNoCache(true);
		ResponseBuilder builder = Response.ok("{\"result\":\"" + username + " POST!\"}", "application/json");
		return builder.cacheControl(cc).build();
	}


	@GET
	@Path("/names/{names}")
	@JSONP
	@NoCache
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, "application/javascript"})
	public Response runBootstraps(@Context final HttpServletRequest request, @Context final HttpServletResponse response,
							 @PathParam("names") final String names) throws DotStateException,
			DotDataException, DotSecurityException {

		final InitDataObject auth = webResource.init(true, request, false);
		final User user = auth.getUser();

		final String [] namesArray = names.split(",");
		return Response.ok(
				this.postmanBootstrapAPI.runPostmanBootstraps(Arrays.asList(namesArray), request, response, user, Collections.emptyMap())
		);
	}

}
