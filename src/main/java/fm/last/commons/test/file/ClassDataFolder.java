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
import java.lang.annotation.Annotation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public final class ClassDataFolder extends AbstractDataFolder {

  private final File parent;

  public ClassDataFolder() {
    parent = new File("src" + File.separator + "test" + File.separator + "data");
  }

  @SuppressWarnings("unchecked")
  @Override
  public final Statement apply(final Statement base, FrameworkMethod method, Object target) {
    if (notAnnotatedWithAny(method, Test.class, Before.class, After.class)) {
      return base;
    }
    Class<?> targetClass = target.getClass();
    folder = new File(parent, targetClass.getName().replaceAll("\\.", File.separator));
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        base.evaluate();
      }
    };
  }

  private boolean notAnnotatedWithAny(FrameworkMethod method, Class<? extends Annotation>... annotationClasses) {
    for (Class<? extends Annotation> annotationClass : annotationClasses) {
      Annotation annotationInstance = method.getAnnotation(annotationClass);
      if (annotationInstance != null) {
        return false;
      }
    }
    return true;
  }

}
