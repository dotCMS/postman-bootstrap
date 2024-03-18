package com.dotcms.plugin.rest.bootstraps;

import com.dotcms.plugin.rest.PostmanBootstrap;
import com.dotcms.plugin.rest.bootstraps.datagen.HTMLPageDataGen;
import com.dotmarketing.beans.Host;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.web.WebAPILocator;
import com.dotmarketing.portlets.templates.model.Template;
import com.liferay.portal.model.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CreatePageWithSystemTemplateBootstrap implements PostmanBootstrap {
    @Override
    public String getName() {
        return "create-page-with-system-template-bootstrap";
    }

    @Override
    public Object run(final HttpServletRequest request,
                      final HttpServletResponse response,
                      final User user,
                      final Map<String, Object> contextMap) {

        final Host currentSite = WebAPILocator.getHostWebAPI().getCurrentHostNoThrow(request);
        final Template systemTemplate = APILocator.getSystemAPI().getSystemTemplate();
        return new HTMLPageDataGen(currentSite, systemTemplate).nextPersisted();
    }
}
