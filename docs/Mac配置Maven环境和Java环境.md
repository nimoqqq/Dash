# Mac配置Maven环境

1. 下载maven包到本地

2. 在终端中输入命令

   ~~~json
   vi ~/.bash_prfile
   
   ## 输入i进入编辑模式
   ## 《注意M2_HOME为自己的maven路径》
   export M2_HOME=/Users/zeal/MyApp/MyRepository/apache-maven-3.6.1
   
   export PATH=$PATH:$M2_HOME/bin
   ##输入：wq保存文件退出
   ##输入：source .base_profile(执行文件)
   ##查看是否配置成功，输入
   echo $M2_HOME
   mvn -version
   ~~~

3. 

