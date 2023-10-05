# Springboot #

http://localhost:9999/springboot/info

## Objetivos.
- Probar el servercontext


## CONSIDERACIONES:
- SI, Publishing attributes via ServletContext#setAttribute is thread-safe!   // This can be derived from the Java Servlet Specification, chapter 4.5
- SI, So the same is also true for reading published attributes via ServletContext#getAttribute.
- Using a ConcurrentHashMap for map with data put on SERLET_CONTEXT.
https://stackoverflow.com/questions/20190070/thread-safety-of-servletcontext-objects#:~:text=We%20can%20deduce%20from%20the,getAttribute%20is%20indeed%20thread%2Dsafe.