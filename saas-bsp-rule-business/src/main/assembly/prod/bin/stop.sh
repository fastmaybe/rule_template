pid=`ps -ef |grep java|grep saas-bsp-rule-service|grep -v grep|awk '{print $2}'`
kill -5 $pid