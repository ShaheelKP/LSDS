import socket
import os
import time

s=socket.socket()
ip=''
port=1632



s.bind((ip,port))
s.listen(5)


'''
def f_open(file):
    with open(file,"r") as f:
        data=f.read()
        print(data)
        return os.stat(file).st_mtime
    
   ''' 
con,addr=s.accept()
print("Connected to client")
data_from_client=con.recv(1024)
print(data_from_client)
#tm=f_open("text.txt")

#con.send(bytes(time.ctime(tm),'utf-8'))
#print(b)
with open("recieved.txt","w") as f:
    b=con.recv(1024)
    print(b)
    f.write(str(b))
f.close()   


with open('text.txt','a') as f:
    data=input("Enter Contents:")
    f.write(data)
f.close()
 
with open("text.txt","r") as f:
    c=f.read(1024)
    timestamp = os.stat("text.txt").st_mtime
    con.send(bytes(time.ctime(timestamp),'utf-8'))
    con.send(bytes(c,'utf-8'))
    

    
    
con.close()