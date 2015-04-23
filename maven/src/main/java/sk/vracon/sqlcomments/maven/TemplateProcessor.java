/*
 * Copyright 2014 Vracon s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sk.vracon.sqlcomments.maven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;

import sk.vracon.sqlcomments.core.Utils;
import sk.vracon.sqlcomments.maven.generate.AbstractStatementContext;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInfo;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

public class TemplateProcessor {

    private static final String TEMPLATE_RESULT_MAPPER = "template/ResultMapper.ftl";

    private static final String TEMPLATE_RESULT_CLASS = "template/ResultClass.ftl";

    private static final String TEMPLATE_CONFIGURATION = "template/StatementConfiguration.ftl";

    private static final String TEMPLATE_INSERT = "template/Insert.ftl";

    private static final String TEMPLATE_UPDATE = "template/Update.ftl";

    private static final String TEMPLATE_DELETE = "template/Delete.ftl";

    private static final String TEMPLATE_FIND_BY_PK = "template/FindByPK.ftl";

    private static final String TEMPLATE_DOMAIN_CONFIGURATION = "template/DomainConfiguration.ftl";

    private Log log;

    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);

    public TemplateProcessor(Log log) throws TemplateModelException {
        this.log = log;

        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(TemplateProcessor.class, "/");
        configuration.setSharedVariable("utils", new BeansWrapperBuilder(Configuration.getVersion()).build().getStaticModels().get(Utils.class.getName()));
    }

    public void populateResultTemplate(File outputDirectory, String className, AbstractStatementContext selectContext, StatementDeclaration declaration,
            Map<String, Object> extraTemplateModel) throws IOException {
        populateResultTemplate(TEMPLATE_RESULT_CLASS, outputDirectory, className, selectContext, declaration, false, extraTemplateModel);
    }

    public void populateResultMapperTemplate(File outputDirectory, String className, AbstractStatementContext selectContext, StatementDeclaration declaration,
            Map<String, Object> extraTemplateModel) throws IOException {
        populateResultTemplate(TEMPLATE_RESULT_MAPPER, outputDirectory, className, selectContext, declaration, true, extraTemplateModel);
    }

    private void populateResultTemplate(String templateName, File outputDirectory, String className, AbstractStatementContext selectContext,
            StatementDeclaration declaration, boolean mapper, Map<String, Object> extraTemplateModel) throws IOException {

        // Build the data-model
        String packageName = null;
        String simpleClassName = className;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
            simpleClassName = className.substring(lastDot + 1);
        }

        Map<String, Object> data = createGenericData(packageName, simpleClassName, declaration);
        data.put("selectContext", selectContext);
        data.putAll(extraTemplateModel);

        // Create file name and write file
        String fileName = packageName == null ? "" : (packageName.replace('.', File.separatorChar) + File.separator);
        fileName += mapper ? ("sqlcomments" + File.separator) : "";
        fileName += simpleClassName + (mapper ? "Mapper" : "") + ".java";

        writeFile(outputDirectory, fileName, templateName, data);
    }

    public void populateGenericConfigurationTemplate(File outputDirectory, String className, StatementDeclaration declaration,
            List<PlaceholderInfo> placeholders) throws IOException {
        populateConfigurationTemplate(outputDirectory, className, TEMPLATE_CONFIGURATION, declaration, placeholders, null);
    }

    public void populateDomainConfigurationTemplate(File outputDirectory, String className, StatementDeclaration declaration,
            List<PlaceholderInfo> placeholders, Map<String, Object> extraTemplateModel) throws IOException {
        populateConfigurationTemplate(outputDirectory, className, TEMPLATE_DOMAIN_CONFIGURATION, declaration, placeholders, extraTemplateModel);
    }

    private void populateConfigurationTemplate(File outputDirectory, String className, String templateName, StatementDeclaration declaration,
            List<PlaceholderInfo> placeholders, Map<String, Object> extraTemplateModel) throws IOException {

        // Build the data-model
        String packageName = null;
        String simpleClassName = className;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
            simpleClassName = className.substring(lastDot + 1);
        }

        Map<String, Object> data = createGenericData(packageName, simpleClassName, declaration);
        data.put("placeholders", placeholders);
        if (extraTemplateModel != null) {
            data.putAll(extraTemplateModel);
        }

        // Create file name and write file
        String fileName = className.replace('.', File.separatorChar) + ".java";

        writeFile(outputDirectory, fileName, templateName, data);
    }

    public Map<String, Object> createGenericData(String packageName, String simpleClassName, StatementDeclaration declaration) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("packageName", packageName);
        data.put("simpleClassName", simpleClassName);
        data.put("statementDeclaration", declaration);
        return data;
    }

    public void writeInsert(File outputDirectory, String fileName, Map<String, Object> data) throws IOException {
        writeFile(outputDirectory, fileName, TEMPLATE_INSERT, data);
    }

    public void writeUpdate(File outputDirectory, String fileName, Map<String, Object> data) throws IOException {
        writeFile(outputDirectory, fileName, TEMPLATE_UPDATE, data);
    }

    public void writeDelete(File outputDirectory, String fileName, Map<String, Object> data) throws IOException {
        writeFile(outputDirectory, fileName, TEMPLATE_DELETE, data);
    }

    public void writeFindByPK(File outputDirectory, String fileName, Map<String, Object> data) throws IOException {
        writeFile(outputDirectory, fileName, TEMPLATE_FIND_BY_PK, data);
    }

    private void writeFile(File outputDirectory, String fileName, String templateName, Map<String, Object> data) throws IOException {
        log.info("Generating file " + fileName);

        // Load template
        Template template = configuration.getTemplate(templateName);

        // Create file and missing directories
        File outputFile = new File(outputDirectory, fileName);
        outputFile.getParentFile().mkdirs();

        // Write template
        Writer writer = null;
        try {
            data.put("templateUtils", new TemplateUtils());

            writer = new FileWriter(outputFile);
            template.process(data, writer);
            writer.flush();
        }
        catch (TemplateException e) {
            throw new IOException("Export file " + fileName + " failed: " + e.getMessage(), e);
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (Exception e) {}
            }
        }
    }
}
