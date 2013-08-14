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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.ComparisonFailure;

import fm.last.commons.lang.units.JedecByteUnit;

/**
 * Additional test assertions that provide Last.fm specific functionality or additional functionality not found in
 * JUnit.
 */
public final class LastAssertions {

  /**
   * Last.fm standard is to name all unit tests with the name of the original database and then this suffix.
   */
  public static final String TEST_DB_SUFFIX = "_unittest";

  private LastAssertions() {
  }

  /**
   * Asserts that the database that the passed DataSource has been configured to use is a unit test database.
   * 
   * @param dataSource Configured DataSource.
   * @throws SQLException If an error occurs connecting to the database to retrieve the database name.
   */
  public static void assertTestDatabase(DataSource dataSource) throws SQLException {
    Connection connection = null;
    String dbName = null;
    try {
      connection = dataSource.getConnection();
      dbName = connection.getCatalog();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    if (!dbName.endsWith(TEST_DB_SUFFIX)) {
      throw new IllegalArgumentException("Database provided was " + dbName
          + " but tests must run against a database who's name ends with " + TEST_DB_SUFFIX);
    }
  }

  /**
   * Asserts that the *contents* of the passed files are equal.
   * 
   * @param file1 File to compare.
   * @param file2 File to compare.
   * @throws IOException If an error occurs comparing the file contents.
   */
  public static void assertFileEquals(File file1, File file2) throws IOException {
    assertFileEquals(null, file1, file2);
  }

  /**
   * Asserts that the *contents* of the passed files are equal.
   * 
   * @param message Failure message.
   * @param expectedFile File containing expected data.
   * @param actualFile File to compare.
   * @throws IOException If an error occurs comparing the file contents.
   */
  public static void assertFileEquals(String message, File expectedFile, File actualFile) throws IOException {
    assertFileEquals(message, expectedFile, null, actualFile, null);
  }

  /**
   * Asserts that the *contents* of the passed files are equal.
   * 
   * @param message Failure message.
   * @param expectedFile File containing expected data.
   * @param expectedFileEncoding Encoding of expected file.
   * @param actualFile File to compare.
   * @param actualFileEncoding Encoding of actual file.
   * @throws IOException If an error occurs comparing the file contents.
   */
  public static void assertFileEquals(String message, File expectedFile, Charset expectedFileEncoding, File actualFile,
      Charset actualFileEncoding) throws IOException {
    boolean equal = true;

    long expectedFileLength = expectedFile.length();
    long actualFileLength = actualFile.length();
    if (expectedFileLength != actualFileLength) {
      equal = false;
    } else {
      equal = fileBytesEqual(expectedFile, actualFile);
    }
    if (!equal) {

      if (message == null) {
        message = "";
      }
      if (Math.max(expectedFileLength, actualFileLength) < JedecByteUnit.MEGABYTES.toBytes(1)) {
        expectedFileEncoding = resolveToDefault(expectedFileEncoding);
        actualFileEncoding = resolveToDefault(actualFileEncoding);
        throw new ComparisonFailure(message, FileUtils.readFileToString(expectedFile, expectedFileEncoding),
            FileUtils.readFileToString(actualFile, actualFileEncoding));
      } else {
        Assert.fail(expectedFile + " not equal to " + actualFile);
      }

    }
  }

  private static boolean fileBytesEqual(File expectedFile, File actualFile) throws IOException {
    InputStream expected = null;
    InputStream actual = null;
    try {
      expected = new BufferedInputStream(new FileInputStream(expectedFile));
      actual = new BufferedInputStream(new FileInputStream(actualFile));
      int readExpected;
      int readActual;
      do {
        readActual = actual.read();
        readExpected = expected.read();
        if (readActual != readExpected) {
          return false;
        }
      } while (readExpected != -1 && readActual != -1);
    } finally {
      IOUtils.closeQuietly(expected);
      IOUtils.closeQuietly(actual);
    }
    return true;
  }

  private static Charset resolveToDefault(Charset charset) {
    if (charset == null) {
      return Charset.defaultCharset();
    }
    return charset;
  }

  /**
   * Asserts that the *contents* of the passed files are not equal.
   * 
   * @param expectedFile File containing expected data.
   * @param actualFile File to compare.
   * @throws IOException If an error occurs comparing the file contents.
   */
  public static void assertFilesNotEqual(File expectedFile, File actualFile) throws IOException {
    assertFilesNotEqual(null, expectedFile, actualFile);
  }

  /**
   * Asserts that the *contents* of the passed files are not equal.
   * 
   * @param message Failure message.
   * @param file1 File to compare.
   * @param file2 File to compare.
   * @throws IOException If an error occurs comparing the file contents.
   */
  public static void assertFilesNotEqual(String message, File file1, File file2) throws IOException {
    boolean equalsFailed = false;
    try {
      assertFileEquals(file1, file2);
    } catch (AssertionError e) {
      equalsFailed = true;
    }
    if (!equalsFailed) {
      fail(message);
    }
  }

  /**
   * Assert that all Objects in the passed array match the expected value.
   * 
   * @param expectedValue Expected value.
   * @param values Array of Objects to compare.
   */
  public static void assertValues(String expectedValue, Object[] values) {
    for (Object value : values) {
      Assert.assertEquals(expectedValue, value);
    }
  }

  /**
   * Asserts that the contents of the files are equivalent - i.e. the same lines but not necessarily in the same order.
   * This allows you to compare files with contents that might be sorted differently. The default Charset is used for
   * reading the files.
   * 
   * @param expected File containing expected data.
   * @param actual File to compare.
   * @throws IOException If an error occurs comparing the file contents.
   */
  public static void assertFilesEquivalent(File expected, File actual) throws IOException {
    assertFilesEquivalent(expected, null, actual, null);
  }

  /**
   * Asserts that the contents of the files are equivalent - i.e. the same lines but not necessarily in the same order.
   * This allows you to compare files with contents that might be sorted differently.
   * 
   * @param expected File containing expected data.
   * @param expectedFileEncoding Encoding of expected file.
   * @param actual File to compare.
   * @param actualFileEncoding Encoding of actual file.
   * @throws IOException If an error occurs comparing the file contents.
   */
  public static void assertFilesEquivalent(File expected, Charset expectedFileEncoding, File actual,
      Charset actualFileEncoding) throws IOException {
    expectedFileEncoding = resolveToDefault(expectedFileEncoding);
    actualFileEncoding = resolveToDefault(actualFileEncoding);
    List<String> expectedLines = FileUtils.readLines(expected, expectedFileEncoding);
    List<String> actualLines = FileUtils.readLines(actual, actualFileEncoding);
    int expectedLineCount = expectedLines.size();
    int actualLineCount = actualLines.size();
    Collection<String> difference;
    if (expectedLineCount >= actualLineCount) {
      difference = CollectionUtils.subtract(expectedLines, actualLines);
    } else {
      difference = CollectionUtils.subtract(actualLines, expectedLines);
    }
    assertEquals("Unexpected difference: " + difference, 0, difference.size());
  }

}
