/*
 * Copyright 2012-2019 Last.fm
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * <p>
 * Why we have our own version of this class: <a
 * href="http://stackoverflow.com/questions/7738881/junit-rule-lifecycle-interaction-with-before"
 * >http://stackoverflow.com/questions/7738881/junit-rule-lifecycle-interaction-with-before</a>
 * </p><p>
 * The TemporaryFolder Rule allows creation of files and folders that are guaranteed to be deleted when the test method
 * finishes (whether it passes or fails):
 * </p>
 *
 * <pre>
 * public static class HasTempFolder {
 *   &#064;Rule
 *   public TemporaryFolder folder = new TemporaryFolder();
 * 
 *   &#064;Test
 *   public void testUsingTempFolder() throws IOException {
 *     File createdFile = folder.newFile(&quot;myfile.txt&quot;);
 *     File createdFolder = folder.newFolder(&quot;subfolder&quot;);
 *     // ...
 *   }
 * }
 * </pre>
 */
public class TemporaryFolder implements TestRule {

  private File folder;

  @Override
  public Statement apply(final Statement base, Description description) {
    Throwable caught;
    try {
      create();
      caught = null;
    } catch (Throwable e) {
      caught = e;
    }
    final Throwable toRethrow = caught;
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        if (toRethrow != null) {
          throw toRethrow;
        }
        try {
          base.evaluate();
        } finally {
          delete();
        }
      }
    };
  }

  // testing purposes only
  /**
   * For testing purposes only. Do not use.
   * @throws IOException if a file operation fails.
   */
  public void create() throws IOException {
    folder = File.createTempFile("junit", "");
    folder.delete();
    folder.mkdir();
  }

  /**
   * Returns a new fresh file with the given name under the temporary folder.
   * @param first The first path element.
   * @param others Any other path elements.
   * @return The new file.
   * @throws IOException if a file operation fails.
   */
  public File newFile(String first, String... others) throws IOException {
    List<String> elements = mergeElements(first, others);
    File file = applyElements(elements);
    if (!folder.equals(file.getParentFile())) {
      file.getParentFile().mkdirs();
    }
    file.createNewFile();
    return file;
  }

  /**
   * Returns a new fresh folder with the given name under the temporary folder.
   * @param first The first path element.
   * @param others Any other path elements.
   * @return The new folder.
   */
  public File newFolder(String first, String... others) {
    List<String> elements = mergeElements(first, others);
    File file = applyElements(elements);
    file.mkdirs();
    return file;
  }

  /**
   * @return the location of this temporary folder.
   */
  public File getRoot() {
    return folder;
  }

  /**
   * Delete all files and folders under the temporary folder. Usually not called directly, since it is automatically
   * applied by the {@link Rule}
   */
  public void delete() {
    recursiveDelete(folder);
  }

  private void recursiveDelete(File file) {
    File[] files = file.listFiles();
    if (files != null) {
      for (File each : files) {
        recursiveDelete(each);
      }
    }
    file.delete();
  }

  private File applyElements(List<String> elements) {
    File file = folder;
    for (String element : elements) {
      file = new File(file, element);
    }
    return file;
  }

  private List<String> mergeElements(String first, String... others) {
    if (first == null || first.trim().isEmpty()) {
      throw new IllegalArgumentException("You must supply at least one path element.");
    }
    List<String> elements = new ArrayList<String>();
    elements.add(first);
    if (others != null && others.length > 0) {
      for (String element : others) {
        if (element == null || element.trim().isEmpty()) {
          throw new IllegalArgumentException("Path element may not be null or empty.");
        }
        elements.add(element);
      }
    }
    return elements;
  }
}
