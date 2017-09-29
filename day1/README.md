# Day 1 exercise
## Headers:
Q1:<br>
First time you load it, it has the status 200, next time it is 304(not modified), meaning that the page is cached.<br>
Q2:<br>
It is there to keep the connection alive, so you do not need to create a new one each time you refresh the server. You can also put the connection header alive on a time limit.<br>
Q3:<br>
I requested to go to http://studypoints.dk:, but was redirected to https://studypoints.dk. The request got a response with a status with 301(moved permanently), which made the browser do a new request to https://studypoints.dk, the location was provided in the response.<br>
## Get HTTP Request Headers on the Server
The parameters in post is written in the body, while the parameters in get is written in URL.
## Session and Cookies
In the servlet you are setting a name on the session cookie. The session will stay alive if you do not close the browser or invalidate it.
To see drawing, run the SessionDemo servlet.
## Persistent Cookies
Instead of using the session cookie, which disappear everytime you close the browser, you can use a cookie instead, that does not disappear until it has gotten over its max age, thereby making it persistent.
