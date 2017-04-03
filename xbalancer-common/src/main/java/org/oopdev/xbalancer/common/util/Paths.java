package org.oopdev.xbalancer.common.util;

/**
 * Created by kamilbukum on 07/03/2017.
 */
public class Paths {
    /**
     * Helps to fix path
     *
     * @param path to fix
     * @return
     */
    public static String fixPath(String path) {
        if (!path.isEmpty()) {
            if (!path.endsWith("/"))
                return path + '/';
            else
                return path;
        } else
            return path;
    }
}
