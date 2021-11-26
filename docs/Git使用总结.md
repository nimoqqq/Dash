#  Git使用总结

#### 创建一个空目录

~~~markdown
* mkdir gitfile
~~~

#### 从git服务器拉取代码

~~~markdown
* git clone
~~~

#### 将目录变成Git可以管理的仓库

~~~markdown
* git init
~~~

#### 将文件添加到暂存区

~~~markdown
* git add readme.txt
~~~

#### 提交文件到版本库

~~~markdown
* git commit -m '这里写提交原因'
~~~

#### 将本地代码改动代码推送到服务器

~~~markdown
* git push
~~~

#### 将服务器上的最新代码拉取到本地

~~~markdown
* git pull： 将远程主机的最新内容拉下来后直接合并
* git fetch： 是将远程主机的最新内容拉到本地，用户在检查了以后决定是否合并到工作本机分支中。
* git push origin 分支名 : 将本地分支提交到远程分支
~~~

#### 合并代码

~~~markdown
* git merge 分支名
* git rebase 分支名 
* git rebase --continue ：解决完冲突之后执行
* 
~~~

#### 查看版本信息

~~~markdown
* git log
~~~

#### 版本回退

~~~markdown
* git reset --hard   HEAD^  或者 $ git reset --hard  HEAD~1 :回退到上一个版本  
* git reflog :用来记录你的每一次命令，以便确定要回到未来哪个版本
* git revert :撤销中间某次提交，（适合远程仓库回退。相当于在提交一个与之前相反的操作）
~~~

#### 撤销修改

~~~markdown
* git checkout -- file ：命令可以丢弃工作区的修改
* git checkout -- readme.txt  ：把readme.txt文件在工作区的修改全部撤销，撤销修改之后就回到和版本库一摸一样的状态。
* git reset HEAD file ：把暂存区的修改撤销掉（unstage），重新放回工作区
~~~

#### 配置开发者用户名和邮箱

~~~markdown
* git config
* git config user.name gafish
* git config user.email gafish@qqqq.com
~~~

#### 分支创建、重命名、查看、删除

~~~markdown
* git checkout -b ：创建并切换到分支
* git branch daily/0.0.0 ：创建分支
* git branch -m daily/0.0.0 daily/0.0.1	 ：修改分支名
* git branch  ：查看分支列表
* git branch -d daily/0.0.1  ：删除分支
* git checkout daily/0.0.1 ：切换分支
* git push origin --delete 分支名 ：删除远程分支
* git push origin 远程分支名:本地分支名 ：推送本地分支到远程分支
~~~

#### 查看文件变动状态

~~~markdown
* git status
~~~

#### 为项目标记里程碑

~~~markdown
* git tag
~~~

#### 列出已存在的远程分支

~~~markdown
* git remote -v
~~~


#### 暂时将未提交的变化移除，稍后再移入（冲突解决）

~~~markdown
* git stash
* git stash pop
~~~

