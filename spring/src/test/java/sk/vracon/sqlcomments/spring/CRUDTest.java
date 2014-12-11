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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sk.vracon.sqlcomments.core.impl.ReflectionBeanMapper;
import sk.vracon.sqlcomments.spring.domain.Users;
import sk.vracon.sqlcomments.spring.domain.sqlcomments.UsersMapper;
import sk.vracon.sqlcomments.spring.domain.sqlcomments.UsersPKConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
@Repository
public class CRUDTest extends GenericTestRepository {

    @Test
    public void testFindByPK() {
        UsersPKConfig config = new UsersPKConfig();
        config.setId(5);

        Users user = findByPK(config, new UsersMapper());

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(new Integer(5), user.getId());
    }

    @Test
    public void testInsertUser() {
        Users user = new Users();

        user.setFirstName("NewUser1");
        user.setLastName("NewUserLast1");

        Users saved = insert(user);

        Assert.assertEquals(user, saved);
        Assert.assertNotNull(saved.getId());

        UsersPKConfig config = new UsersPKConfig();
        config.setId(saved.getId());

        Users found = findByPK(config, new UsersMapper());

        Assert.assertNotNull(found);
        Assert.assertEquals(saved.getId(), found.getId());
    }

    @Test
    public void testUpdateUser() {
        UsersPKConfig config = new UsersPKConfig();
        config.setId(5);

        Users user = findByPK(config, new UsersMapper());

        Assert.assertNotNull(user);

        user.setFirstName("ChangedFirstName");
        user.setLastName("ChangedLastName");

        update(user);

        Users updated = singleResult(config, new ReflectionBeanMapper<Users>(Users.class));

        Assert.assertNotNull(user);
        Assert.assertEquals("ChangedFirstName", updated.getFirstName());
        Assert.assertEquals("ChangedLastName", updated.getLastName());
    }

    @Test
    public void testDeleteWithInstance() {
        UsersPKConfig config = new UsersPKConfig();
        config.setId(5);

        Users user = findByPK(config, new UsersMapper());

        Assert.assertNotNull(user);

        delete(user);

        Users deleted = findByPK(config, new UsersMapper());

        Assert.assertNull(deleted);
    }

    @Test
    public void testDeleteWithConfig() {
        UsersPKConfig config = new UsersPKConfig();
        config.setId(6);

        delete(config);

        Users deleted = findByPK(config, new UsersMapper());

        Assert.assertNull(deleted);
    }

}
