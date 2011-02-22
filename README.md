Dining Philosophers with Java Monitors
======================================

Getting the files:
------------------

On the lab computers, use the command:
<pre><code>git clone git://github.com/cwru-eecs338/java_monitors.git</code></pre>

Running the code:
-----------------

1. Run the <code>ant</code> command to compile the Java and create an executable JAR file
2. Execute the JAR file with the command <code>./main.jar</code>
3. You can use optional arguments to change the number of philosophers and the duration of the program in seconds. For example, <code>./main.jar 25 10</code> will run the program with 25 philosophers for 10 seconds.

Lessons:
--------

* Using monitors and the <code>synchronized</code> keyword in Java.
* Observe an implementation for a solution to the dining philosophers problem.
* Basic multi--threading in Java (optional)
