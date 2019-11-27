import socket
import os
import time
s=socket.socket()
s.connect(('192.168.43.46',1632))
s.send(b"Send me the last modified file")
with open('file.txt', 'r') as f:
    a=f.read(1024)
    
    timeg=os.stat('file.txt').st_mtime
    s.send(bytes(time.ctime(timeg),'utf-8'))
    s.send(bytes(a,'utf-8'))
    
    
f.close()
f=s.recv(1024)
a=s.recv(1024)
time=s.recv(1024)
print(a)
print(time)
print(f)
s.close()

