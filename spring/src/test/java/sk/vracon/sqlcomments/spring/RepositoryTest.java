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
package sk.vracon.sqlcomments.spring;

import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sk.vracon.sqlcomments.core.impl.ReflectionBeanMapper;
import sk.vracon.sqlcomments.core.impl.StatementConfigurationAdapter;
import sk.vracon.sqlcomments.spring.sqlcomments.BlobConfig;
import sk.vracon.sqlcomments.spring.sqlcomments.BlobTestMapper;
import sk.vracon.sqlcomments.spring.sqlcomments.FindCompanyConfig;
import sk.vracon.sqlcomments.spring.sqlcomments.FindCompanyMapper;
import sk.vracon.sqlcomments.spring.sqlcomments.PlaceholderInsideInClauseConfig;
import sk.vracon.sqlcomments.spring.sqlcomments.PlaceholderInsideInClauseMapper;
import sk.vracon.sqlcomments.spring.sqlcomments.PlaceholderInsideLikeClauseConfig;
import sk.vracon.sqlcomments.spring.sqlcomments.PlaceholderInsideLikeClauseMapper;
import sk.vracon.sqlcomments.spring.sqlcomments.ReplacementTestConfig;
import sk.vracon.sqlcomments.spring.sqlcomments.ReplacementTestMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
@Repository
public class RepositoryTest extends GenericTestRepository {

    @Test
    public void testSelectList() throws Exception {
        /*-
          @SQLComment(name="findCompany", resultClass="sk.vracon.sqlcomments.spring.FindCompany", configClass="sk.vracon.sqlcomments.spring.sqlcomments.FindCompanyConfig")
          select comp.id, comp.name from companies comp
          where comp.id = :companyId
         */

        FindCompanyConfig config = new FindCompanyConfig();
        config.setCompanyId(5);

        List<FindCompany> companies = list(config, new FindCompanyMapper());

        Assert.assertNotNull(companies);
        Assert.assertEquals(1, companies.size());

        FindCompany companyInfo = companies.get(0);

        Assert.assertNotNull(companyInfo);
        Assert.assertNotNull(companyInfo.getId());
        Assert.assertEquals(5, companyInfo.getId().intValue());
    }

    @Test
    public void testSingleResult_NotNull() throws Exception {
        // Reusing select findCompany
        FindCompanyConfig config = new FindCompanyConfig();
        config.setCompanyId(5);

        FindCompany companyInfo = singleResult(config, new FindCompanyMapper());

        Assert.assertNotNull(companyInfo);
        Assert.assertNotNull(companyInfo.getId());
        Assert.assertEquals(5, companyInfo.getId().intValue());
    }

    @Test
    public void testSingleResult_Null() throws Exception {
        // Reusing select findCompany
        FindCompanyConfig config = new FindCompanyConfig();
        config.setCompanyId(-1);

        FindCompany companyInfo = singleResult(config, new FindCompanyMapper());

        Assert.assertNull(companyInfo);
    }

    @Test
    public void testPlaceholderInsideInClause() throws Exception {
        /*-
          @SQLComment(name="placeholderInsideInClause", resultClass="sk.vracon.sqlcomments.spring.PlaceholderInsideInClause", configClass="sk.vracon.sqlcomments.spring.sqlcomments.PlaceholderInsideInClauseConfig")
          select comp.id, comp.name from companies comp
          where comp.id IN (:companyId)
          order by comp.id
         */

        Set<Integer> ids = new HashSet<Integer>();
        ids.add(5);
        ids.add(6);
        ids.add(7);

        PlaceholderInsideInClauseConfig config = new PlaceholderInsideInClauseConfig();
        config.setCompanyId(ids);

        List<PlaceholderInsideInClause> companies = list(config, new PlaceholderInsideInClauseMapper());

        Assert.assertNotNull(companies);
        Assert.assertEquals(3, companies.size());

        PlaceholderInsideInClause companyInfo1 = companies.get(0);

        Assert.assertNotNull(companyInfo1);
        Assert.assertNotNull(companyInfo1.getId());
        Assert.assertEquals(5, companyInfo1.getId().intValue());

        PlaceholderInsideInClause companyInfo2 = companies.get(1);

        Assert.assertNotNull(companyInfo2);
        Assert.assertNotNull(companyInfo2.getId());
        Assert.assertEquals(6, companyInfo2.getId().intValue());

        PlaceholderInsideInClause companyInfo3 = companies.get(2);

        Assert.assertNotNull(companyInfo3);
        Assert.assertNotNull(companyInfo3.getId());
        Assert.assertEquals(7, companyInfo3.getId().intValue());

    }

    @Test
    public void testPlaceholderInsideLikeClause() throws Exception {
        /*-
          @SQLComment(name="placeholderInsideLikeClause", resultClass="sk.vracon.sqlcomments.spring.PlaceholderInsideLikeClause", configClass="sk.vracon.sqlcomments.spring.sqlcomments.PlaceholderInsideLikeClauseConfig")
          select comp.id, comp.name from companies comp
          where comp.name LIKE :companyName
         */

        PlaceholderInsideLikeClauseConfig config = new PlaceholderInsideLikeClauseConfig();
        config.setCompanyName("D%");

        List<PlaceholderInsideLikeClause> companies = list(config, new PlaceholderInsideLikeClauseMapper());

        Assert.assertNotNull(companies);
        Assert.assertEquals(2, companies.size());

        PlaceholderInsideLikeClause companyInfo1 = companies.get(0);

        Assert.assertNotNull(companyInfo1);
        Assert.assertNotNull(companyInfo1.getId());
        Assert.assertNotNull(companyInfo1.getName());
        Assert.assertTrue(companyInfo1.getName().startsWith("D"));

        PlaceholderInsideLikeClause companyInfo2 = companies.get(1);

        Assert.assertNotNull(companyInfo2);
        Assert.assertNotNull(companyInfo2.getId());
        Assert.assertNotNull(companyInfo2.getName());
        Assert.assertTrue(companyInfo2.getName().startsWith("D"));
    }

    @Test
    public void testReplacement() throws Exception {
        /*-
          @SQLComment(name="replacementTest", resultClass="sk.vracon.sqlcomments.spring.ReplacementTest", configClass="sk.vracon.sqlcomments.spring.sqlcomments.ReplacementTestConfig")
          select comp.id, comp.name from companies comp
          where comp.id IN (:companyId)
          order by ${orderBy} -- //@ orderBy != null
         */

        Set<Integer> ids = new HashSet<Integer>();
        ids.add(5);
        ids.add(6);
        ids.add(7);

        ReplacementTestConfig config = new ReplacementTestConfig();
        config.setCompanyId(ids);
        config.setOrderBy("comp.id desc");

        List<ReplacementTest> companies = list(config, new ReplacementTestMapper());

        Assert.assertNotNull(companies);
        Assert.assertEquals(3, companies.size());

        ReplacementTest companyInfo1 = companies.get(0);

        Assert.assertNotNull(companyInfo1);
        Assert.assertNotNull(companyInfo1.getId());
        Assert.assertEquals(7, companyInfo1.getId().intValue());

        ReplacementTest companyInfo2 = companies.get(1);

        Assert.assertNotNull(companyInfo2);
        Assert.assertNotNull(companyInfo2.getId());
        Assert.assertEquals(6, companyInfo2.getId().intValue());

        ReplacementTest companyInfo3 = companies.get(2);

        Assert.assertNotNull(companyInfo3);
        Assert.assertNotNull(companyInfo3.getId());
        Assert.assertEquals(5, companyInfo3.getId().intValue());

    }

    @Test
    public void testReplacementInvalidSQL() throws Exception {

        List<FindCompany> companies = list(new StatementConfigurationAdapter("replacementInvalidSQL", RepositoryTest.class) {
            public Map<String, Object> generateParameterMap() {
                Map<String, Object> params = new HashMap<String, Object>();

                params.put("orderBy", "id");
                params.put("orderDirection", "desc");

                return params;
            }
        }, ReflectionBeanMapper.createInstance(FindCompany.class));

        Assert.assertNotNull(companies);
        Assert.assertEquals(10, companies.size());

        int indx = 10;
        for (FindCompany company : companies) {
            Assert.assertNotNull(company);
            Assert.assertNotNull(company.getId());
            Assert.assertEquals(indx, company.getId().intValue());

            indx--;
        }
    }

    @Test
    public void testBlob() throws Exception {
        /*-
          @SQLComment(name="blobTest", resultClass="sk.vracon.sqlcomments.spring.BlobTest", configClass="sk.vracon.sqlcomments.spring.sqlcomments.BlobConfig")
          select * from documents
          where id = 1
         */
        BlobConfig config = new BlobConfig();

        List<BlobTest> documents = list(config, new BlobTestMapper());

        Assert.assertNotNull(documents);
        Assert.assertEquals(1, documents.size());

        BlobTest doc = documents.get(0);
        Blob data = doc.getData();

        Assert.assertNotNull(data);

        InputStream stream = data.getBinaryStream();

        Assert.assertNotNull(stream);

        stream.close();
    }
}
