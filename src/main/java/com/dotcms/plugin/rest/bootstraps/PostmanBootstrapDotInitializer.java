package com.dotcms.plugin.rest.bootstraps;

import com.dotcms.config.DotInitializer;
import com.dotcms.plugin.rest.PostmanBootstrap;
import com.dotcms.plugin.rest.PostmanBootstrapAPI;

import java.util.ArrayList;
import java.util.List;

public class PostmanBootstrapDotInitializer implements DotInitializer {
    @Override
    public void init() {

        final List<PostmanBootstrap> postmanBootstraps = new ArrayList<>();
        postmanBootstraps.add(new CreatePageWithSystemTemplateBootstrap());

        for (final PostmanBootstrap postmanBootstrap : postmanBootstraps) {
            PostmanBootstrapAPI.INSTANCE.registerPostmanBootstrap(postmanBootstrap.getName(), postmanBootstrap);
        }
    }
}
