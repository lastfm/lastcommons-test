## [7.0.2] - TBD
### Changed
- Made `RootDataFolderExtension` and `RootSqlFolderExtension` classes public.

## [7.0.1] - 2019-10-22
### Changed
- Redeploying Maven Artifacts as 7.0.0 release is not showing up in Maven Central (was released when Sonatype was experiencing issues so release 
  seems to have gone to a black hole).

## [7.0.0] - 2019-09-30
### Added
- Folder extension classes for all the `DataFolder` rules.

### Changed
- JUnit4 and JUnit5 dependencies made `provided` in the POM.

## [6.1.0] - 2019-09-10
### Changed
- Updated `MethodRule` to `TestRule` in the `DataFolder` interface.

## [6.0.0] - 2019-09-04
### Removed
- Removed test dependency on Spring. 

## [5.2.1] - 2016-03-13
### Changed
- Update Apache dependency to 3.2.2 for security fixes.
- Update Spring dependency to 3.2.14 so tests pass on Java 8.

## [5.2.0] - 2013-10-25
### Changed
- Support multi-element paths in `TemporaryFolder`.

## [5.1.2] - 2013-08-16
### Changed
- `assertFileEquals` now prints out a nice message when files aren't equal and are smaller than 1MB.
- added `assertFilesEquivalent` which compares files with the same lines, just in a different order.
- added ability to pass in `CharSet` to use for reading files to both methods above.

## [5.1.1] - 2013-04-12
### Changed
- Modifications to file path resolution for Windows, contributed by mzgubin - see http://code.google.com/p/citrine-scheduler/issues/detail?id=44.
- Migrated project to lastfm-oss-parent.
- upgrading Spring to 3.2.2.RELEASE.

## [5.0.0] - 2012-08-08)
### Changed
- Open Sourced.
- Initial release from GitHub.
