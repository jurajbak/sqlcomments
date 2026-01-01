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

import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.core.impl.StatementConfigurationAdapter;
import sk.vracon.sqlcomments.core.resultmappers.ReflectionBeanMapper;
import sk.vracon.sqlcomments.spring.sqlcomments.BlobConfig;
import sk.vracon.sqlcomments.spring.sqlcomments.BlobTestMapper;
import sk.vracon.sqlcomments.spring.sqlcomments.FindDepartmentConfig;
import sk.vracon.sqlcomments.spring.sqlcomments.FindDepartmentMapper;
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
          @SQLComment(name="findDepartment", resultClass="sk.vracon.sqlcomments.spring.FindDepartment", configClass="sk.vracon.sqlcomments.spring.sqlcomments.FindDepartmentConfig")
          select d.deptno, d.dname from dept d
          where d.deptno = :departmentId
         */

        FindDepartmentConfig config = new FindDepartmentConfig();
        config.setDepartmentId(20);

        List<FindDepartment> departments = list(config, new FindDepartmentMapper());

        Assert.assertNotNull(departments);
        Assert.assertEquals(1, departments.size());

        FindDepartment department = departments.get(0);

        Assert.assertNotNull(department);
        Assert.assertNotNull(department.getDeptno());
        Assert.assertEquals(20, department.getDeptno().intValue());
    }

    @Test
    public void testSingleResult_NotNull() throws Exception {
        // Reusing select FindDepartment
        FindDepartmentConfig config = new FindDepartmentConfig();
        config.setDepartmentId(10);

        FindDepartment department = singleResult(config, new FindDepartmentMapper());

        Assert.assertNotNull(department);
        Assert.assertNotNull(department.getDeptno());
        Assert.assertEquals(10, department.getDeptno().intValue());
    }

    @Test
    public void testSingleResult_Null() throws Exception {
        // Reusing select FindDepartment
        FindDepartmentConfig config = new FindDepartmentConfig();
        config.setDepartmentId(-1);

        FindDepartment department = singleResult(config, new FindDepartmentMapper());

        Assert.assertNull(department);
    }

    @Test
    public void testPlaceholderInsideInClause() throws Exception {
        /*-
          @SQLComment(name="placeholderInsideInClause", resultClass="sk.vracon.sqlcomments.spring.PlaceholderInsideInClause", configClass="sk.vracon.sqlcomments.spring.sqlcomments.PlaceholderInsideInClauseConfig")
          select d.deptno, d.dname from dept d
          where d.deptno IN (:departmentNumbers)
          order by d.dname
         */

        Set<Integer> ids = new HashSet<Integer>();
        ids.add(20);
        ids.add(10);
        ids.add(30);

        PlaceholderInsideInClauseConfig config = new PlaceholderInsideInClauseConfig();
        config.setDepartmentNumbers(ids);

        List<PlaceholderInsideInClause> departments = list(config, new PlaceholderInsideInClauseMapper());

        Assert.assertNotNull(departments);
        Assert.assertEquals(3, departments.size());

        PlaceholderInsideInClause department1 = departments.get(0);

        Assert.assertNotNull(department1);
        Assert.assertNotNull(department1.getDeptno());
        Assert.assertEquals(10, department1.getDeptno().intValue());

        PlaceholderInsideInClause department2 = departments.get(1);

        Assert.assertNotNull(department2);
        Assert.assertNotNull(department2.getDeptno());
        Assert.assertEquals(20, department2.getDeptno().intValue());

        PlaceholderInsideInClause department3 = departments.get(2);

        Assert.assertNotNull(department3);
        Assert.assertNotNull(department3.getDeptno());
        Assert.assertEquals(30, department3.getDeptno().intValue());

    }

    @Test
    public void testPlaceholderInsideLikeClause() throws Exception {
        /*-
          @SQLComment(name="placeholderInsideLikeClause", resultClass="sk.vracon.sqlcomments.spring.PlaceholderInsideLikeClause", configClass="sk.vracon.sqlcomments.spring.sqlcomments.PlaceholderInsideLikeClauseConfig")
          select d.deptno, d.dname from dept d
          where d.dname LIKE :departmentName
         */

        PlaceholderInsideLikeClauseConfig config = new PlaceholderInsideLikeClauseConfig();
        config.setDepartmentName("R%");

        List<PlaceholderInsideLikeClause> companies = list(config, new PlaceholderInsideLikeClauseMapper());

        Assert.assertNotNull(companies);
        Assert.assertEquals(1, companies.size());

        PlaceholderInsideLikeClause department1 = companies.get(0);

        Assert.assertNotNull(department1);
        Assert.assertNotNull(department1.getDeptno());
        Assert.assertNotNull(department1.getDname());
        Assert.assertTrue(department1.getDname().startsWith("R"));
    }

    @Test
    public void testReplacement() throws Exception {
        /*-
          @SQLComment(name="replacementTest", resultClass="sk.vracon.sqlcomments.spring.ReplacementTest", configClass="sk.vracon.sqlcomments.spring.sqlcomments.ReplacementTestConfig")
          select d.deptno, d.dname from dept d
          where d.deptNo IN (:departmentNumbers)
          order by ${orderBy} -- //@ orderBy != null
         */

        Set<Integer> ids = new HashSet<Integer>();
        ids.add(10);
        ids.add(30);
        ids.add(20);

        ReplacementTestConfig config = new ReplacementTestConfig();
        config.setDepartmentNumbers(ids);
        config.setOrderBy("d.deptno desc");

        List<ReplacementTest> companies = list(config, new ReplacementTestMapper());

        Assert.assertNotNull(companies);
        Assert.assertEquals(3, companies.size());

        ReplacementTest department1 = companies.get(0);

        Assert.assertNotNull(department1);
        Assert.assertNotNull(department1.getDeptno());
        Assert.assertEquals(30, department1.getDeptno().intValue());

        ReplacementTest department2 = companies.get(1);

        Assert.assertNotNull(department2);
        Assert.assertNotNull(department2.getDeptno());
        Assert.assertEquals(20, department2.getDeptno().intValue());

        ReplacementTest department3 = companies.get(2);

        Assert.assertNotNull(department3);
        Assert.assertNotNull(department3.getDeptno());
        Assert.assertEquals(10, department3.getDeptno().intValue());

    }

    @Test
    public void testReplacementInvalidSQL() throws Exception {

    	List<FindDepartment> departments = list(new StatementConfigurationAdapter("replacementInvalidSQL", RepositoryTest.class) {
            public Map<String, Object> parameterMap() {
                Map<String, Object> params = new HashMap<String, Object>();

                params.put("orderBy", "deptno");
                params.put("orderDirection", "desc");

                return params;
            }

			@Override
			public Map<String, Type<?>> typeMap() {
				return null;
			}
        }, ReflectionBeanMapper.createInstance(FindDepartment.class));

        Assert.assertNotNull(departments);
        Assert.assertEquals(4, departments.size());

        int indx = 40;
        for (FindDepartment department : departments) {
            Assert.assertNotNull(department);
            Assert.assertNotNull(department.getDeptno());
            Assert.assertEquals(indx, department.getDeptno().intValue());

            indx -= 10;
        }
    }

    @Test
    public void testBlob() throws Exception {
        /*-
          @SQLComment(name="blobTest", resultClass="sk.vracon.sqlcomments.spring.BlobTest", configClass="sk.vracon.sqlcomments.spring.sqlcomments.BlobConfig")
          select * from datatypes
          where integer_ = 123456
         */
        BlobConfig config = new BlobConfig();

        List<BlobTest> documents = list(config, new BlobTestMapper());

        Assert.assertNotNull(documents);
        Assert.assertEquals(1, documents.size());

        BlobTest doc = documents.get(0);
        Blob data = doc.getBlob();

        Assert.assertNotNull(data);

        InputStream stream = data.getBinaryStream();

        Assert.assertNotNull(stream);

        stream.close();
    }
}
