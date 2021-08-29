# Docker学习笔记

## Docker安装

### 环境准备

* CentOS7

> 环境查看

~~~shell
## 系统内核
[root@iZ2ze631txosifz9amuka8Z ~]# uname -r
4.18.0-240.15.1.el8_3.x86_64
~~~

~~~shell
##系统版本
[root@iZ2ze631txosifz9amuka8Z ~]# cat /etc/os-release
NAME="CentOS Linux"
VERSION="8"
ID="centos"
ID_LIKE="rhel fedora"
VERSION_ID="8"
PLATFORM_ID="platform:el8"
PRETTY_NAME="CentOS Linux 8"
ANSI_COLOR="0;31"
CPE_NAME="cpe:/o:centos:centos:8"
HOME_URL="https://centos.org/"
BUG_REPORT_URL="https://bugs.centos.org/"
CENTOS_MANTISBT_PROJECT="CentOS-8"
CENTOS_MANTISBT_PROJECT_VERSION="8"
~~~

> 安装

​	帮助文档：https://docs.docker.com/

~~~shell
#1、卸载旧的版本
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
                  
#2、 需要的安装包
sudo yum install -y yum-utils

#3、设置镜像仓库
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo  #默认是国外的镜像
    
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo   #阿里镜像

#4、安装docker -ce社区办 -ee企业版
sudo yum install docker-ce docker-ce-cli containerd.io

#5、启动docker
sudo systemctl start docker

#6、使用docker version 是否安装成功
docker version 

#7、运行 hello-world
sudo docker run hello-world

#8、查看一下下载的这个hello-world 镜像
docker images
~~~

了解：卸载docker

~~~shell
#1、卸载依赖
yum remove docker-ce docker-ce-cli containerd.io

#2、删除资源
rm -rf /var/lib/docker

# /var/lib/docker   docker的默认工作路径
~~~

### 阿里云镜像加速

1. 工作台搜索『镜像加速器』

2. 选择『镜像工具』--- 『镜像加速器』

~~~shell
# 执行配置命令
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://u8t7tej2.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
~~~

## Docker的常用命令

### 帮助命令

~~~shell
docker version			# 显示docker的版本信息
docker info					# 显示docker的系统信息，包括镜像和容器的数量 
docker 命令 --help	 # 万能命令
~~~

帮助文档：https://docs.docker.com/reference/

### 镜像命令

* **docker images 查看所有本地的主机上的镜像**

~~~shell
[root@iZ2ze631txosifz9amuka8Z ~]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED        SIZE
hello-world   latest    d1165f221234   3 months ago   13.3kB

# 解释
REPOSITORY	镜像的仓库源
TAG					标签
IMAGE ID		镜像的ID
CREATED			镜像的创建时间
SIZE				镜像的大小

# 可选项
-a, --all             列数所有镜像
-q, --quiet           只显示镜像ID
~~~

* **docker search 搜索镜像**

~~~shell
[root@iZ2ze631txosifz9amuka8Z ~]# docker search mysql
NAME                              DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
mysql                             MySQL is a widely used, open-source relation…   11063     [OK]
mariadb                           MariaDB Server is a high performing open sou…   4193      [OK]
mysql/mysql-server                Optimized MySQL Server Docker images. Create…   822                  [OK]
mysql/mysql-cluster               Experimental MySQL Cluster Docker images. Cr…   88

# 可选项
--filter=STARS=3000  # 搜索出来stars大于3000的

~~~

* **docker pull 下载镜像**

~~~shelll
# 下载镜像 docker pull 镜像名 [:tag]
docker pull mysql
Using default tag: latest		# 如果不写tag，默认下载最新的
latest: Pulling from library/mysql
b4d181a07f80: Pull complete		# 分层下载
a462b60610f5: Pull complete
578fafb77ab8: Pull complete
524046006037: Pull complete
d0cbe54c8855: Pull complete
aa18e05cc46d: Pull complete
32ca814c833f: Pull complete
9ecc8abdb7f5: Pull complete
ad042b682e0f: Pull complete
71d327c6bb78: Pull complete
165d1d10a3fa: Pull complete
2f40c47d0626: Pull complete
Digest: sha256:52b8406e4c32b8cf0557f1b74517e14c5393aff5cf0384eff62d9e81f4985d4b	# 签名
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest	# 真实地址

# 指定版本下载
[root@iZ2ze631txosifz9amuka8Z ~]# docker pull mysql:5.7
5.7: Pulling from library/mysql
b4d181a07f80: Already exists
a462b60610f5: Already exists
578fafb77ab8: Already exists
524046006037: Already exists
d0cbe54c8855: Already exists
aa18e05cc46d: Already exists
32ca814c833f: Already exists
52645b4af634: Pull complete
bca6a5b14385: Pull complete
309f36297c75: Pull complete
7d75cacde0f8: Pull complete
Digest: sha256:1a2f9cd257e75cc80e9118b303d1648366bc2049101449bf2c8d82b022ea86b7
Status: Downloaded newer image for mysql:5.7
docker.io/library/mysql:5.7
~~~

* **Docker rmi 删除镜像**

~~~shell
Docker rmi -f 镜像id   # 删除指定容器
Docker rmi -f 镜像id 镜像id 镜像id 镜像id  # 删除多个容器
Docker rmi -f $(docker images -aq) # 删除全部的容器
~~~

* 提交镜像

~~~shell
docker commit # 提交容器称为一个新的副本

# docker commit -m="提交信息描述" -a="作者" 容器id 目标镜像名：[TAG]
~~~



### 容器命令

**说明：有了镜像才可以创建容器，以linux为例；下载一个centos镜像来测试学习**

~~~shell
docker pull centos
~~~

* 新建容器并启动

~~~shell
docker run [可选参数] image

# 参数说明
--name = "name" 容器名字，用来区分容器
-d 		后台运行
-it		使用交互方式运行，进入容器产看内容
-p
		-p  主机端口：容器端口
		-p	容器端口
		-p ip:主机端口：容器端口
-P			随机端口
~~~

* 列出容器列表

~~~shell
docker ps # 当前正在运行的容器
-a			# 列出所有容器
-n=？ 	 # 显示最近创建的容器	
-q		  # 只显示容器的编号
~~~

* 退出容器

~~~shell
exit #退出并停止
command + P + Q # 退出不停止
~~~

* 删除容器

~~~shell
docker rm 容器id   # 删除指定容器
docker rm -f 容器id   # 强制删除指定容器
docker rm -f $(docker ps -aq)	# 删除所有容器
docker ps -a -q | xargs docker rm # 删除所有的容器
~~~

* 启动和停止容器操作

~~~shell
docker start 容器id			# 启动容器
docker restart 容器id		# 重启容器
docker stop 容器id			# 停止当前正在运行的容器
docker kill 容器id			# 强制停止当前正在运行的容器
~~~



### 常用的其他命令

* 查看日志

~~~shell
# docker log
	-f
		--tail nnumber # 要显示日志的条数
	-t	# 时间戳
~~~

* 查看容器进程信息

~~~shell
# docker top 容器id
~~~

* 查看元数据

~~~shell
# docker inspect 容器id
~~~

* 进入当前正在运行的容器

~~~shell
docker exec -it 容器id bashshell 	# 进入容器后开启一个新的终端

docker attach 容器id			# 进入容器正在执行的终端
~~~

* 从容器内拷贝文件到主机

~~~shell
docker cp 容器id :容器路径 主机路径
~~~



### 部署Nginx

~~~shell

~~~



### 部署Tomcat

~~~shell

~~~



### 部署ES+Kibana

~~~shell

~~~

### 小结

~~~shell

~~~



## 容器数据卷

* 使用数据卷

~~~shell
# volume
docker run -it -v 主机目录:容器内目录
~~~

* 具名和匿名挂载

~~~shell
# 匿名挂载
-v 容器内路径
docker run -d -p --name nginx01 -v /etc/nginx nginx

# 具名挂载
docker run -d -p --name nginx02 -v juming-nginx:/etc/nginx nginx

# 查看所有的 volume 的情况
docker vloume ls

# 多个容器共享挂载路径
docker run -d -p --name nginx01 -v juming-nginx:/etc/nginx nginx

docker run -d -P --name nginx02 --volumes-from nginx01 nginx
~~~

* 拓展

~~~shell
# 通过 -v 容器内路径，ro rw 改变读写权限
ro readonly  	只读
rw readwrite	读写

# docker run -d -P --name nginx01 -v juming-nginx:/etc/nginx:ro nginx
# docker run -d -P --name nginx01 -v juming-nginx:/etc/nginx:rw nginx
~~~

## DockerFile



### DockerFile 的指令

~~~shell
FROM					# 基础镜像，一切从这里开始构建
MAINTAINER		# 镜像是谁写的，作者+邮箱
RUN						# 镜像构建的时候需要运行的命令
WORKDIR				# 镜像的工作目录
VOLUME				# 挂载的目录
EXPOSE				# 暴露端口配置
ADD						# 步骤：添加命令，
CMD						# 指定这个容器启动的时候要运行的命令。（只有最后一个会生效，可以被替代）
ENTRYPOINT		#指定这个容器启动的时候要运行的命令，可以追加命令

ONBUILD 			#当构建一个被继承DockerFile这个时候就会运行ONBUILD的指令。触发指令
COPY					#类似ADD，将我们文件拷贝到镜像中
ENV						#构建的时候设置环境变量！
~~~



### 实战

1. 创建自己的DockerFile

~~~markdown
# cat dockerFile-mycentos
FROM centos
MAINTAINER chuf<907701820@qq.com>
ENV MYPATH	/usr/local
WORKDIR	$MYPATH
RUN yum -y install vim
RUN yum -y install net-tools
EXPOSE 80
CMD echo $MYPATH 
CMD echo "----end---"
CMD /bin/bash
~~~

2. 构建镜像

~~~shell
#命令docker build -f dockerie文件路径 -t 镜像名:[tag]
docker build -f dockerFile-mycentos -t mycentos:0.1 .
~~~



### 实战: Tomcat 镜像

1. 准备镜像文件 tomcat 压缩包，jdk的压缩包！



2. 编写dockerfiler 文件，官方命名`DockerFiler`,build 会自动寻找这个文件。就不需要 -f 指定了！



3. 构建镜像



4. 启动镜像



5. 访问测试



6. 发布项目，使用卷挂载



### 发布自己的镜像

1、地址https://hub.docker.com/注册自己的账号! 

2、确定这个这账号可以登录

3、在我们服务器上提交自己的镜像

4、登录完毕后就可以提交镜像了，就是一步docker push



## Docker 网络

### 理解 Docker0

~~~shell
ip addr  # 获取本机 ip
~~~

**Linux 可以 ping 通 Docker内部容器**

> 原理：
>
> 1. 我们每启动一个docker容器，docker就会给docker容器分配一个ip，我们只要安装了docker，就会有一个网卡docker0 桥接模式，使用的技术是evth-pair技术！
> 2. 在启动一个容器测试，发现又多了一对网卡~！
>
> ~~~shell
> # 我们发现这个容器带来网卡，都是一对对的
> # evth-pair就是一对的虚拟设备接口，他们都是成对出现的，一段连着协议，一段彼此相连
> # 正因为有这个特性，evth-pair充当一个桥梁，连接各种虚拟网络设备的
> # OpenStac，Docker容器之间的连接，OVS的连接，都是使用evth-pair技术
> ~~~



### --Link

> 本质探究：一link就是我们在hosts配置中增加了一个172.18.0.3 tomcat02

### 自定义网络（容器互连）

~~~shell
# 网络模式
	* bridge：桥接docker（默认ID
	* none：不配置网络
	* host：和宿主机共享网络
	* container：容器网络连通！（用的少！局限很大）
~~~

