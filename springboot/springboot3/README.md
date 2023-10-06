# Springboot #

http://localhost:9999/springboot/info

## Objetivos.
- Probar el servercontext


## CONSIDERACIONES:


## USECASE  servletContext applicaiton Scope
paquete:   package rbarec.springboot3zero.appcontext;

- podemos almacenar objeto en el app scope.
- el servletcontext es threadSafe por definicion
- SI, Publishing attributes via ServletContext#setAttribute is thread-safe!   // This can be derived from the Java Servlet Specification, chapter 4.5
- SI, So the same is also true for reading published attributes via ServletContext#getAttribute.
- Using a ConcurrentHashMap for map with data put on SERLET_CONTEXT.
https://stackoverflow.com/questions/20190070/thread-safety-of-servletcontext-objects#:~:text=We%20can%20deduce%20from%20the,getAttribute%20is%20indeed%20thread%2Dsafe.



## USECASE  Interface Driven Controllers in
paquete:   package rbarec.springboot3zero.books;

https://www.baeldung.com/spring-interface-driven-controllers
http://localhost:9999/springboot/book/
-If we add web request annotations to the controller, they’ll take precedence over the interface’s ones. In other words, 