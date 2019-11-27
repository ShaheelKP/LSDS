import socket
import os
import time
s=socket.socket()
s.connect(('localhost',5683))
s.send(b"connected successfully")
with open('file.txt', 'r') as f:
    a=f.read(1024)
    
    timeg=os.stat('file.txt').st_mtime
    s.send(bytes(time.ctime(timeg),'utf-8'))
    s.send(bytes(a,'utf-8'))
    
    
f.close()
s.close()

