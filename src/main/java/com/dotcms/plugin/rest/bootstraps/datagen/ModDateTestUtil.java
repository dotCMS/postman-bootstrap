package com.dotcms.plugin.rest.bootstraps.datagen;

import com.dotmarketing.business.APILocator;
import com.dotmarketing.db.LocalTransaction;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotRuntimeException;
import com.dotmarketing.portlets.contentlet.model.Contentlet;
import com.dotmarketing.portlets.contentlet.model.ContentletVersionInfo;

import java.util.Date;
import java.util.Optional;

public class ModDateTestUtil {



    public static void updateContentletVersionDate(final Contentlet contentlet, final Date modDate)  {

        try {
            LocalTransaction.wrap(()-> updateContentletVersionDate(contentlet.getIdentifier(), contentlet.getLanguageId(), modDate));
        } catch (Exception e) {
            throw new DotRuntimeException(e);
        }
    }




    // no delete
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
