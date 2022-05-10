A lightweight web server

To run the program, go to HttpConnectionWorkerThread.java, and 
edit the filepath of index.html accordingly. 
Run the main method in HttpServer.java file. 
Go to your browser, and type localhost:8010.
A very simple webpage that 
describes The best vacation spots in US will be served.
It works on all browsers, and it uses Networking, 
Multi-threading, and reading files.



The http server will keep listening on port 8010, until
the user manually stops the server.

The program does the following:
-Reads configuration files
-**Opens a Socket to listen to a port**
-Reads Request Messages
-Opens and reads files from the filesystem
-Writes response messages

http.json: for the configuration files
configuration.java: the file that the JSon is mapped to
json.java: creates a private static object mapper,
and a way to pass a JSon string into a JSon Node

Methods are briefly explained with comments in the code.