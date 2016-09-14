FROM ubuntu
MAINTAINER holyangelmom 944505383@qq.com
ENV DEBIAN_FRONTEND noninteractive
# 清空ubuntu更新包
RUN sudo rm -rf /var/lib/apt/lists/*
RUN sudo apt-get update
RUN sudo apt-get install -y vim wget curl openssh-server sudo
RUN sudo mkdir -p /var/run/sshd
# 将sshd的UsePAM参数设置成no
RUN sed -i 's/UsePAM yes/UsePAM no/g' /etc/ssh/sshd_config

RUN useradd panaidan
RUN echo "panaidan:1200310517" | chpasswd
RUN echo "panaidan   ALL=(ALL)       ALL" >> /etc/sudoers

# 把admin用户的shell改成bash，否则SSH登录Ubuntu服务器，命令行不显示用户名和目录 
RUN usermod -s /bin/bash panaidan
RUN apt-get install -y supervisor
RUN mkdir -p /var/log/supervisor
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf
EXPOSE 22

# 执行supervisord来同时执行多个命令，使用 supervisord 的可执行路径启动服务。
CMD ["/usr/bin/supervisord"]
