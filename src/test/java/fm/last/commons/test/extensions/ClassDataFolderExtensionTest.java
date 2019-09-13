package fm.last.commons.test.extensions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class ClassDataFolderExtensionTest {

  @RegisterExtension
  DataFolder folder = new ClassDataFolderExtension();

  @Test
  public void initialised() throws IOException {
    File actualFolder = folder.getFolder();
    assertTrue(actualFolder.exists());
    assertTrue(actualFolder.canRead());
  }
}
