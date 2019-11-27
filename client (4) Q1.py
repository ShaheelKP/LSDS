import socket
import os
import time
s=socket.socket()
s.connect(('localhost',5000))
#s.send(b"connected successfully")
with open('file.txt', 'r') as f:
    a=f.read(1024)
    
 #   timeg=os.stat('file.txt').st_mtime
 #   s.send(bytes(time.ctime(timeg),'utf-8'))
    s.send(bytes(a,'utf-8'))#a.encode('utf-8')
    
    
f.close()
data_from_server=s.recv(1024)
print(data_from_server)
s.close()

