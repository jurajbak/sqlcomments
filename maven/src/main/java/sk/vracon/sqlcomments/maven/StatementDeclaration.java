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

public class StatementDeclaration {

    private int declarationLineNumber;
    private String baseClassName;
    private String name;
    private boolean defaultResultClass;
    private String resultClassName;
    private String configurationClassName;
    private boolean defaultConfigurationClass;
    private String statementText;

    /**
     * @return the declarationLineNumber
     */
    public int getDeclarationLineNumber() {
        return declarationLineNumber;
    }

    /**
     * @param declarationLineNumber
     *            the declarationLineNumber to set
     */
    public void setDeclarationLineNumber(int declarationLineNumber) {
        this.declarationLineNumber = declarationLineNumber;
    }

    /**
     * @return the baseClassName
     */
    public String getBaseClassName() {
        return baseClassName;
    }

    /**
     * @param baseClassName
     *            the baseClassName to set
     */
    public void setBaseClassName(String baseClassName) {
        this.baseClassName = baseClassName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the defaultResultClass
     */
    public boolean isDefaultResultClass() {
        return defaultResultClass;
    }

    /**
     * @param defaultResultClass
     *            the defaultResultClass to set
     */
    public void setDefaultResultClass(boolean defaultResultClass) {
        this.defaultResultClass = defaultResultClass;
    }

    /**
     * @return the resultClassName
     */
    public String getResultClassName() {
        return resultClassName;
    }

    /**
     * @param resultClassName
     *            the resultClassName to set
     */
    public void setResultClassName(String resultClassName) {
        this.resultClassName = resultClassName;
    }

    /**
     * @return the configurationClassName
     */
    public String getConfigurationClassName() {
        return configurationClassName;
    }

    /**
     * @param configurationClassName
     *            the configurationClassName to set
     */
    public void setConfigurationClassName(String configurationClassName) {
        this.configurationClassName = configurationClassName;
    }

    /**
     * @return the defaultConfigurationClass
     */
    public boolean isDefaultConfigurationClass() {
        return defaultConfigurationClass;
    }

    /**
     * @param defaultConfigurationClass
     *            the defaultConfigurationClass to set
     */
    public void setDefaultConfigurationClass(boolean defaultConfigurationClass) {
        this.defaultConfigurationClass = defaultConfigurationClass;
    }

    /**
     * @return the statementText
     */
    public String getStatementText() {
        return statementText;
    }

    /**
     * @param statementText
     *            the statementText to set
     */
    public void setStatementText(String statementText) {
        this.statementText = statementText;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StatementDeclaration [declarationLineNumber=");
        builder.append(declarationLineNumber);
        builder.append(", baseClassName=");
        builder.append(baseClassName);
        builder.append(", name=");
        builder.append(name);
        builder.append(", defaultResultClass=");
        builder.append(defaultResultClass);
        builder.append(", resultClassName=");
        builder.append(resultClassName);
        builder.append(", configurationClassName=");
        builder.append(configurationClassName);
        builder.append(", defaultConfigurationClass=");
        builder.append(defaultConfigurationClass);
        builder.append(", statementText=");
        builder.append(statementText);
        builder.append("]");
        return builder.toString();
    }
}
