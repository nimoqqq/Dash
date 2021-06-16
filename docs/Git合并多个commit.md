# Git将多个commit合并成一个commit

1. 查看提交记录

   ~~~shell
   git log --graph --pretty=oneline --abbrev-commit
   ~~~

2. 合并commit记录

   ~~~shell
   ## n 代表条数
   git rebase -i HEAD~n
   git rebase -i commitID
   ~~~

3. vi 进入编辑模式

   ~~~shell
   # pick 的意思是要会执行这个 commit
   # squash 的意思是这个 commit 会被合并到前一个commit
   ##编辑之后，保存退出
   :wq 
   ~~~

4. 强制提交到远程分支

   ~~~shell
   git push -f
   ~~~

   

