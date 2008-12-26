/*
 * Copyright 2005-2007 the original author or authors.
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

import net.sf.dozer.DataObjectInstantiator;
import net.sf.dozer.NoProxyDataObjectInstantiator;
import net.sf.dozer.vo.enumtest.MyBean;
import net.sf.dozer.vo.enumtest.MyBeanPrime;
import net.sf.dozer.vo.enumtest.SrcType;
import net.sf.dozer.vo.enumtest.SrcTypeWithOverride;

/**
 * Functional test for enum mapping as described 
 * <a href=http://dozer.sourceforge.net/documentation/enum.html>here</a>.
 * 
 * In this functional test, Enum is categorized into two types: Based Enum and Overrided Enum.
 * Based Enum refers to those enum without any overrided methods, including constructors.  A 
 * typical Based Enum would look as below.
 * <code><pre>
 * public enum SrcType {
 *   FOO , BAR;
 * }
 * </pre></code>
 * On the contrary, Overrided Enum refers to those enum with overrided methods, including 
 * constructors. A typical Overrided Enum would look as below.
 * <code><pre>
 * public enum SrcTypeWithOverride {
 *   FOO { public String display() { return "Src.FOO"; } },
 *   BAR { public String display() { return "Src.BAR"; } };
 *   public abstract String display();
 * }
 * </pre></code>
 * 
 * @author cchou.hung
 *
 */
public class EnumMappingTest extends AbstractMapperTest {
  
  protected void setUp() throws Exception {
    super.setUp();
  }
  
  /**
   * Test on a mapping from Overrided Enum to Based Enum. 
   */
  public void testOverridedEnumMapsToBasedEnum() {
    mapper = getMapper(new String[] { "enumMappingOverriedEnumToBasedEnum.xml" });
    MyBean src = new MyBean();
    src.setSrcTypeWithOverride(SrcTypeWithOverride.FOO);
    MyBeanPrime dest = (MyBeanPrime) mapper.map(src, MyBeanPrime.class);
    assertEquals(src.getSrcTypeWithOverride().toString(),
        dest.getDestType().toString() );
  }
  
  /**
   * Test on a mapping from Based Enum to Overrided Enum.
   */
  public void testBasedEnumMapsToOverridedEnum() {
    mapper = getMapper(new String[] { "enumMappingOverriedEnumToBasedEnum.xml" });
    MyBean src = new MyBean();
    src.setSrcType(SrcType.FOO);
    MyBeanPrime dest = (MyBeanPrime) mapper.map(src, MyBeanPrime.class);
    assertEquals(src.getSrcType().toString(),
        dest.getDestTypeWithOverride().toString() );
  }
  
  /**
   * Test on a mapping from Based Enum to Based Enum.
   */
  public void testBasedEnumMapsToBasedEnum() {
    mapper = getMapper(new String[] { "enumMapping.xml" });
    MyBean src = new MyBean();
    src.setSrcType(SrcType.FOO);
    MyBeanPrime dest = (MyBeanPrime) mapper.map(src, MyBeanPrime.class);
    assertEquals(src.getSrcType().toString(),
        dest.getDestType().toString());
  }
  
  /**
   * Test on a mapping from Overrided Enum to Overrided Enum.
   */
  public void testOverridedEnumMapsToOverridedEnum() {
    mapper = getMapper(new String[] { "enumMapping.xml" });
    MyBean src = new MyBean();
    src.setSrcTypeWithOverride(SrcTypeWithOverride.FOO);
    MyBeanPrime dest = (MyBeanPrime) mapper.map(src, MyBeanPrime.class);
    assertEquals(src.getSrcTypeWithOverride().toString(),
        dest.getDestTypeWithOverride().toString());
  }
  
  /**
   * Test on a mapping from Enum to itself. This test is used to reproduce bug#1806780.
   */
  public void testEnumMapsToItself() {
    MyBean src = new MyBean();
    src.setSrcType(SrcType.FOO);
    MyBean dest = (MyBean) mapper.map(src, MyBean.class);
    assertEquals(src.getSrcType(), dest.getSrcType());
    assertEquals(src.getSrcTypeWithOverride(), dest.getSrcTypeWithOverride());
  }
  
  /**
   * Test on if mapping to nonexist enum value would throw exception.
   */
  public void testEnumMapsToNonexistEnumValue(){
    mapper = getMapper(new String[] { "enumMapping.xml" });
    MyBean src = new MyBean();
    src.setSrcType(SrcType.BAR);
    try {
      MyBeanPrime dest = (MyBeanPrime) mapper.map(src, MyBean.class);
      fail("Expect to throw exception but didn't.");
    } catch (Exception e){
      //expect exception
    }
  }
  
  protected DataObjectInstantiator getDataObjectInstantiator() {
    return NoProxyDataObjectInstantiator.INSTANCE;
  }

}