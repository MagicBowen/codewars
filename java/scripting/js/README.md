Steps for OSX repeated here:

Download avatar-js.jar from the java.net Maven Repo. Current version is here: https://maven.java.net/content/repositories/public/com/oracle/avatar-js/0.10.25-SNAPSHOT/ Example file: avatar-js-0.10.25-20140313.063039-43.jar
Download the native library avatar-js.dylib from the java.net Maven Repo. Current version is here: https://maven.java.net/content/repositories/public/com/oracle/libavatar-js-macosx-x64/0.10.25-SNAPSHOT/ 
Example file:libavatar-js-macosx-x64-0.10.25-20140312.062209-35.dylib 
For Linux you would download the corresponding .so file from ../libavatar-js-linux-x64/0.10.25-SNAPSHOT/ 
For Windows you would download the corresponding .dll file from ../libavatar-js-win-x64/0.10.25-SNAPSHOT/
Rename the native library to avatar-js.dylib and rename the jar to avatar-js.jar and put both in a directory called dist.
Create a simple Node.js app in the a file called app.js.
Run the command: java -Djava.library.path=dist -jar dist/avatar-js.jar app.js

Repostory:

https://maven.java.net/content/repositories/public/com/oracle/avatar-js
https://maven.java.net/content/repositories/public/com/oracle/libavatar-js-macosx-x64/

Tutorial:

http://www.baeldung.com/java-nashorn
http://wiki.apidesign.org/wiki/AvatarJS
https://github.com/apigee/trireme
https://www.infoq.com/articles/nashorn
https://github.com/coveo/nashorn-commonjs-modules
https://github.com/rtyler/avatar-js
https://blogs.oracle.com/java-platform-group/nodejs-and-iojs-on-java
http://blog.jonasbandi.net/2014/03/running-nodejs-applications-on-jvm-with.html
https://strongloop.com/strongblog/how-to-run-node-js-on-the-jvm-with-avatar-js-and-loopback/
https://stackoverflow.com/questions/31008639/embed-avatar-js-in-java-application-example
http://www.oracle.com/technetwork/articles/java/jf14-nashorn-2126515.html
https://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/
https://github.com/pk11/nashorn-websockets-demo
https://github.com/rahmanusta/Nashorn
https://github.com/brunoborges/nashorn