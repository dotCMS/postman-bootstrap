package com.dotcms.plugin.rest;

import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostmanBootstrapAPI {

    public static final PostmanBootstrapAPI INSTANCE = new PostmanBootstrapAPI();
    private PostmanBootstrapAPI() {
    }

    private final Map<String, PostmanBootstrap> postmanBootstrapMap = new ConcurrentHashMap<>();

    public void registerPostmanBootstrap(final String name, final PostmanBootstrap postmanBootstrap) {
        this.postmanBootstrapMap.put(name, postmanBootstrap);
    }

    public Object runPostmanBootstraps(final List<String> names,
                                       final HttpServletRequest request,
                                       final HttpServletResponse response,
                                       final User user, final Map<String, Object> contextMap) {

        final Map<String, Object> resultMap = new HashMap<>();
        for (final String name : names) {
            if (this.postmanBootstrapMap.containsKey(name)) {
                final Object result = this.postmanBootstrapMap.get(name).run(request, response, user, contextMap);
                resultMap.put(name, result);
            } else {
                resultMap.put(name, "PostmanBootstrap not found: " + name);
            }
        }


        return resultMap;
    }
}
