/*
 * Copyright 2012 Last.fm
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fm.last.commons.test.file;

import java.io.File;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public final class MethodDataFolder extends AbstractDataFolder {

  private final File parent;

  public MethodDataFolder() {
    parent = new File("src" + File.separator + "test" + File.separator + "data");
  }

  @Override
  public Statement apply(final Statement base, Description description) {
    Test testAnnotation = description.getAnnotation(Test.class);
    if(testAnnotation == null){
      return base;
    }
    final String methodName = description.getMethodName();
    final Class<?> targetClass = description.getTestClass();
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        folder = new File(parent, targetClass.getName().replaceAll(PACKAGE_DELIMITER_PATTERN,
            FILE_SEPARATOR_REPLACEMENT)
            + File.separator + methodName);
        base.evaluate();
      }
    };

  }
}
