package fm.last.commons.test.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DataFolderCore {

  public File getFolder(File folder) throws IOException {
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

  public URI getUri(String relativePath, File folder) throws IOException {
    try {
      return new URI("file://" + internalGetAbsolutePath(relativePath, folder));
    } catch (URISyntaxException e) {
      throw new IOException(e);
    }
  }

  public String internalGetAbsolutePath(String relativePath, File folder) throws IOException {
    return new File(getFolder(folder), relativePath).getAbsolutePath();
  }
}
