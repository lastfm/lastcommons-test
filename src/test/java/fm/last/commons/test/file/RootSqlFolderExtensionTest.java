package fm.last.commons.test.file;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.function.Executable;

public class RootSqlFolderExtensionTest {

  @RegisterExtension
  DataFolder rootSqlFolder = new RootSqlFolder();

  @Test
  public void initialised() throws IOException {
    File actualFolder = rootSqlFolder.getFolder();
    assertTrue(actualFolder.exists());
  }

  @Test
  public void getNonExistentFolder() {
    assertThrows(FileNotFoundException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        DataFolder newSqlFolder = new RootSqlFolder("sub");
        File notFound = newSqlFolder.getFolder();
      }
    });
  }
}
