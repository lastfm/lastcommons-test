package fm.last.commons.test.extensions;

import java.io.File;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ClassDataFolderExtension extends BaseDataFolder implements BeforeEachCallback {

  private final File parent = new File("src" + File.separator + "test" + File.separator + "data");
  private File folder;

  @Override
  public void beforeEach(ExtensionContext extensionContext) {
    Class<?> targetClass = extensionContext.getRequiredTestClass();
    folder = new File(parent, targetClass.getName().replaceAll(PACKAGE_DELIMITER_PATTERN, FILE_SEPARATOR_REPLACEMENT));
  }

  @Override
  public File getDataFolder() {
    return folder;
  }
}
