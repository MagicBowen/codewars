## setup

### maven

[tutorial](http://www.yiibai.com/maven/)

#### install maven

Download : http://maven.apache.org/

install:

```bash
cp -R apache-maven-3.5.0 /usr/local/lib/maven
ln -s /usr/local/lib/maven/apache-maven-3.5.0 /usr/local/lib/maven/default
```

edit ~/.bash_profile

```bash
export MAVEN_HOME=/usr/local/lib/maven/default
export PATH="$MAVEN_HOME/bin:$PATH"
```

```bash
source ~/.bash_profile
mvn -h
```

#### setup a new project

```bash
mvn archetype:generate -DgroupId=com.bowen -DartifactId=fizzbuzzwhizz -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

#### build

```bash
mvn clean package
cd target/classes
java com.bowen.App
```

#### test

```bash
mvn test
```

#### generate project for IDE

```bash
mvn eclipse:eclipse
```

```bash
mvn idea:idea
```

### TestNg

add dependency in maven:

```
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>6.8.7</version>
        <scope>test</scope>
    </dependency> 
```

download testng plubin for eclipse:

`help -> Install New Software -> Work with`: http://beust.com/eclipse/6.9.13
After installed ok, `new project -> TestNg'

IDEA does not need add testng plubin;



### eclipse

download eclipse for java.

download `maven-archetype-quickstart-1.1.jar` from http://maven.ibiblio.org/maven2/org/apache/maven/archetypes/maven-archetype-quickstart/1.1/

```bash
mvn install:install-file -DgroupId=org.apache.maven.archetypes -DartifactId=maven-archetype-quickstart -Dversion=1.1 -Dpackaging=jar -Dfile=maven-archetype-quickstart-1.1.jar
```

Then you can setup maven project by eclipse.

### IDEA

Download IntelliJ IDEA CE;

create a maven project;

modify the code font and keywords color of java;


