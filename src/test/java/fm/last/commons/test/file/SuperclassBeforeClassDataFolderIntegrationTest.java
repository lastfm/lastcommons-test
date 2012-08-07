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
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;

public abstract class SuperclassBeforeClassDataFolderIntegrationTest {

  static final String DATA_FILE = "data.txt";

  @Rule
  public DataFolder folder = new ClassDataFolder();

  File dataFile;

  @Before
  public void init() throws IOException {
    dataFile = folder.getFile(DATA_FILE);
  }

}
