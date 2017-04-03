package org.oopdev.xbalancer.assets.util;

import com.google.common.net.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * Created by kamilbukum on 07/03/2017.
 */
public class EncodingUtil {

    public static final MediaType DEFAULT_MEDIA_TYPE = MediaType.HTML_UTF_8;

    public static void decideMimeAndEncoding(HttpServletRequest req, HttpServletResponse resp, Charset charset) {
        //Decide mimetype
        String mime = req.getServletContext().getMimeType(req.getRequestURI());
        MediaType mediaType = (mime == null) ? DEFAULT_MEDIA_TYPE : MediaType.parse(mime);
        if (charset != null && mediaType.is(MediaType.ANY_TEXT_TYPE)) {
            mediaType = mediaType.withCharset(charset);
        }
        resp.setContentType(mediaType.type() + '/' + mediaType.subtype());
        if (mediaType.charset().isPresent()) {
            resp.setCharacterEncoding(mediaType.charset().get().toString());
        }
    }
}
