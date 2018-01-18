## Guidelines for contributors

* Use **italian** function and variable names
* Try to split a bigger work in little commits, in order to bisect easier eventual bugs
* **Do write comments** using Javadoc for every function, plus extra documentation inside functions if needed
* Name interfaces with a leading capital 'I', in order to avoid ambiguity with classes (es. *ICollection*)
* Have a question? Want to ask confirmation before adding a feature? Create an issue with the tag 'discussion'!
* Avoid using *this* keyword when not necessary, use it only in case fields and local variables names are equal

### Code formatting style
We decided to use a common code style for this project. To apply that in IntelliJ, create a file called `codeStyleSettings.xml` in .idea folder with the following content:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project version="4">
  <component name="ProjectCodeStyleSettingsManager">
    <option name="PER_PROJECT_SETTINGS">
      <value>
        <codeStyleSettings language="JAVA">
          <option name="ELSE_ON_NEW_LINE" value="true" />
          <option name="CATCH_ON_NEW_LINE" value="true" />
          <option name="FINALLY_ON_NEW_LINE" value="true" />
        </codeStyleSettings>
      </value>
    </option>
    <option name="USE_PER_PROJECT_SETTINGS" value="true" />
  </component>
</project>
```
Then, when you commit your edits, in Commit window check "Reformat code". In this way, the code will be committed with the right formatting.
