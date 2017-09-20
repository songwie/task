<p>
	<strong>统一任务管理系统</strong> 
</p>
<p>
	代码地址：<a href="https://github.com/songwie/task" target="_blank">https://github.com/songwie/task</a> 
</p>
<p>
	下载地址：<a href="http://songwie.com/attached/task/task-web.war" target="_blank">http://songwie.com/attached/task/task-web.war</a>
</p>
<p>
	任务管理系统是一个java 实现的，<strong>spring</strong> 集成<strong>quartz</strong>的动态任务管理系统， 通过该应用可以动态管理任务的调用，无需重启服务。
</p>
<p>
	&nbsp; &nbsp; &nbsp; 任务既可以通过http 的方式调用统一RES 的方式调用其它项目任务，也可以在task 中管理执行自己任务，部署task 新任务，通过 虚拟机class reload，无需重启。
</p>
<p>
	技术架构：
</p>
<p>
	&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; spring boot + spring-data-jpa + jquery
</p>
<p>
	截图：
</p>
<p>
	<img src="http://songwie.com/attached/image/20150215/20150215114720_395.png" alt="" /> 
</p>
<p>
	<img src="http://songwie.com/attached/image/20150215/20150215114739_785.png" alt="" /> 
</p>
<p>
启动方式：
</p>
<p>
spring boot启动：
java -jar C:\Users\sw\Desktop\task-web.war 
</p>
<p>
或者运行根目录下的start.sh
具体参数为：

nohup java -Xms500m -Xmx500m -Xmn100m -XX:PermSize=64m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:CMSInitiatingOccupancyFraction=70 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1986 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar task-web.war --spring.profiles.active=test --datasource.druid.url=jdbc:mysql://127.0.0.1:3306/task --datasource.druid.username=root --datasource.druid.password=root &  

以上根据实际情况修改数据库地址、用户名、密码
</p>

<p>
后续改造路线：
	1. 完善日志监控，任务监控
	
	2. 集成mq（activemq等）把任务消息触发异步化，应用只需要集成mq消息的客户端即可接收定时任务，兼容非web应用，作为统一消息平台
	
	3. 任务切分，基于主流的3层任务切分思路把单一的任务分为a.任务切分、b.任务data load c.任务执行excute三个阶段  最终把任务打散切分到整个集群减少单一节点压力。
</p>
