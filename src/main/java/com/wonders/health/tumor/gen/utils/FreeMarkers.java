/**
 *
 */
package com.wonders.health.tumor.gen.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;


/**
 * FreeMarkers工具类
 *
 * @version 2013-01-15
 */
public class FreeMarkers {

    private static Logger logger = LoggerFactory.getLogger(FreeMarkers.class);


    public static String getResourceUrl(String moduleName) {
        File file = new File("");
        StringBuffer fileUrl = new StringBuffer();
        fileUrl.append(file.getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper");
        fileUrl.append(File.separator + moduleName + File.separator);
        return fileUrl.toString();
    }

    public static String getBaseJspUrl(String moduleName) {
        File file = new File("");
        StringBuffer fileUrl = new StringBuffer();
        fileUrl.append(file.getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "views");
        fileUrl.append(File.separator + moduleName + File.separator);
        return fileUrl.toString();
    }

    public static String getBaseJavaUrl(String packageName, String moduleName) {
        String arr[] = packageName.split("\\.");
        File file = new File("");
        StringBuffer fileUrl = new StringBuffer();
        fileUrl.append(file.getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "java");
        for (String a : arr) {
            fileUrl.append(File.separator + a);
        }
        fileUrl.append(File.separator + moduleName + File.separator);
        return fileUrl.toString();
    }

    public static String getTestBaseUrl(String packageName, String moduleName) {
        String arr[] = packageName.split("\\.");
        File file = new File("");
        StringBuffer fileUrl = new StringBuffer();
        fileUrl.append(file.getAbsolutePath() + File.separator + "src" + File.separator + "test" + File.separator + "java");
        for (String a : arr) {
            fileUrl.append(File.separator + a);
        }
        fileUrl.append(File.separator + "test");
        fileUrl.append(File.separator + "gen" + File.separator + moduleName + File.separator);
        return fileUrl.toString();
    }

    /**
     * XML文件转换为对象
     *
     * @param filepath
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fileToObject(String filepath, Class<?> clazz) {
        String content = "";
        try {
            content = FileUtils.readFileToString(new File(filepath), "utf-8");
            return (T) XmlUtils.fromXml(content, clazz);
        } catch (IOException e) {
            logger.warn("error convert: {}", e.getMessage());
        }
        return null;
    }

    public static <T> T fileToObject(URL url, Class<?> clazz) {
        String content = "";
        try {
            content = IOUtils.toString(url, "utf-8");
            return (T) XmlUtils.fromXml(content, clazz);
        } catch (IOException e) {
            logger.warn("error convert: {}", e.getMessage());
        }
        return null;
    }

    public static String renderString(String templateString, Map<String, ?> model) {
        try {
            StringWriter result = new StringWriter();
            Template t = new Template("name", new StringReader(templateString), new Configuration());
            t.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static String renderTemplate(Template template, Object model) {
        try {
            StringWriter result = new StringWriter();
            template.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration();
        Resource path = new DefaultResourceLoader().getResource(directory);
        cfg.setDirectoryForTemplateLoading(path.getFile());
        return cfg;
    }

}
