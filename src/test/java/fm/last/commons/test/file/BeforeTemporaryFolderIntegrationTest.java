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

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BeforeTemporaryFolderIntegrationTest {

  private static final String DATA_FILE = "data.txt";

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private File dataFile;

  @Before
  public void init() throws IOException {
    dataFile = folder.newFile(DATA_FILE);
  }

  @Test
  public void integration() throws IOException {
    // create a file with no root path, will get created relative to project root
    File projectFile = new File("projectFile.dat");

    String projectFileBasePath = projectFile.getAbsolutePath().split(File.separator)[1];
    String dataFileBasePath = dataFile.getAbsolutePath().split(File.separator)[1];

    // all we can really check is that the root of our test files isn't the same as the root to the project
    assertFalse(projectFileBasePath.equals(dataFileBasePath));
  }

}
