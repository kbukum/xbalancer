package org.oopdev.xbalancer.assets;

import org.oopdev.xbalancer.common.util.Strings;

import javax.servlet.http.HttpServlet;

/**
 * Created by kamilbukum on 07/03/2017.
 */
public abstract class AssetServlet extends HttpServlet {
    /**
     * resource path is reference to resource by {@link AssetType}
     *
     * @return
     */
    abstract public String getResourcePath();

    /**
     * uri path is alias path to get resource
     *
     * @return
     */
    abstract public String getUriPath();

    public static String getUriPath(String uriPath) {
        if (!Strings.has(uriPath)) {
            uriPath = "/";
        }
        if (!uriPath.endsWith("*")) {
            if (uriPath.endsWith("/")) {
                uriPath = uriPath + "/";
            }
            uriPath = uriPath + "*";
        }
        return uriPath;
    }
}
