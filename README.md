<img src="media/logo/ic_app.png" height="100px" />

OrmLite Data Backup
=============

[![Travis CI](https://travis-ci.org/fartem/ormlite-data-backup.svg?branch=master)](https://travis-ci.org/fartem/ormlite-data-backup)
[![Codebeat](https://codebeat.co/badges/bbe23f46-26d5-46c3-907e-bd24033993d6)](https://codebeat.co/projects/github-com-fartem-ormlite-data-backup-master)
[![Codecov](https://codecov.io/gh/fartem/ormlite-data-backup/branch/master/graph/badge.svg)](https://codecov.io/gh/fartem/ormlite-data-backup)
[![Hits-of-Code](https://hitsofcode.com/github/fartem/ormlite-data-backup)](https://hitsofcode.com/view/github/fartem/ormlite-data-backup)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-OrmLite%20Data%20backup-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/7940)

About
-------------

Demo app for demonstrating a way of backup user data.

Downloads
-------------

<img src="media/qrcodes/github_download.png" height="150px" />

Screenshots
-------------

<p align="center">
  <img src="media/screenshots/screenshot_01.png" width="190" />
  <img src="media/screenshots/screenshot_02.png" width="190" />
  <img src="media/screenshots/screenshot_03.png" width="190" />
  <img src="media/screenshots/screenshot_04.png" width="190" />
</p>

How to contribute
-------------

Read [Commit Convention](https://github.com/fartem/repository-rules/blob/master/commit-convention/COMMIT_CONVENTION.md). Make sure your build is green before you contribute your pull request. Then:

```shell
$ ./gradlew clean
$ ./gradlew build
$ ./gradlew -Pandroid.testInstrumentationRunnerArguments.class=com.smlnskgmail.jaman.ormlitedatabackup.AndroidTestSuite connectedCheck
```

If you don't see any error messages, submit your pull request.

Contributors
-------------

* [@fartem](https://github.com/fartem) as Artem Fomchenkov
