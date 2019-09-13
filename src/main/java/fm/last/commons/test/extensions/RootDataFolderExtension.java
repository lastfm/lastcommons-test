package fm.last.commons.test.extensions;

import java.io.File;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RootDataFolderExtension extends DataFolder implements BeforeEachCallback {

  public RootDataFolderExtension(){
    this(new String[] {});
  }

  public RootDataFolderExtension(String... children) {
    StringBuilder path = new StringBuilder();
    path.append("src");
    path.append(File.separator);
    path.append("test");
    path.append(File.separator);
    path.append("data");
    for (String child : children) {
      path.append(File.separator);
      path.append(child);
    }
    folder = new File(path.toString());
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    //To register extension
  }
}
