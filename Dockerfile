FROM openjdk:17

COPY target/todo.jar todo.jar

ENTRYPOINT [ "java", "-jar", "/todo.jar" ]
