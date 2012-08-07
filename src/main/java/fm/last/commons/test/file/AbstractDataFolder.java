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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

abstract class AbstractDataFolder implements DataFolder {

  File folder;

  AbstractDataFolder() {
  }

  @Override
  public File getFolder() throws IOException {
    if (!folder.exists()) {
      throw new FileNotFoundException(folder.getAbsolutePath());
    }
    if (!folder.canRead()) {
      throw new IOException("Cannot read '" + folder.getAbsolutePath() + "'");
    }
    if (!folder.isDirectory()) {
      throw new IOException("Path is not a directory '" + folder.getAbsolutePath() + "'");
    }
    return folder;
  }

  @Override
  public File getFile(String relativePath) throws IOException {
    return new File(getFolder(), relativePath);
  }

  @Override
  public String getAbsolutePath(String relativePath) throws IOException {
    return internalGetAbsolutePath(relativePath);
  }

  @Override
  public URI getUri(String relativePath) throws IOException {
    try {
      return new URI("file://" + internalGetAbsolutePath(relativePath));
    } catch (URISyntaxException e) {
      throw new IOException(e);
    }
  }

  private String internalGetAbsolutePath(String relativePath) throws IOException {
    return new File(getFolder(), relativePath).getAbsolutePath();
  }

}
