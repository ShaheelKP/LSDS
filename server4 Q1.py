import socket
import os
import time

s=socket.socket()
ip=''
port=5000



s.bind((ip,port))
s.listen(5)
print("Server started at ",s.getsockname())
con,addr=s.accept()
print("Connected to client")
data=con.recv(1024).decode('utf-8')
print(data)
if data.isnumeric():
    while len(data)!=1:
        sum=0
        for c in data:
            sum=sum+int(c)
        data=str(sum)
        print(data)
    con.send(bytes(data,'utf-8'))
else:
    print('sorry')
         
   
con.close()