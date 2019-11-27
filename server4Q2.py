import socket
import os
import time
import numpy as np

s=socket.socket()
ip=''
port=np.random.randint(5000,6000,1)



s.bind((ip,port))
s.listen(5)
print("Server started at ",s.getsockname())
con,addr=s.accept()
print("Connected to client")
data_from_client=con.recv(1024)
print(data_from_client)

with open("recieved.txt","w") as f:
     b=con.recv(1024)
     print(b)
f.close()   

   
con.close()