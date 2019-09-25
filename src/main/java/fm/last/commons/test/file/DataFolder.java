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
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.rules.TestRule;

import fm.last.commons.test.core.DataFolderUtils;
public interface DataFolder extends TestRule {

/**
 * NOTE: This class shares the same name as the interface due to backwards compatibility issues
 * that were introduced when moving this project to JUnit5.
 */
public abstract class DataFolder implements TestRule, fm.last.commons.test.core.DataFolder {

  static final String FILE_SEPARATOR_REPLACEMENT = Matcher.quoteReplacement(File.separator);
  static final String PACKAGE_DELIMITER_PATTERN = Pattern.quote(".");

  public abstract File getDataFolder();

  @Override
  public File getFolder() throws IOException {
    return DataFolderUtils.getFolder(getDataFolder());
  }

  @Override
  public File getFile(String relativePath) throws IOException {
    return new File(getFolder(), relativePath);
  }

  @Override
  public String getAbsolutePath(String relativePath) throws IOException {
    return DataFolderUtils.getAbsolutePath(relativePath, getDataFolder());
  }

  @Override
  public URI getUri(String relativePath) throws IOException {
    return DataFolderUtils.getUri(relativePath, getDataFolder());
  }

}
