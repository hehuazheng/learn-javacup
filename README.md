
- 将javacup runtime加入maven仓库
```
# mvn install:install-file -DgroupId=fs -DartifactId=j
avacup -Dversion=11b -Dpackaging=jar -DgeneratePom=true -Dfile=java-cup-11b-runt
ime.jar
```

[参考](http://www2.cs.tum.edu/projects/cup/docs.php)