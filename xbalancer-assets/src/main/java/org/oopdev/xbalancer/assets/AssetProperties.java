
package org.oopdev.xbalancer.assets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssetProperties {

    /**
     * used to define Servlet by type {@link AssetType}.
     */
    @JsonProperty
    private AssetType type;

    /**
     * the base URL or base file system path from which assets are loaded
     */
    @JsonProperty
    private String resourcePath;

    /**
     * the URI path fragment in which all requests are rooted
     */
    @JsonProperty
    private String uriPath;

    /**
     * the filename to use when directories are requested, or null to serve no
     */
    @JsonProperty
    private String indexFile;

    /**
     * it is unique name for asset
     */
    @JsonProperty
    private String assetsName;

    @JsonProperty
    private boolean cached;

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getUriPath() {
        return uriPath;
    }

    public void setUriPath(String uriPath) {
        this.uriPath = uriPath;
    }

    public String getIndexFile() {
        return indexFile;
    }

    public void setIndexFile(String indexFile) {
        this.indexFile = indexFile;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public boolean isCached() {
        return cached;
    }

    public void setCached(boolean cached) {
        this.cached = cached;
    }
}
