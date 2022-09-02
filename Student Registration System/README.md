# Project's Title : Student Registration System.

## Project Description:
1 - This project used to register students in Database , update, and delete them.

2 - I used MVC Architecture: 
      ** Servlet technology for business logic layer (Controller) with java.
      ** JSP technology for view layer.
      ** Oracle Database for model layer.
      
![MVC](https://user-images.githubusercontent.com/61011535/188104751-81c808a4-335e-4c58-9821-2aaf9fd9f342.PNG)

## How to run:

1 - Install Apache tomcat v9.0
2 - Install Oracle Database
3 - Create a pluggable database with name: Student_Registration_System with your userName and password.
4 - put your userName and password in webContent\META-INF\context.xml file.
5 - Create a table with these queries:

```
CREATE TABLE student  
( student_id number(11) NOT NULL ,  
  firstName varchar2(25) NOT NULL,  
  lastName varchar2(25) NOT NULL,
  Email varchar2(30) NULL,
  CONSTRAINT student_pk PRIMARY KEY (student_id)  
);  


CREATE SEQUENCE student_seq;

CREATE OR REPLACE TRIGGER student_on_insert
  BEFORE INSERT ON student
  FOR EACH ROW
BEGIN
  SELECT student_seq.nextval
  INTO :new.student_id
  FROM dual;
END;
```
6 - Install Eclipse and connect it to Tomcat
7 - import the App into Eclipse
8 - Run on Server.


Project Pages:

![Student Pages](https://user-images.githubusercontent.com/61011535/188109179-d4c1cf6f-62b8-490e-8efe-e14a98976d01.PNG)


![Add Student page](https://user-images.githubusercontent.com/61011535/188109202-5d935d89-c93d-4240-8bb7-7cb0bcf0cbe0.PNG)


![Validation](https://user-images.githubusercontent.com/61011535/188109217-9c6de84f-47ce-4b36-bee1-960c7ffac1e2.PNG)


![update student page](https://user-images.githubusercontent.com/61011535/188109234-8681d618-7185-4b1b-ace7-61c7e2c3ab79.PNG)



