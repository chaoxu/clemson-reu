clemson-reu
===========
The star.java is the one that suppose to give you the nimber for a set of fireworks.

After looking around, it seems you will need java virtual machine to open jar files. So you have to have Java to use the star.java.
(maybe this will be a reason why someone should write it in other languages, like C. Python would be too slow)

javac star.java
try the following in your terminal:
java star a1 a2 a3 ... an

where a1 to an are integers... and they are small integers, large ones will eat up all your memory in seconds.

Example
java star 3 4 5

output will be 

[] 0
[1] 1
[2] 2
[3] 3
[4] 1
[5] 4
[6] 3
[7] 2
[8] 1
[9] 4
[1, 1, 1] 3
[1, 1, 2] 4
[1, 1, 3] 5
[1, 1, 4] 6
[1, 1, 5] 2
[1, 2, 2] 5
[1, 2, 3] 6
[1, 2, 4] 7
[1, 2, 5] 8
[1, 3, 3] 2
[1, 3, 4] 8
[1, 3, 5] 5
[1, 4, 4] 3
[1, 4, 5] 10
[2, 2, 2] 3
[2, 2, 3] 7
[2, 2, 4] 5
[2, 2, 5] 9
[2, 3, 3] 4
[2, 3, 4] 9
[2, 3, 5] 10
[2, 4, 4] 7
[2, 4, 5] 11
[3, 3, 3] 0
[3, 3, 4] 6
[3, 3, 5] 7
[3, 4, 4] 4
[3, 4, 5] 9

I have tested java star 20 20 200, took around 6 seconds.

I have also tested java star 20 20 20 20 20, also took around 6 seconds and around 800MB of memory.

Keep in mind this algorithm takes O((2m)^n) time, and O(m^n) memory. (There is no way to around that, since we doneed to output around m^n lines). The memory will run out way before the time become unreasonable. 

Note I have uploaded a textfile of the result of running java star 30 30 30 400, which should be suffice for a while.
Just running it took 2GB of ram, and around 10 minutes on my quad core 8GB ram i7 laptop (Oh if only I can use it to play Diablo...)
The file have over 10 million lines, it is going to be a test of how fast can we operate on such large data. The file is sorted, so you might want to only read to 3 partitions if it become unreasonable to work on such large data.
