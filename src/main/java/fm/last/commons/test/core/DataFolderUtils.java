package fm.last.commons.test.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public final class DataFolderUtils {

  private DataFolderUtils(){}

  public static File getFolder(File folder) throws IOException {
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

  public static URI getUri(String relativePath, File folder) throws IOException {
    try {
      return new URI("file://" + getAbsolutePath(relativePath, folder));
    } catch (URISyntaxException e) {
      throw new IOException(e);
    }
  }

  public static String getAbsolutePath(String relativePath, File folder) throws IOException {
    return new File(getFolder(folder), relativePath).getAbsolutePath();
  }

  public static File buildDataFolder(String first, String... children){
    StringBuilder path = new StringBuilder();
    path.append("src");
    path.append(File.separator);
    path.append("test");
    path.append(File.separator);
    path.append(first);
    for (String child : children) {
      path.append(File.separator);
      path.append(child);
    }
    return new File(path.toString());
  }


  public static File buildRootDataFolder(String... children){
    return buildDataFolder("data", children);
  }

  public static File buildRootSqlDataFolder(String... children) {
    return buildDataFolder("sql", children);
  }
}
