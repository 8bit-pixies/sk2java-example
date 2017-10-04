
Installing the environment:

```sh
conda env create -f environment.yml 
```

Update environment configuration

```sh
conda env export > environment.yml
```

Force update of an environment

```sh
conda env update -f environment.yml
```

## Creating a Jar

```
javac Brain.java
jar cf Brain.jar Brain.class

# usage:
java -jar Brain.jar 
```