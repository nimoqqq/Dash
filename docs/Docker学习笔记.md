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



### 容器命令

**说明：有了镜像才可以创建容器，以linux为例；下载一个centos镜像来测试学习**

~~~shell
docker pull centos
~~~

* 新建容器并启动

~~~shell
# docker run 

~~~



