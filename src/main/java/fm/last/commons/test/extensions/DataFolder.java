package fm.last.commons.test.extensions;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fm.last.commons.test.file.BaseDataFolder;
import fm.last.commons.test.file.DataFolderCore;

public abstract class DataFolder implements BaseDataFolder {

  static final String FILE_SEPARATOR_REPLACEMENT = Matcher.quoteReplacement(File.separator);
  static final String PACKAGE_DELIMITER_PATTERN = Pattern.quote(".");

  private DataFolderCore core = new DataFolderCore();
  File folder;

  @Override
  public File getFolder() throws IOException {
    return core.getFolder(folder);
  }

  @Override
  public File getFile(String relativePath) throws IOException {
    return new File(getFolder(), relativePath);
  }

  @Override
  public String getAbsolutePath(String relativePath) throws IOException {
    return core.internalGetAbsolutePath(relativePath, folder);
  }

  @Override
  public URI getUri(String relativePath) throws IOException {
    return core.getUri(relativePath, folder);
  }
}
