##desc:

    use for generate android model code  with a quick way
    like rails scaffold


##usage :   
   ###   `java -jar <pathofjar> -g <ModelName> -attr <FieldName> <FieldType> ...`
* `pathofjar`  path of this build jar file
* -g        assign model name  
  `ModelName`  the model name you want generate, like student,
* -p        assign package name   
   if you want assign another package name ,use this attr
* -attr     assign model fields , fieldName and fieldType pairs  
  `FieldName`  the model's field ,like id,age,height,weight  
  `FieldType`  the model field type, support int,string,float,double,name,password
       
  ####example:
* 1. cd code path  
      cd project/module/src/main/java/com/test/testFragment/
* 2. generate code  
      ###`java -jar D:\AndroidCodeGen-0.1.jar -g student -attr age int name string weight float`    
      `D:\AndroidCodeGen-0.1.jar` is jar file path  
      `student` is model name  
      `age\name\weight` is attribute
