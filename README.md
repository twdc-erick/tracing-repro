# tracing-repro

Two verticles are stood up in this sample app:
- Event loop verticle (default in Vertx)
- Worker verticle

After runnning the application, you can make cURL requests to each verticle in a command line.

Command to test event loop verticle behavior:
`curl 'localhost:8080' & curl 'localhost:8080'`

Command to test worker verticle behavior:
`curl 'localhost:9080' & curl 'localhost:9080'`

I ran the Datadog test agent with docker according to the instructions: https://github.com/DataDog/dd-apm-test-agent

Also downloaded and used the latest dd-java-agent.jar version. In IntelliJ, I added its path as a VM option. Also enabled dd.trace.debug in the VM options as well. 
