# _Swords & Pitchforks_ RPG game
Didactic and highly experimental Java CLI RPG game made by a bunch of IT students.
**Project is no longer mantained.**

## How to add it to IntelliJ

In the starting window, click on *Checkout from Version Control* -> *GitHub* and insert your GitHub credentials.<br>
Now you should be in *Clone Repository* window. In *Git Repository URL*, paste the following link: `https://github.com/Fs00/rpg-game-4inc.git`. Then decide your local directory and you're ready.

## How to commit and push from IntelliJ

You can commit all your changes by simply pressing *Ctrl-K*, or clicking the Commit button in the upper right corner. Then you can push right-clicking any file in the project -> Repository -> Push *(Ctrl-Shift-K)*.

## How to get other people's changes

If other people in the meantime have made some modifications to the repository, you must **pull** them to your repo before committing and pushing.  
To do that, right-click any file in the project -> Repository -> Pull (it is preferable if you tick the *"No commit"* option). If there are some conflicts the IDE should warn you.

## Troubleshooting

### Source files are not recognized as part of the project (IntelliJ)

Your .iml (IntelliJ project file) must look like that:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<module type="WEB_MODULE" version="4">
  <component name="NewModuleRootManager" inherit-compiler-output="true">
    <exclude-output />
    <content url="file://$MODULE_DIR$">
        <sourceFolder url="file://$MODULE_DIR$/src" isTestSource="false" packagePrefix="ittbuonarroti.rpggame" />
    </content>
    <orderEntry type="inheritedJdk" />
    <orderEntry type="sourceFolder" forTests="false" />
  </component>
</module>
```
The problem is much likely due to the missing `sourceFolder` tag. Copy that code in your .iml file and you should be ok.
