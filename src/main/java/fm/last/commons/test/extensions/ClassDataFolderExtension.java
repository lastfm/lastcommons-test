package fm.last.commons.test.extensions;

import java.io.File;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ClassDataFolderExtension extends DataFolder implements BeforeEachCallback {

  private final File parent;

  public ClassDataFolderExtension(){
    parent = new File("src" + File.separator + "test" + File.separator + "data");
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    Class<?> targetClass = extensionContext.getRequiredTestClass();
    folder = new File(parent, targetClass.getName().replaceAll(PACKAGE_DELIMITER_PATTERN, FILE_SEPARATOR_REPLACEMENT));
  }
}
