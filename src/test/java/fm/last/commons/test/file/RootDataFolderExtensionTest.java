package fm.last.commons.test.file;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class RootDataFolderExtensionTest {

  @RegisterExtension
  DataFolder rootDataFolder = new RootDataFolder();

  @Test
  public void initialised() throws IOException {
    File actualFolder = rootDataFolder.getFolder();
    assertTrue(actualFolder.exists());
    assertTrue(actualFolder.canRead());
    assertTrue(actualFolder.getAbsolutePath().endsWith("src" + File.separator + "test" + File.separator + "data"));
  }
}
