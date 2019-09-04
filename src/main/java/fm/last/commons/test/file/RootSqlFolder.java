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

public final class RootSqlFolder extends AbstractDataFolder {

  public RootSqlFolder() {
    this(new String[] {});
  }

  public RootSqlFolder(String... children) {
    StringBuilder path = new StringBuilder();
    path.append("src");
    path.append(File.separator);
    path.append("test");
    path.append(File.separator);
    path.append("sql");
    for (String child : children) {
      path.append(File.separator);
      path.append(child);
    }
    folder = new File(path.toString());
  }

  @Override
  public Statement apply(Statement statement, Description description) {
    return statement;
  }
}
