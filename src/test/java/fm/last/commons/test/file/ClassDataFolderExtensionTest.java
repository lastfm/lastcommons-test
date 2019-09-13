package fm.last.commons.test.file;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class ClassDataFolderExtensionTest {

  @RegisterExtension
  DataFolder folder = new ClassDataFolder();

  @Test
  public void initialised() throws IOException {
    File testFolder = folder.getFolder();
    assertTrue(testFolder.exists());
    assertTrue(testFolder.canRead());
    assertTrue(testFolder.getAbsolutePath().endsWith(
        getClass().getName().replaceAll(Pattern.quote("."), Matcher.quoteReplacement(File.separator))));
  }
}
