## 修复 Ubuntu apt 更新时签名无效的问题
update签名无效，这里可能的情况是 cache 出错, 可以通过以下步骤来重建cache。
按照以下命令输入即可：
```sh
sudo -i  
apt-get clean  
cd /var/lib/apt  
mv lists lists.old  
mkdir -p lists/partial  
apt-get clean  
apt-get update
```
