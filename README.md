date-util
==============

[![Build Status](https://github.com/perceivechuchu/date-util/actions/workflows/maven.yml/badge.svg)](https://github.com/perceivechuchu/date-util/actions/workflows/maven.yml)

date-util is a library that provides comprehensive date and time utilities, addressing the challenges of date manipulation, formatting, and timezone handling

Installation
============
The artifact is available on Maven Central and can be added to the project's pom.xml:

```xml
<dependency>
    <groupId>za.co.stridepace</groupId>
    <artifactId>date-util</artifactId>
    <version>1.0.0</version>
</dependency>
```

The latest version can be found [here]()

Usage
=====

```
String localDateText = "05/12/2023";
String dateFormatPattern = "dd/MM/yyyy";
LocalDate localDate = convertToLocalDate(localDateText, dateFormatPattern);
```

Licence
=======
The repository code is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).

Author
======
Perceive Chuchu [perceivechuchu@gmail.com](mailto:perceivechuchu@gmail.com) with :green_heart:

Feedback
========
Please [leave your feedback](https://github.com/perceivechuchu/date-util/issues) if you have noticed any issue or have a feature request.

Contributions and Support
=========================
If you want to create a new feature, though not compulsory, it will be helpful to reach out to me first before proceeding.

This avoids a scenario where one would submit a PR for an issue that someone else is working on already.
