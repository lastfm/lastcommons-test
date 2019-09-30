package fm.last.commons.test.extensions;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fm.last.commons.test.core.DataFolderUtils;

public abstract class BaseDataFolder implements fm.last.commons.test.core.DataFolder {

  static final String FILE_SEPARATOR_REPLACEMENT = Matcher.quoteReplacement(File.separator);
  static final String PACKAGE_DELIMITER_PATTERN = Pattern.quote(".");

  /**
   * Implement to instantiate the data folder file
   * @return the data folder
   */
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
