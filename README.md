#About
Common test classes used by some [Last.fm](http://www.last.fm) projects. We have open-sourced this code to allow us to open-source additional projects that may have a dependency on this library.

#Start using
You can [download](https://github.com/lastfm/lastcommons-test/downloads) a JAR file or obtain lastcommons-test from Maven Central using the following identifier:
* [fm.last.commons:lastcommons-test:5.1.2](http://search.maven.org/#artifactdetails%7Cfm.last.commons%7Clastcommons-test%7C5.1.2%7Cjar)

#Building
This project uses the [Maven](http://maven.apache.org/) build system.

# Contributing
All contributions are welcome. Please use the [Last.fm codeformatting profile](https://github.com/lastfm/lastfm-oss-config/blob/master/src/main/resources/fm/last/last.fm.eclipse-codeformatter-profile.xml) found in the `lastfm-oss-config` project for formatting your changes.


#Usage examples

##Temporary files and folders
Use a <tt>fm.last.commons.test.file.TemporaryFolder</tt> rule to cleanly obtain a temporary folder for file writing etc. When doing so JUnit nicely handles the life-cycle of this folder, creating it when necessary and removing it when your test is done. The code looks something like this:

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
  
    @Test
    public void myTest() {
      File myTempData = temporaryFolder.newFile("my-data");
      ...

##Data files and folders
###Top level data folder
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

###Per-class data folder
    public class MyTest {
  
      @Rule
      public DataFolder dataFolder = new ClassDataFolder();
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data/fm/last/project/MyTest
        ...

###Per-method data folder
    public class MyTest {
  
      @Rule
      public DataFolder dataFolder = new MethodDataFolder();
  
      @Test
      public void myTestMethod() throws IOException {
        File actualFolder = dataFolder.getFolder();
        // Path: ./src/test/data/fm/last/project/MyTest/myTestMethod
        ...


***Note:*** calls to <tt>getFolder()</tt> will throw an exception if the folder doesn't exist or is not readable.

#Legal
Copyright 2013 [Last.fm](http://www.last.fm/)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
