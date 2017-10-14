## Total ##
18/20
## Break Down ##
-2 GradeReader.java
## Comments ##

You were skipping a couple of lines after every read because you were not taking account for the `\n` at the end of each line properly. When you use the `useDelimeter("x")` method it essentially changes the delimiter from `\n` to `x` so your `next()` method does not account for `\n` but `x`.   
This can be corrected by adding two delimiters which I have demonstrated in the GradeReaderTA.java 
There are many other ways to solve this lab which include reading a complete line at a time and then tokenizing it using a StringTokenizer. Another way can be using regex (regular expressions) which might extract any number in the string. There are a lot of solutions that you can try.
