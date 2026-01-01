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

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sk.vracon.sqlcomments.core.resultmappers.ReflectionBeanMapper;
import sk.vracon.sqlcomments.spring.domain.Employee;
import sk.vracon.sqlcomments.spring.domain.sqlcomments.EmployeeMapper;
import sk.vracon.sqlcomments.spring.domain.sqlcomments.EmployeePKConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
@Repository
public class CRUDTest extends GenericTestRepository {

    @Test
    public void testFindByPK() {
        EmployeePKConfig config = new EmployeePKConfig();
        config.setEmpno(7900);

        Employee user = findByPK(config, new EmployeeMapper());

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getEmpno());
        Assert.assertEquals(Integer.valueOf(7900), user.getEmpno());
    }

    @Test
    public void testInsertUser() {
    	Employee user = new Employee();

        user.setEname("NewUser1");
        user.setEmpno(1);
        user.setDeptno(10);
        user.setHiredate(new Date());
        user.setJob("Test");
        user.setSal(1000);

        Employee saved = insert(user);

        Assert.assertEquals(user, saved);
        Assert.assertNotNull(saved.getEmpno());

        EmployeePKConfig config = new EmployeePKConfig();
        config.setEmpno(saved.getEmpno());

        Employee found = findByPK(config, new EmployeeMapper());

        Assert.assertNotNull(found);
        Assert.assertEquals(saved.getEmpno(), found.getEmpno());
    }

    @Test
    public void testUpdateUser() {
    	EmployeePKConfig config = new EmployeePKConfig();
        config.setEmpno(7900);

        Employee user = findByPK(config, new EmployeeMapper());

        Assert.assertNotNull(user);

        user.setEname("ChangedName");
        user.setJob("ChangedJob");

        update(user);

        Employee updated = singleResult(config, new ReflectionBeanMapper<Employee>(Employee.class));

        Assert.assertNotNull(user);
        Assert.assertEquals("ChangedName", updated.getEname());
        Assert.assertEquals("ChangedJob", updated.getJob());
    }

    @Test
    public void testDeleteWithInstance() {
        EmployeePKConfig config = new EmployeePKConfig();
        config.setEmpno(7902);

        Employee user = findByPK(config, new EmployeeMapper());

        Assert.assertNotNull(user);

        delete(user);

        Employee deleted = findByPK(config, new EmployeeMapper());

        Assert.assertNull(deleted);
    }

    @Test
    public void testDeleteWithConfig() {
        EmployeePKConfig config = new EmployeePKConfig();
        config.setEmpno(6);

        delete(config);

        Employee deleted = findByPK(config, new EmployeeMapper());

        Assert.assertNull(deleted);
    }

}
