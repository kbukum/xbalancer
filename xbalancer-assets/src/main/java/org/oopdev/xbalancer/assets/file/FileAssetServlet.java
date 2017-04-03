package org.oopdev.xbalancer.assets.file;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.net.HttpHeaders;
import org.oopdev.xbalancer.assets.AssetProperties;
import org.oopdev.xbalancer.assets.AssetServlet;
import org.oopdev.xbalancer.assets.util.EncodingUtil;
import org.oopdev.xbalancer.common.util.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 *
 */
public class FileAssetServlet extends AssetServlet {

    private Cache<String, FileAsset> cache;
    /**
     * the base URL from which assets are loaded
     */
    private final String resourcePath;
    /**
     * the URI path fragment in which all requests are rooted
     */
    private final String uriPath;
    /**
     * the filename to use when directories are requested, or null to serve no indexes
     */
    private final String indexFile;
    /**
     * the default character set
     */
    private final Charset defaultCharset;
    /**
     *
     */
    private final boolean cached;

    /**
     * Creates a new {@code FileAssetServlet} that serves static assets loaded from {@code resourceURL}
     *
     * @param asset
     * @param defaultCharset the default character set
     */
    public FileAssetServlet(AssetProperties asset, Charset defaultCharset) {
        this.resourcePath = Paths.fixPath(asset.getResourcePath());
        String uriPath = asset.getUriPath();
        uriPath = uriPath.endsWith("/") ? uriPath.substring(0, uriPath.length() - 1) : uriPath;
        this.uriPath = uriPath.isEmpty() ? "/" : uriPath;
        this.indexFile = asset.getIndexFile();
        this.defaultCharset = defaultCharset;
        this.cached = asset.isCached();
        cache = CacheBuilder.newBuilder().build();
    }


    public String getIndexFile() {
        return indexFile;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        try {
            final StringBuilder builder = new StringBuilder(req.getServletPath());

            // If http is empty redirect to index http.
            if (req.getPathInfo() != null) {
                builder.append(req.getPathInfo());
            }
            String assetPath = builder.toString();
            //Get from cache if not available load it.
            FileAsset asset = cache.getIfPresent(assetPath);
            try {
                if (asset == null) {
                    asset = loadAsset(assetPath);
                }
            } catch (RuntimeException e) {

            }
            if (asset == null) {
                if (!assetPath.contains(".")) {
                    builder.delete(builder.lastIndexOf("/"), builder.length());
                    builder.append("/").append(getIndexFile());
                    asset = loadAsset(builder.toString());
                }

            }
            //If still it is null it means nothing to load.
            if (asset == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Maybe client got it already
            if (isDifferentFromClientCache(req, asset)) {
                resp.sendError(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }

            //Modify response identifiers
            resp.setDateHeader(HttpHeaders.LAST_MODIFIED, asset.getLastModified());
            resp.setHeader(HttpHeaders.ETAG, asset.getMd5());

            EncodingUtil.decideMimeAndEncoding(req, resp, defaultCharset);


            try (ServletOutputStream output = resp.getOutputStream()) {
                output.write(asset.loadAsset());
            }
        } catch (RuntimeException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private FileAsset loadAsset(String path) {
        if (!path.startsWith(uriPath))
            return null;
        String absolutePath = path.substring(uriPath.length());
        absolutePath = absolutePath.startsWith("/") ? absolutePath.substring(1) : absolutePath;
        absolutePath = absolutePath.endsWith("/") ? absolutePath.substring(0, absolutePath.length() - 1) : absolutePath;
        absolutePath = this.resourcePath + absolutePath;

        FileAsset asset = new FileAsset(absolutePath, cached);
        cache.put(path, asset);

        return asset;
    }

    private long msToSec(long time) {
        return time / 1000;
    }

    private boolean isDifferentFromClientCache(HttpServletRequest req, FileAsset asset) {
        return asset.getMd5().equals(req.getHeader(HttpHeaders.IF_NONE_MATCH)) ||
                (msToSec(req.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE)) >= msToSec(asset.getLastModified()));
    }

    @Override
    public String getResourcePath() {
        return resourcePath;
    }

    @Override
    public String getUriPath() {
        return uriPath;
    }
}
