package com.example.demo1.image;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * description: 图片校验
 *
 * @author fengsihan
 * @date 2019/8/1 18:30
 */
@RestController
@RequestMapping("/biz/upload")
@Slf4j
public class ImageController {

    /**
     * 图片上传 bos
     *
     * @param imageUrl 图片地址，多条以逗号分隔
     *
     * @return
     */
    @GetMapping({"/img"})
    public List imgUploadToBos(String imageUrl) {
        log.info("imgUploadToBos param:{}", imageUrl);
        List<Map<String, Object>> outputImgList = Lists.newArrayList();
        String[] imgList;
        if (imageUrl.contains(";@;")) {
            imgList = imageUrl.split(";@;");
        } else {
            imgList = imageUrl.split(",");
        }
        for (String img : imgList) {
            BufferedImage _newImg;
            BufferedInputStream bf = null;
            Map<String, Object> map = Maps.newHashMap();
            try {
                Stopwatch totalStopWatch = Stopwatch.createStarted();
                URL url = new URL(img);
                HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, trustAllCerts, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                //                    URL url = new URL(img);
                // 打开restful链接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                // 设置访问提交模式，表单提交
                conn.setRequestProperty("User-Agent", "baiduspider");
                conn.setConnectTimeout(2000);
                conn.setReadTimeout(2000);

                map.put("size", conn.getContentLength());
                bf = new BufferedInputStream(conn.getInputStream());
                FileTypeEnum fileType = UploadUtil.getFileType(bf);

                if (!fileType.equals(FileTypeEnum.jpg) && !fileType.equals(FileTypeEnum.png)) {
                    // 无效
                }

                BufferedImage _img = ImageIO.read(bf);
                _newImg = new BufferedImage(_img.getWidth(), _img.getHeight(), BufferedImage.TYPE_INT_RGB);
                _newImg.getGraphics().drawImage(_img, 0, 0, null);
                log.info("imageAnalysis param:{} cost:{}", img, totalStopWatch.elapsed(TimeUnit.MILLISECONDS));
            } catch (SocketTimeoutException e) {
                log.error("imgResolveTimeOutError img:{}", img, e);
            } catch (IOException e1) {
                log.error("imgResolveError img:{}", img, e1);
                //  String.format("图片链接无效[%s]", img);
            } catch (Exception e) {
            } finally {
                if (bf != null) {
                    try {
                        bf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return null;
    }

    static TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
    };

    public class NullHostNameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String arg0, SSLSession arg1) {
            return true;
        }
    }
}