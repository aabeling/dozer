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
package net.sf.dozer.propertydescriptor;

import net.sf.dozer.fieldmap.FieldMap;

/**
 * Internal property descriptor interface. Only intended for internal use.  Dozer property descriptors are used
 * to read and write the actual field mapping values on the target objects.
 * 
 * @author garsombke.franz
 */
public interface DozerPropertyDescriptor {

  public Class<?> getPropertyType();

  public Object getPropertyValue(Object bean);

  public void setPropertyValue(Object bean, Object value, FieldMap fieldMap);

}
