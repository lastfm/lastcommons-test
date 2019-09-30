# About
Common test classes used by some [Last.fm](http://www.last.fm) projects. We have open-sourced this code to allow us to open-source additional projects that may have a dependency on this library.

# Start using
Obtain lastcommons-test from Maven Central:

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/fm.last.commons/lastcommons-test/badge.svg?subject=fm.last:lastcommons-test)](https://maven-badges.herokuapp.com/maven-central/fm.last.commons/lastcommons-test) ![GitHub license](https://img.shields.io/github/license/lastfm/lastcommons-test)

This project provides a TemporaryFolder or a DataFolder for use in your JUnit tests. 

`TemporaryFolder` gives you an empty folder for each test that lets you create folders and files during your test. JUnit nicely handles the life-cycle of this folder, creating it when necessary and removing it when your test is done.

The `DataFolder` classes provide a convenient way to refer to files and folders containing test data within your project, so you can fetch data easily during a test without needing to hard code paths.

To use within your own tests, make sure you have either JUnit4 or JUnit5 in your POM. Neither are provided through `lastcommons-test`.


You can [download](https://search.maven.org/remotecontent?filepath=fm/last/commons/lastcommons-test/5.2.1/lastcommons-test-5.2.1.jar) a JAR file or obtain lastcommons-test from Maven Central using the following identifier:
* [fm.last.commons:lastcommons-test](https://search.maven.org/artifact/fm.last.commons/lastcommons-test)

# Building
This project uses the [Maven](http://maven.apache.org/) build system.

# Contributing
All contributions are welcome. Please use the [Last.fm codeformatting profile](https://github.com/lastfm/lastfm-oss-config/blob/master/src/main/resources/fm/last/last.fm.eclipse-codeformatter-profile.xml) found in the `lastfm-oss-config` project for formatting your changes.


# Usage examples

## Temporary files and folders
Use a <tt>fm.last.commons.test.file.TemporaryFolder</tt> rule to cleanly obtain a temporary folder for file writing etc. The code looks something like this:

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
  
    @Test
    public void myTest() {
      File logged = temporaryFolder.newFile("log.txt");
      File report = temporaryFolder.newFile("2012", "03", "01", "report.tsv");
      ...

## Data files and folders

Data folders are now available as JUnit4 Rules _and_ JUnit5 Extensions. Examples can be found below:

### Top level data folder

#### JUnit5 Example
    public class MyTest {
  
      @RegisterExtension
      public DataFolder dataFolder = new RootDataFolderExtension();
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data
        ...
        
And also a child folder within the root:

    public class MyMp3Test {
  
      @RegisterExtension
      public DataFolder dataFolder = new RootDataFolderExtension("mp3", "128K", "clean");
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data/mp3/128k/clean
        ...


#### JUnit4 Example
    public class MyTest {
  
      @Rule
      public DataFolder dataFolder = new RootDataFolder();
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data
        ...

And also a child folder within the root:

    public class MyMp3Test {
  
      @Rule
      public DataFolder dataFolder = new RootDataFolder("mp3", "128K", "clean");
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data/mp3/128k/clean
        ...

### Per-class data folder

#### JUnit5 Example
    public class MyTest {
  
      @RegisterExtension
      public DataFolder dataFolder = new ClassDataFolderExtension();
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data/fm/last/project/MyTest
        ...

#### JUnit4 Example
    public class MyTest {
  
      @Rule
      public DataFolder dataFolder = new ClassDataFolder();
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data/fm/last/project/MyTest
        ...

### Per-method data folder

#### JUnit5 Example
    public class MyTest {
  
      @RegisterExtension
      public DataFolder dataFolder = new MethodDataFolderExtension();
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data/fm/last/project/MyTest/myTestMethod
        ...


#### JUnit4 Example
    public class MyTest {
  
      @Rule
      public DataFolder dataFolder = new MethodDataFolder();
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data/fm/last/project/MyTest/myTestMethod
        ...


***Note:*** calls to <tt>getFolder()</tt> will throw an exception if the folder doesn't exist or is not readable.

# Legal
Copyright 2013-2019 [Last.fm](http://www.last.fm/)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
