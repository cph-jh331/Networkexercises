## When and why we will use Threads in our programs?
We use threads when we want to do tasks at the same time.
## Explain about the Race Condition Problem and ways to solve it in Java
A race condition occurs when two threads are trying to change the state of a nonatomic object. This can create inconsistent data and loss of hair.<br>
We can fix it by using synchronized on the methods that are accessed by multiple threads or make the object atomic.
## Explain how we can write reusable non-blocking Java Controls using Threads 
When an client connects to the server, it generates an new thread, which controls the client. This makes them not block eachother, since they all are running concurrently.
## Explain about deadlocks, how to detect them and ways to solve the Deadlock Problem
Deadlocks happens when you have threads that are waiting on each other in a circular fahsion. To fix it by trying not to create threads that wait on eachother by atomizing
the the methods, so only one thread can access the method at a time.

## exercise:
1. Program is started from server.ServerTest.java.<br>
2. incrementing count can create a race condition, since more than thread can access it at a time. This is fixed by synchronizing the increment method.<br>
3. telnet o 127.0.0.1 9999, enter turntile to become one, or monitor to become that.<br>
Turntile options: count: increments the spectator count by one. spectators: shows the current number of spectators. exit: exits.<br>
Monitor options: spectators: shows the current number of spectators. exit: exits.


