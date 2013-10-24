package fm.last.commons.test.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TemporaryFolderTest {

  private final TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Before
  public void setUp() throws IOException {
    temporaryFolder.create();
  }

  @After
  public void tearDown() throws IOException {
    temporaryFolder.delete();
  }

  @Test
  public void createSingleElementFile() throws IOException {
    File newFile = temporaryFolder.newFile("X");
    assertThat(newFile.exists(), is(true));
    assertThat(newFile.isFile(), is(true));
    assertThat(newFile, is(new File(temporaryFolder.getRoot(), "X")));
  }

  @Test
  public void createMultiElementFile() throws IOException {
    File newFile = temporaryFolder.newFile("A", "B", "C");
    assertThat(newFile.exists(), is(true));
    assertThat(newFile.isFile(), is(true));

    File parent = newFile.getParentFile();
    assertThat(newFile, is(new File(parent, "C")));

    newFile = newFile.getParentFile();
    parent = parent.getParentFile();
    assertThat(newFile, is(new File(parent, "B")));

    newFile = newFile.getParentFile();
    assertThat(newFile, is(new File(temporaryFolder.getRoot(), "A")));
  }

  @Test
  public void createSingleElementFolder() throws IOException {
    File newFolder = temporaryFolder.newFolder("X");
    assertThat(newFolder.exists(), is(true));
    assertThat(newFolder.isDirectory(), is(true));
    assertThat(newFolder, is(new File(temporaryFolder.getRoot(), "X")));
  }

  @Test
  public void createMultiElementFolder() throws IOException {
    File newFolder = temporaryFolder.newFolder("A", "B", "C");
    assertThat(newFolder.exists(), is(true));
    assertThat(newFolder.isDirectory(), is(true));

    File parent = newFolder.getParentFile();
    assertThat(newFolder, is(new File(parent, "C")));

    newFolder = newFolder.getParentFile();
    parent = parent.getParentFile();
    assertThat(newFolder, is(new File(parent, "B")));

    newFolder = newFolder.getParentFile();
    assertThat(newFolder, is(new File(temporaryFolder.getRoot(), "A")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullSingleElementFile() throws IOException {
    temporaryFolder.newFile(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullSingleElementFolder() {
    temporaryFolder.newFolder(null);
  }

}
