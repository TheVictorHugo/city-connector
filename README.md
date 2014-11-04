CityConnector
=============

I created a web server (called http-server) that servers static HTML and image files for an advanced programming course. Given time constraints, the server only supports the GET method. It returns a 501 status code for all other methods. The server is robust against client failur. If a client browser crashes in the middle of sending a request, the server closes the socket connection and moves on to the next client request.

In this particular implementation, the server connects to a database (mdb-lookup-host) that lets users search for a name or message. The web server makes a TCP connection to the mdb-lookup-server when it starts up and keeps using the same socket connection. There's only one persistent connection to the mdb-lookup-server during the execution of the web server. 

To compile
-------
Simply use the makefile.

To run
-------
    ./http-server <server_port> <web_root> <mdb-lookup-host> <mdb-lookup-port>
    
    ./http-server 8888 /home/you/html localhost 9999
    
"server_port" is the port listening for HTTP requests and "web_root" is the top level directory for HTML files. 
    
    
    
