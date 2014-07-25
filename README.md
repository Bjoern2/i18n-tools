i18n-tools for Java
===================

This application converts Java I18N files (files with extention '.properties') into XLS and back.

[![Build Status](https://buildhive.cloudbees.com/job/Bjoern2/job/i18n-tools/badge/icon)](https://buildhive.cloudbees.com/job/Bjoern2/job/i18n-tools/)


Example:
Input files:
```
# I18N.properties
hello=Hello
```

```
# I18N_de_DE.properties
hello=Hallo
```

```
# I18N_en_EN.properties
hello=Hello
```

```
# I18N_fr_FR.properties
hello=Salut
```

Console command:
```bash
# Linux and friends
sh ~/i18n-tools/bin/i18n-tools --inputFile ~/I18N.properties --outputFile ~/I18N.xls
```
```bash
# Windows (command line; cmd.exe)
c:\i18n-tools\bin\i18n-tools.bat --inputFile c:/temp/I18N.properties --outputFile c:/temp/I18N.xls
```
(Please use for both parameters absolute paths. No relative paths!)

Output file:
I18N.xls
<table>
  <tr>
    <th>Key</th><th>Default</th><th>de_DE</th><th>en_EN</th><th>fr_FR</th>
  </tr>
  <tr>
    <td>hello</td><td>Hello</td><td>Hallo</td><td>Hello</td><td>Salut</td>
  </tr>
</table>


Download
========

Download the last builded version from BuildHive:

[i18n-tools-1.0.zip](http://bjoern2.github.io/i18n-tools/downloads/i18n-tools-1.0.zip)

[i18n-tools-1.0.tar](http://bjoern2.github.io/i18n-tools/downloads/i18n-tools-1.0.tar)
