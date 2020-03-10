<img src="media/logo/ic_app.png" height="100px" />

OrmLite Data Backup
=============

[![Travis CI](https://img.shields.io/travis/fartem/ormlite-data-backup)](https://travis-ci.org/fartem/ormlite-data-backup)
[![Codecov](https://img.shields.io/codecov/c/github/fartem/ormlite-data-backup)](https://codecov.io/gh/fartem/ormlite-data-backup)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-OrmLite%20Data%20backup-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/7940)
[![Open issues](https://img.shields.io/github/issues-raw/fartem/ormlite-data-backup.svg?color=ff534a)](https://github.com/fartem/ormlite-data-backup/issues)

About
-------------

Demo app for demonstration a way of backup user data.
Main functionality is working.

Features
-------------

* create local (on running device) backup;
* restore local backup.

Downloads
-------------

<img src="media/qrcodes/github_download.png" height="150px" />

Screenshots
-------------

<p align="center">
  <img src="media/screenshots/screenshot_01.png" width="150" />
  <img src="media/screenshots/screenshot_02.png" width="150" />
  <img src="media/screenshots/screenshot_03.png" width="150" />
  <img src="media/screenshots/screenshot_04.png" width="150" />
  <img src="media/screenshots/screenshot_05.png" width="150" />
</p>

How to contribute
-------------

Read [Commit Convention](https://github.com/fartem/repository-rules/blob/master/commit-convention/COMMIT_CONVENTION.md). Make sure your build is green before you contribute your pull request. Then:

```shell
gradlew clean
gradlew build
gradlew -Pandroid.testInstrumentationRunnerArguments.class=com.smlnskgmail.jaman.ormlitedatabackup.AndroidTestSuite connectedCheck
```

If you don't see any error messages, submit your pull request.

Contributors
-------------

* [@fartem](https://github.com/fartem) as Artem Fomchenkov