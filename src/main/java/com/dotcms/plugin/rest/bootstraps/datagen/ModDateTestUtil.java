package com.dotcms.plugin.rest.bootstraps.datagen;

import com.dotcms.business.WrapInTransaction;
import com.dotmarketing.beans.Identifier;
import com.dotmarketing.beans.VersionInfo;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.FactoryLocator;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotRuntimeException;
import com.dotmarketing.exception.DotSecurityException;
import com.dotmarketing.portlets.containers.model.Container;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.contentlet.model.ContentletVersionInfo;
import com.dotmarketing.portlets.templates.model.Template;

import java.util.Date;
import java.util.Optional;

public class ModDateTestUtil {



    public static void updateContentletVersionDate(final Contentlet contentlet, final Date modDate) {
        updateContentletVersionDate(contentlet.getIdentifier(), contentlet.getLanguageId(), modDate);
    }



    // no delete
    @WrapInTransaction
    public static void updateContentletVersionDate(final String assetId, final long langId, final Date modDate) {

        try {
            final Optional<ContentletVersionInfo> contentletVersionInfoOptional =
                    APILocator.getVersionableAPI().getContentletVersionInfo(assetId, langId);

            if (contentletVersionInfoOptional.isPresent()) {
                final ContentletVersionInfo contentletVersionInfo = contentletVersionInfoOptional
                        .get();

                contentletVersionInfo.setVersionTs(modDate);
                APILocator.getVersionableAPI().saveContentletVersionInfo(contentletVersionInfo);
            }
        } catch (DotDataException e) {
            throw new DotRuntimeException(e);
        }
    }
}
