package fm.last.commons.test.extensions;

import java.io.File;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RootSqlFolderExtension extends DataFolder implements BeforeEachCallback {

  public RootSqlFolderExtension(){
    this(new String[] {});
  }

  public RootSqlFolderExtension(String... children) {
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
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    //Register extension
  }
}
