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
package net.sf.dozer.vo.deep;

import net.sf.dozer.vo.Apple;
import net.sf.dozer.vo.BaseTestObject;

/**
 * @author garsombke.franz
 * @author sullins.ben
 * @author tierney.matt
 */
public class HomeDescription extends BaseTestObject {
  private Description description;
  private long[] prim;
  private double price;
  private Apple van;

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Description getDescription() {
    return description;
  }

  public void setDescription(Description description) {
    this.description = description;
  }

  public long[] getPrim() {
    return prim;
  }

  public void setPrim(long[] prim) {
    this.prim = prim;
  }

  public Apple getVan() {
    return van;
  }

  public void setVan(Apple van) {
    this.van = van;
  }
}
