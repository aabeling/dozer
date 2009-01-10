/*
 * Copyright 2005-2010 the original author or authors.
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
package net.sf.dozer.functional_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import net.sf.dozer.Mapper;
import net.sf.dozer.vo.collections.User;
import net.sf.dozer.vo.collections.UserGroup;
import net.sf.dozer.vo.collections.UserGroupImpl;
import net.sf.dozer.vo.collections.UserGroupPrime;
import net.sf.dozer.vo.collections.UserImpl;

import org.junit.Test;

public class CumulativeCollectionMappingTest extends AbstractFunctionalTest {

  @Test
  public void testMappingInterface() throws Exception {

    // prepare 2 Users in 1 UserGroup
    User user1 = newInstance(UserImpl.class);
    user1.setFirstName("first name 1");
    user1.setLastName("last name 1");

    User user2 = newInstance(UserImpl.class);
    user2.setFirstName("first name 2");
    user2.setLastName("last name 2");

    UserGroup userGroup = newInstance(UserGroupImpl.class);
    userGroup.setName("usergroup name");
    userGroup.addUser(user1);
    userGroup.addUser(user2);

    // assert 2 users in collection
    assertEquals("Two users in source usergroup expected.", 2, userGroup.getUsers().size());

    // get mapper
    Mapper mapper = getMapper(new String[] { "mapping-interface.xml" });

    // do mapping
    UserGroupPrime userGroupPrime = mapper.map(userGroup, UserGroupPrime.class);

    // check mapped group
    assertNotNull(userGroupPrime);
    assertEquals(userGroup.getName(), userGroupPrime.getName());

    // check mapped collection
    assertEquals("Two users in mapped usergroup expected.", 2, userGroupPrime.getUsers().size());
  }

  @Test
  public void testMappingConcrete() throws Exception {

    // prepare 2 Users in 1 UserGroup
    UserImpl user1 = newInstance(UserImpl.class);
    user1.setFirstName("first name 1");
    user1.setLastName("last name 1");

    UserImpl user2 = newInstance(UserImpl.class);
    user2.setFirstName("first name 2");
    user2.setLastName("last name 2");

    UserGroupImpl userGroup = newInstance(UserGroupImpl.class);
    userGroup.setName("usergroup name");
    userGroup.addUser(user1);
    userGroup.addUser(user2);

    // assert 2 users in collection
    assertEquals("Two users in source usergroup expected.", 2, userGroup.getUsers().size());

    // get mapper
    Mapper mapper = getMapper(new String[] { "mapping-concrete.xml" });

    // do mapping
    UserGroupPrime userGroupPrime = mapper.map(userGroup, UserGroupPrime.class);

    // check mapped group
    assertNotNull(userGroupPrime);
    assertEquals(userGroup.getName(), userGroupPrime.getName());

    // check mapped collection
    assertEquals("Two users in mapped usergroup expected.", 2, userGroupPrime.getUsers().size());
  }

  @Override
  protected DataObjectInstantiator getDataObjectInstantiator() {
    return NoProxyDataObjectInstantiator.INSTANCE;
  }

}
