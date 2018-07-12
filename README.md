# spring-boot-sample
Sample Spring Boot web app for demo purposes.

# Configure tomcat manager
```
[vagrant@localhost localhost]$ pwd
/home/vagrant/apache-tomcat-8.5.32/conf/Catalina/localhost
[vagrant@localhost localhost]$ cat manager.xml 
<Context privileged="true" antiResourceLocking="false" 
         docBase="${catalina.home}/webapps/manager">
    <Valve className="org.apache.catalina.valves.RemoteAddrValve" allow="^.*$" />
</Context>
```

# Add tomcat user
Not good practice for -gui and -script as the same user. This is a POC
```
<role rolename="manager-gui"/>
<user username="tomcat" password="password" roles="manager-gui,manager-script"/>
```