package fm.last.commons.test.extensions;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MethodDataFolderExtension extends DataFolder implements BeforeEachCallback {

  private final File parent;

  public MethodDataFolderExtension(){
    parent = new File("src" + File.separator + "test" + File.separator + "data");
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    Test testAnnotation = extensionContext.getRequiredTestMethod().getAnnotation(Test.class);
    if(testAnnotation != null) {
      final String methodName = extensionContext.getRequiredTestMethod().getName();
      final Class<?> targetClass = extensionContext.getRequiredTestClass();
          folder = new File(parent, targetClass.getName().replaceAll(PACKAGE_DELIMITER_PATTERN,
              FILE_SEPARATOR_REPLACEMENT)
              + File.separator + methodName);
    }
  }
}
