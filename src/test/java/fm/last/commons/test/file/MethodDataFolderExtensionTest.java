package fm.last.commons.test.file;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.function.Executable;

public class MethodDataFolderExtensionTest {

  @RegisterExtension
  DataFolder methodFolder = new MethodDataFolder();

  @Test
  public void halloWelt() throws IOException {
    File actualFolder = methodFolder.getFolder();
    assertTrue(actualFolder.exists());
    assertTrue(actualFolder.canRead());
    assertTrue(actualFolder.getAbsolutePath().endsWith(
        getClass().getName().replaceAll(Pattern.quote("."), Matcher.quoteReplacement(File.separator)) + File.separator
            + "halloWelt"));
  }

  @Test
  public void nonExistentFolder() {
    assertThrows(FileNotFoundException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        File notFound = methodFolder.getFolder();
      }
    });
  }
}
