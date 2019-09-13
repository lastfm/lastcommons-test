package fm.last.commons.test.extensions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.function.Executable;

public class MethodDataFolderExtensionTest {

  @RegisterExtension
  DataFolder folder = new MethodDataFolderExtension();

  @Test
  void holaMundo() throws IOException {
    File actualFolder = folder.getFolder();
    assertTrue(actualFolder.exists());
  }

  @Test
  void nonExistentFolder() throws IOException {
    assertThrows(FileNotFoundException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        File notFound = folder.getFolder();
      }
    });
  }
}
