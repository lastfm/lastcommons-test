package fm.last.commons.test.extensions;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import fm.last.commons.test.core.DataFolder;

public class SuffixedRootDataFolderExtensionTest {

  @RegisterExtension
  DataFolder folder = new RootDataFolderExtension("suffix");

  @Test
  public void integration() throws IOException {
    File actualFolder = folder.getFolder();
    assertTrue(actualFolder.exists());
    assertTrue(actualFolder.canRead());
    assertTrue(actualFolder.getAbsolutePath().endsWith(
        "src" + File.separator + "test" + File.separator + "data" + File.separator + "suffix"));
  }

}
