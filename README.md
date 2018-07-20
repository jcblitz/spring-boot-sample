# spring-boot-sample
Sample Spring Boot web app to demo Jenkins pipelines

# Vagrantfile

The enclosed vagrantfile is for a Jenkins server running on port 8080 that starts up by default. 

You should also install Tomcat 8 to test the deploy stage. You can see below where I installed it for my testing. It is not included in the Vagrantfile, you need to do this manually

# Configure tomcat manager

You'll need to enable tomcat manager to use that method of deploying, which I am. It requires that module to be turned on as well as a user with access. Below are snippets on how to do that.

## Tomcat config
```
[vagrant@localhost localhost]$ pwd
/home/vagrant/apache-tomcat-8.5.32/conf/Catalina/localhost
[vagrant@localhost localhost]$ cat manager.xml 
<Context privileged="true" antiResourceLocking="false" 
         docBase="${catalina.home}/webapps/manager">
    <Valve className="org.apache.catalina.valves.RemoteAddrValve" allow="^.*$" />
</Context>
```

## Add tomcat user

Not a good practice for -gui and -script to be the same user. This is a POC so it doesn't matter
```
<role rolename="manager-gui"/>
<user username="tomcat" password="password" roles="manager-gui,manager-script"/>
```

# Jenkins

Once you have Jenkins running, create a new pipeline from SCM and point to this project in github and hit build.