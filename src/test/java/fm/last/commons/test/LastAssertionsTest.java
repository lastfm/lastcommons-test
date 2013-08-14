/*
 * Copyright 2012 Last.fm
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fm.last.commons.test;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fm.last.commons.lang.units.JedecByteUnit;
import fm.last.commons.test.file.ClassDataFolder;
import fm.last.commons.test.file.DataFolder;

/**
 * Unit test case for our test assertions.
 */
@RunWith(MockitoJUnitRunner.class)
public class LastAssertionsTest {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Rule
  public DataFolder dataFolder = new ClassDataFolder();

  @Mock
  private DataSource dataSource;

  @Mock
  private Connection connection;

  @Test(expected = java.lang.AssertionError.class)
  public void assertFilesEqualWithDifferentFilesButSameLength() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    File file2 = new File(dataFolder.getFolder(), "file2.txt");
    LastAssertions.assertFileEquals(file1, file2);
  }

  @Test(expected = java.lang.AssertionError.class)
  public void assertFilesEqualWithDifferentFilesWithDifferentLength() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    File file2 = new File(dataFolder.getFolder(), "file3.txt");
    LastAssertions.assertFileEquals(file1, file2);
  }

  @Test(expected = java.lang.AssertionError.class)
  public void assertFilesEqualWithDifferentFilesOneLargerThanMessageThreshold() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    File largeFile = temporaryFolder.newFile("largeFile.txt");
    FileUtils.writeStringToFile(largeFile, RandomStringUtils.randomAscii((int) JedecByteUnit.MEGABYTES.toBytes(1.1)));
    LastAssertions.assertFileEquals(file1, largeFile);
  }

  @Test(expected = java.lang.AssertionError.class)
  public void assertFilesEqualWithLeftFileEmpty() throws IOException {
    File empty = temporaryFolder.newFile("empty.txt");
    File file2 = new File(dataFolder.getFolder(), "file1.txt");
    LastAssertions.assertFileEquals(empty, file2);
  }

  @Test(expected = java.lang.AssertionError.class)
  public void assertFilesEqualWithRightFileEmpty() throws IOException {
    File empty = temporaryFolder.newFile("empty.txt");
    File file2 = new File(dataFolder.getFolder(), "file1.txt");
    LastAssertions.assertFileEquals(file2, empty);
  }

  @Test
  public void assertFilesEqualWithSameFile() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    LastAssertions.assertFileEquals(file1, file1);
  }

  @Test
  public void assertFilesEqualWithSameFileInDifferentFolders() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    File file2 = temporaryFolder.newFile("copy.txt");
    FileUtils.copyFile(file1, file2);
    LastAssertions.assertFileEquals(file1, file2);
  }

  @Test
  public void assertEmptyFilesEqual() throws IOException {
    File file1 = temporaryFolder.newFile("empty1.txt");
    File file2 = temporaryFolder.newFile("empty2.txt");
    LastAssertions.assertFileEquals(file1, file2);
  }

  @Test
  public void assertFilesNotEqual() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    File file2 = new File(dataFolder.getFolder(), "file2.txt");
    LastAssertions.assertFilesNotEqual(file1, file2);
  }

  @Test(expected = java.lang.AssertionError.class)
  public void assertFilesNotEqualWithSameFile() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    File file2 = new File(dataFolder.getFolder(), "file1.txt");
    LastAssertions.assertFilesNotEqual(file1, file2);
  }

  @Test
  public void assertValues() {
    String value = "value";
    LastAssertions.assertValues(value, new String[] { value, value, value });
  }

  @Test(expected = java.lang.AssertionError.class)
  public void assertValuesNotEqual() {
    String value = "value";
    LastAssertions.assertValues(value, new String[] { value, "value2", value });
  }

  @Test
  public void assertTestDatabase() throws SQLException {
    when(dataSource.getConnection()).thenReturn(connection);
    when(connection.getCatalog()).thenReturn("test" + LastAssertions.TEST_DB_SUFFIX);
    LastAssertions.assertTestDatabase(dataSource);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertWrongTestDatabase() throws SQLException {
    when(dataSource.getConnection()).thenReturn(connection);
    when(connection.getCatalog()).thenReturn("test");
    LastAssertions.assertTestDatabase(dataSource);
  }

  @Test(expected = java.lang.AssertionError.class)
  public void assertFilesEquivalentWithDifferentLines() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    File file2 = new File(dataFolder.getFolder(), "file2.txt");
    LastAssertions.assertFilesEquivalent(file1, file2);
  }

  @Test
  public void assertFilesEquivalentWithSameLines() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file3.txt");
    File file2 = new File(dataFolder.getFolder(), "file3-copy.txt");
    LastAssertions.assertFilesEquivalent(file1, file2);
  }

  @Test
  public void assertFilesEquivalentWithSameLinesInDifferentOrder() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file3.txt");
    File file2 = new File(dataFolder.getFolder(), "file3-equivalent.txt");
    LastAssertions.assertFilesEquivalent(file1, file2);
  }

  @Test
  public void assertFilesEquivalentWithSameFile() throws IOException {
    File file1 = new File(dataFolder.getFolder(), "file1.txt");
    LastAssertions.assertFilesEquivalent(file1, file1);
  }

  @Test(expected = AssertionError.class)
  public void assertFilesEquivalentWithLeftFileEmpty() throws IOException {
    File empty = temporaryFolder.newFile("empty.txt");
    File file2 = new File(dataFolder.getFolder(), "file1.txt");
    LastAssertions.assertFilesEquivalent(empty, file2);
  }

  @Test(expected = AssertionError.class)
  public void assertFilesEquivalentWithRightFileEmpty() throws IOException {
    File empty = temporaryFolder.newFile("empty.txt");
    File file2 = new File(dataFolder.getFolder(), "file1.txt");
    LastAssertions.assertFilesEquivalent(file2, empty);
  }

  @Test
  public void assertEmptyFilesEquivalent() throws IOException {
    File file1 = temporaryFolder.newFile("empty1.txt");
    File file2 = temporaryFolder.newFile("empty2.txt");
    LastAssertions.assertFilesEquivalent(file1, file2);
  }
}
