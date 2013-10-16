i18n-tools for Java
===================

This application converts Java I18N files (files with extention '.properties') into XLS and back.

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
sh ~/i18n-tools/bin/i18n --input ~/I18N.properties --output ~/I18N.xls
```

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


