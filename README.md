
- 将javacup runtime加入maven仓库
```
# mvn install:install-file -DgroupId=fs -DartifactId=j
avacup -Dversion=11b -Dpackaging=jar -DgeneratePom=true -Dfile=java-cup-11b-runt
ime.jar
```

执行 java -jar java-cup-11b.jar sql.cup 生成对应的java文件
执行 java JLex.Main dep/yylex.lex 生成词法解析java文件，重命名为Yylex.java

或者执行ant命令

[参考](http://www2.cs.tum.edu/projects/cup/docs.php)