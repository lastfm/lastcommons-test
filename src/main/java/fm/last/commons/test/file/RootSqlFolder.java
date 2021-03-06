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

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import fm.last.commons.test.core.DataFolderUtils;

public final class RootSqlFolder extends DataFolder {

  private File folder;

  public RootSqlFolder() {
    this(new String[] {});
  }

  public RootSqlFolder(String... children) {
    folder = DataFolderUtils.buildRootSqlDataFolder(children);
  }

  @Override
  public Statement apply(Statement statement, Description description) {
    return statement;
  }
  
  @Override
  public File getDataFolder() {
    return folder;
  }
}
