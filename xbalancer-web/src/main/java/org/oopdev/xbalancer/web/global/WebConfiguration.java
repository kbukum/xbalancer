package org.oopdev.xbalancer.web.global;

import org.oopdev.xbalancer.assets.AssetProperties;
import org.oopdev.xbalancer.assets.file.FileAssetServlet;
import org.oopdev.xbalancer.assets.http.HttpAssetServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@Configuration
@ConfigurationProperties(prefix = "xb")
public class WebConfiguration {

    private AssetProperties asset;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        if (asset == null) {
            return null;
        }
        switch (asset.getType()) {
            case http:
                return new ServletRegistrationBean(new HttpAssetServlet(asset, Charset.forName("UTF-8")), asset.getUriPath());
            case filesystem:
                return new ServletRegistrationBean(new FileAssetServlet(asset, Charset.forName("UTF-8")), asset.getUriPath());
        }
        return null;
    }

    public void setAsset(AssetProperties asset) {
        this.asset = asset;
    }

    public AssetProperties getAsset() {
        return asset;
    }
}
