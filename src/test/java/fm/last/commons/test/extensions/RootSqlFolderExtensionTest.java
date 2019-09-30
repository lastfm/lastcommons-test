package fm.last.commons.test.extensions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.function.Executable;

import fm.last.commons.test.core.DataFolder;

public class RootSqlFolderExtensionTest {

  @RegisterExtension
  DataFolder folder = new RootSqlFolderExtension();

  @Test
  public void initialised() throws IOException {
    File actualFolder = folder.getFolder();
    assertTrue(actualFolder.exists());
  }

  @Test
  public void getNonExistentFolder() {
    assertThrows(FileNotFoundException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        BaseDataFolder newSqlFolder = new RootSqlFolderExtension("sub");
        File notFound = newSqlFolder.getFolder();
      }
    });
  }
}
