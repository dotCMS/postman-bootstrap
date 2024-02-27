package com.dotcms.plugin.rest;

import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Implement this interface to init you postman bootstrap
 */
public interface PostmanBootstrap {

    default String getName () {
        return this.getClass().getName();
    }

    Object run (final HttpServletRequest request, final HttpServletResponse response,
              final User user, final Map<String, Object> contextMap);
}
